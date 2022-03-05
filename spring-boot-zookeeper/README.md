# spring-boot-zookeeper

> Zookeeper 微服务注册与发现

## 步骤

1. 创建一个新的父 maven 项目
	
	1. 删除 src 文件夹
	
	2. 配置 pom.xml 文件
	
	```
	
	<modules>
        <module>spring-boot-zookeeper-provider</module>
        <module>spring-boot-zookeeper-consumer</module>
    </modules>
	
	```

2. 下载并安装 Zookeeper 软件，启动 Zookeeper

3. 服务提供者

	1. 在父 maven 项目下，创建一个新的 Spring Boot 服务提供者项目 `spring-boot-zookeeper-provider`
	
	2. 配置 pom.xml 文件，添加依赖

	```
	
	<dependencies>
	    <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
        </dependency>
	</dependencies>
	
	```
	
	3. 配置 application.yml 文件
	
	```
	
	spring:
	  cloud:
	    zookeeper:
	      connect-string: localhost:2181
	      discovery:
	        enabled: true
	        register: true
	
	```
	
	4. 编写接口类
	
	```
	
	@RestController
	public class HelloController {
	
	    @RequestMapping("/hello")
	    public String hello() {
	        return "hello zookeeper";
	    }
	}
	
	```
	
	5. 编写启动类
	
	```
	
	@SpringBootApplication
	@EnableDiscoveryClient
	public class ZookeeperProviderApplication {
	
	    public static void main(String[] args) {
	        SpringApplication.run(ZookeeperProviderApplication.class, args);
	    }
	}
	
	```

4. 服务消费者

	1. 在父 maven 项目下，创建一个新的 Spring Boot 服务消费者项目 `spring-boot-zookeeper-consumer`
	
	2. 配置 pom.xml 文件，添加依赖

	```
	
	<dependencies>
	    <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
        </dependency>
	</dependencies>
	
	```
	
	3. 配置 application.yml 文件
	
	```
	
	spring:
	  cloud:
	    zookeeper:
	      connect-string: localhost:2181
	      discovery:
	        enabled: true
	        register: false
	
	```
	
	4. 配置 RestTemplate Http 请求
	
	```
	
	@Configuration
	public class RestTemplateConfig {
	
	    @Bean
	    @LoadBalanced
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
	}
	
	```

	5. 编写接口类
	
	```
	
	@RestController
	public class HelloController {
	
	    @Resource
	    private LoadBalancerClient loadBalancer;
	
	    @Resource
	    private DiscoveryClient discoveryClient;
	
	    /**
	     * 获取所有服务
	     */
	    @RequestMapping("/services")
	    public List<ServiceInstance> services() {
	        return discoveryClient.getInstances("zookeeper-provider");
	    }
	
	    /**
	     * 从所有服务中选择一个服务（轮询）
	     */
	    @RequestMapping("/choose")
	    public String choose() {
	        return loadBalancer.choose("zookeeper-provider").getUri().toString();
	    }
	
	    @Resource
	    RestTemplate restTemplate;
	
	    @RequestMapping("/hello")
	    public String hello() {
	        return restTemplate.getForObject("http://zookeeper-provider/hello", String.class);
	    }
	}
	
	```
	
	6. 编写启动类
	
	```
	
	@SpringBootApplication
	@EnableDiscoveryClient
	public class ZookeeperConsumerApplication {
	
	    public static void main(String[] args) {
	        SpringApplication.run(ZookeeperConsumerApplication.class, args);
	    }
	}
	
	```

5. 先启动服务提供者，再启动服务消费者，调用接口，查看结果