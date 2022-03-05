# spring-boot-ws

> 实现 WebService

## 步骤

1. 创建一个新的父 maven 项目
	
	1. 删除 src 文件夹
	
	2. 配置 pom.xml 文件
	
	```
	
	<modules>
        <module>spring-boot-ws-services</module>
        <module>spring-boot-ws-client</module>
    </modules>
	
	```

2. 服务提供者
	
	1. 在父 maven 项目下，创建一个新的 Spring Boot 服务提供者项目 `spring-boot-ws-services`
	
	2. 配置 pom.xml 文件，添加依赖

	```
	
	<dependencies>
	    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web-services</artifactId>
        </dependency>
	</dependencies>
	
	```
	
	3. 配置 application.yml 文件
	
	```
	
	server:
  		port: 8001
	
	```
	
	4. 编写服务
	
	```
	
	public interface UserService {

	    User get(Long id);
	
	    String getName(Long id);
	}
	
	@Service
	@WebService(name = "UserService", serviceName = "UserServiceImpl", targetNamespace = "http://services.ws.fleet.com")
	public class UserServiceImpl implements UserService {
	
	    private Map<Long, User> users = new HashMap<>();
	
	    public UserServiceImpl() {
	        User user = new User();
	        user.setId(1L);
	        user.setName("fleet-1");
	        users.put(user.getId(), user);
	
	        user = new User();
	        user.setId(2L);
	        user.setName("fleet-2");
	        users.put(user.getId(), user);
	
	        user = new User();
	        user.setId(3L);
	        user.setName("fleet-3");
	        users.put(user.getId(), user);
	    }
	
	    @Override
	    public User get(Long id) {
	        return users.get(id);
	    }
	
	    @Override
	    public String getName(Long id) {
	        return users.get(id).getName();
	    }
	}
	
	```
	
	5. 配置服务
	
	```
	
	@Configuration
	public class WSConfig {
	
	    @Resource
	    UserService userService;
	
	    @Bean
	    public Endpoint endpoint() {
	        return Endpoint.publish("http://127.0.0.1:8002/services/userService", userService);
	    }
	}
	
	```

	6. 以上配置成功后，启动 SpringbootApplication
	
	7. 访问 service 的 url：http://127.0.0.1:8002/services/userService?wsdl，查看效果
	
3. 客户端

	1. 在父 maven 项目下，创建一个新的 Spring Boot 客户端项目 `spring-boot-ws-client`

	2. 配置 pom.xml 文件
	
	```
	
	<dependencies>
	    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web-services</artifactId>
        </dependency>
	</dependencies>
	
	```
	
	3. 命令行进入 `spring-boot-ws-client` 目录，输入命令 `wsimport -s .\src\main\java -p com.fleet.ws.client.service -keep http://127.0.0.1:8002/services/userService?wsdl` 生成服务

	4. 编写单元测试类，查看接口是否能访问成功
	
	```
	
	@Test
    public void get() {
        UserService userService = new UserServiceImpl().getUserServicePort();
        User user = userService.get(1L);
        System.out.println("查询到用户:" + JSON.toJSONString(user));
    }

    @Test
    public void getName() {
        UserService userService = new UserServiceImpl().getUserServicePort();
        String name = userService.getName(1L);
        System.out.println("查询到用户名:" + name);
    }
	
	```
