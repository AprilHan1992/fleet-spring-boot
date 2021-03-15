# spring-boot-eureka

> Eureka 微服务注册与发现

## 步骤

1. 创建一个新的父 maven 项目
	
	1. 删除 src 文件夹
	
	2. 配置 pom.xml 文件
	
	```
	
	<modules>
        <module>spring-boot-eureka-eureka</module>
        <module>spring-boot-eureka-provider</module>
        <module>spring-boot-eureka-consumer</module>
    </modules>
	
	```

2. 服务注册中心

	1. 在父 maven 项目下，创建一个新的 Spring Boot 服务注册中心项目 `spring-boot-eureka-eureka`
	
	2. 配置 pom.xml 文件，添加依赖

	```
	
	<dependencies>
	    <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
	</dependencies>
	
	```
	
	3. 配置 application.yml 文件
	
	```
	
	server:
	  port: 8761
	eureka:
	  instance:
	    hostname: localhost
	  client:
	    register-with-eureka: false
	    fetch-registry: false
	    service-url:
	      defaultZone: http://localhost:8761/eureka/
	    healthcheck:
	      enabled: true
	
	```
	
	4. 编写启动类
	
	```
	
	@SpringBootApplication
	@EnableEurekaServer
	public class EurekaEurekaApplication {
	
	    public static void main(String[] args) {
	        SpringApplication.run(EurekaEurekaApplication.class, args);
	    }
	}
	
	```

3. 服务提供者

	1. 在父 maven 项目下，创建一个新的 Spring Boot 服务提供者项目 `spring-boot-eureka-provider`
	
	2. 配置 pom.xml 文件，添加依赖

	```
	
	<dependencies>
	    <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
	</dependencies>
	
	```
	
	3. 配置 application.yml 文件
	
	```
	
	eureka:
	  client:
	    service-url:
	      defaultZone: http://localhost:8761/eureka/
	    healthcheck:
	      enabled: true
	  instance:
	    prefer-ip-address: true
	
	```
	
	4. 编写接口类
	
	```
	
	@RestController
	public class HelloController {
	
	    @RequestMapping("/hello")
	    public String hello() {
	        return "hello eureka";
	    }
	}
	
	```
	
	5. 编写启动类
	
	```
	
	@SpringBootApplication
	@EnableEurekaClient
	public class EurekaProviderApplication {
	
	    public static void main(String[] args) {
	        SpringApplication.run(EurekaProviderApplication.class, args);
	    }
	}
	
	```

4. 服务消费者

	1. 在父 maven 项目下，创建一个新的 Spring Boot 服务消费者项目 `spring-boot-eureka-consumer`
	
	2. 配置 pom.xml 文件，添加依赖

	```
	
	<dependencies>
	    <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
	</dependencies>
	
	```
	
	3. 配置 application.yml 文件
	
	```
	
	eureka:
	  client:
	    service-url:
	      defaultZone: http://localhost:8761/eureka/
	    healthcheck:
	      enabled: true
	  instance:
	    prefer-ip-address: true
	
	```
	
	4. 配置 RestTemplate Http 请求
	
	```
	
	/**
	 * Spring Cloud RestTemplate 支持 ip、域名、服务名 调用
	 */
	@Configuration
	public class RestTemplateConfig {
	
	    /**
	     * 加 @LoadBalanced 注解，支持 服务名 调用
	     */
	    @Bean
	    @LoadBalanced
	    public RestTemplate loadBalanced() {
	        return new RestTemplate();
	    }
	
	    /**
	     * 不加 @LoadBalanced 注解，支持 ip、域名 调用
	     */
	    @Bean
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
	        return discoveryClient.getInstances("eureka-provider");
	    }
	
	    /**
	     * 从所有服务中选择一个服务（轮询）
	     */
	    @RequestMapping("/choose")
	    public String choose() {
	        return loadBalancer.choose("eureka-provider").getUri().toString();
	    }
	
	    @Resource
	    RestTemplate loadBalanced;
	
	    @RequestMapping("/hello")
	    public String hello() {
	        return loadBalanced.getForObject("http://eureka-provider/hello", String.class);
	    }
	
	    @Resource
	    RestTemplate restTemplate;
	
	    @RequestMapping("hello1")
	    public String hello1() {
	        return restTemplate.getForObject(choose() + "/hello", String.class);
	    }
	
	    @RequestMapping("hello2")
	    public String hello2() {
	        return restTemplate.getForObject("http://localhost:8000/api/provider/hello", String.class);
	    }
	}
	
	```
	
	6. 编写启动类
	
	```
	
	@SpringBootApplication
	@EnableEurekaClient
	public class EurekaConsumerApplication {
	
	    public static void main(String[] args) {
	        SpringApplication.run(EurekaConsumerApplication.class, args);
	    }
	}
	
	```

5. 先启动服务注册中心，然后启动服务提供者，最后启动服务消费者，调用接口，查看结果
