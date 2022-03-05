# spring-boot-netty

> 简单的服务提供者、消费者

## 步骤

1. 创建一个新的父 maven 项目
	
	1. 删除 src 文件夹
	
	2. 配置 pom.xml 文件
	
	```
	
	<modules>
		<module>spring-boot-netty-provider</module>
        <module>spring-boot-netty-consumer</module>
    </modules>
	
	```

2. 服务提供者

	1. 在父 maven 项目下，创建一个新的 Spring Boot 服务提供者项目 `spring-boot-netty-provider`
	
	2. 配置 pom.xml 文件，添加依赖

	```
	
	<dependencies>
	    <dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-web</artifactId>
	    </dependency>
		<dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-test</artifactId>
	        <scope>test</scope>
	    </dependency>
	</dependencies>
	
	```
	
	3. 配置 application.yml 文件，配置端口号
	
	```
	
	server:
	  port: 8001
	
	```
	
	4. 编写接口类
	
	```
	
	@RestController
	public class HelloController {
	
	    @RequestMapping("/hello")
	    public String hello() {
	        return "hello netty";
	    }
	}
	
	```
	
	5. 编写启动类
	
	```
	
	@SpringBootApplication
	public class HelloApplication {
	
	    public static void main(String[] args) {
	        SpringApplication.run(HelloApplication.class, args);
	    }
	}
	
	```

3. 服务消费者

	1. 在父 maven 项目下，创建一个新的 Spring Boot 服务消费者项目 `spring-boot-netty-consumer`
	
	2. 配置 pom.xml 文件，添加依赖

	```
	
	<dependencies>
	    <dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-web</artifactId>
	    </dependency>
		<dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-test</artifactId>
	        <scope>test</scope>
	    </dependency>
	</dependencies>
	
	```
	
	3. 配置 application.yml 文件，配置端口号
	
	```
	
	server:
	  port: 8000
	
	```
	
	4. 配置 RestTemplate Http 请求
	
	```
	
	@Configuration
	public class RestTemplateConfig {
	
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
	    RestTemplate restTemplate;
	
	    @RequestMapping("/hello")
	    public String hello() {
	        return restTemplate.getForObject("http://localhost:8001/hello", String.class);
	    }
	}
	
	```
	
	6. 编写启动类
	
	```
	
	@SpringBootApplication
	public class HelloApplication {
	
	    public static void main(String[] args) {
	        SpringApplication.run(HelloApplication.class, args);
	    }
	}
	
	```

4. 先启动服务提供者，再启动服务消费者，调用接口，查看结果
