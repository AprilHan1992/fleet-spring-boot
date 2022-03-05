# spring-boot-consul

> Consul 微服务注册与发现

## 步骤

1. 创建一个新的父 maven 项目
	
	1. 删除 src 文件夹
	
	2. 配置 pom.xml 文件
	
	```
	
	<modules>
        <module>spring-boot-consul-provider</module>
        <module>spring-boot-consul-consumer</module>
    </modules>
	
	```

2. 下载并安装 Consul 软件，`consul agent -dev` 启动 Consul

3. 服务提供者

	1. 在父 maven 项目下，创建一个新的 Spring Boot 服务提供者项目 `spring-boot-consul-provider`
	
	2. 配置 pom.xml 文件，添加依赖

	```
	
	<dependencies>
	    <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>
	</dependencies>
	
	```
	
	3. 配置 application.yml 文件
	
	```
	
	spring:
	  cloud:
	    consul:
	      host: localhost
	      port: 8500
	
	```
	
	4. 编写接口类
	
	```
	
	@RestController
	public class HelloController {
	
	    @RequestMapping("/hello")
	    public String hello() {
	        return "hello consul";
	    }
	}
	
	```
	
	5. 编写启动类
	
	```
	
	@SpringBootApplication
	@EnableDiscoveryClient
	public class ConsulProviderApplication {
	
		public static void main(String[] args) {
			SpringApplication.run(ConsulProviderApplication.class, args);
		}
	}
	
	```

4. 服务消费者

	1. 在父 maven 项目下，创建一个新的 Spring Boot 服务消费者项目 `spring-boot-consul-consumer`
	
	2. 配置 pom.xml 文件，添加依赖

	```
	
	<dependencies>
	    <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>
	</dependencies>
	
	```
	
	3. 配置 application.yml 文件
	
	```
	
	spring:
	  cloud:
	    consul:
	      host: localhost
	      port: 8500
	      # 设置不需要注册到 consul 中
	      discovery:
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
	        return discoveryClient.getInstances("consul-provider");
	    }
	
	    /**
	     * 从所有服务中选择一个服务（轮询）
	     */
	    @RequestMapping("/choose")
	    public String choose() {
	        return loadBalancer.choose("consul-provider").getUri().toString();
	    }
	
	    @Resource
	    RestTemplate restTemplate;
	
	    @RequestMapping("/hello")
	    public String hello() {
	        return restTemplate.getForObject("http://consul-provider/hello", String.class);
	    }
	}
	
	```
	
	6. 编写启动类
	
	```
	
	@SpringBootApplication
	public class ConsulConsumerApplication {
	
		public static void main(String[] args) {
			SpringApplication.run(ConsulConsumerApplication.class, args);
		}
	}
	
	```

5. 先启动服务提供者，再启动服务消费者，调用接口，查看结果