# spring-boot-axis2

> Axis2 实现 WebService

## 步骤

1. 创建一个新的父 maven 项目
	
	1. 删除 src 文件夹
	
	2. 配置 pom.xml 文件
	
	```
	
	<modules>
        <module>spring-boot-axis2-services</module>
        <module>spring-boot-axis2-client</module>
    </modules>
	
	```

2. 服务提供者
	
	1. 在父 maven 项目下，创建一个新的 Spring Boot 服务提供者项目 `spring-boot-axis2-services`
	
	2. 配置 pom.xml 文件，添加依赖

	```
	
	<dependencies>
	    <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-spring</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-ant-plugin</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-corba</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-jaxws</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-transport-http</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-transport-local</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-xmlbeans</artifactId>
            <version>1.7.9</version>
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
	
	    User get(@WebParam(name = "id") Long id);
	
	    String getName(@WebParam(name = "id") Long id);
	}
	
	@Service
	@WebService
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
	public class Axis2Config {
	
	    @Bean
	    public ServletRegistrationBean<AxisServlet> axisServlet() {
	        ServletRegistrationBean<AxisServlet> axisServlet = new ServletRegistrationBean<>(new AxisServlet(), "/services/*");
	        String path = this.getClass().getResource("/WEB-INF").getPath();
	        axisServlet.addInitParameter("axis2.repository.path", path);
	        axisServlet.setLoadOnStartup(1);
	        return axisServlet;
	    }
	}
	
	```

	6. 在 resources 目录下创建 WEB-INF/services/config/META-INF 文件，配置 services.xml
	
	```
	
	<?xml version="1.0" encoding="UTF-8"?>
	<serviceGroup>
	    <service name="userService" targetNamespace="http://services.axis2.fleet.com"> 
	        <description>Axis2 实现 WebService</description>
	        <parameter name="ServiceClass">com.fleet.axis2.services.service.impl.UserServiceImpl</parameter>
	        <schema schemaNamespace="http://services.axis2.fleet.com"/>
	        <operation name="get">       
	            <messageReceiver class="org.apache.axis2.rpc.receivers.RPCMessageReceiver"/> 
	        </operation>
	        <operation name="getName">
	            <messageReceiver class="org.apache.axis2.rpc.receivers.RPCMessageReceiver"/>
	        </operation>
	        <messageReceivers>
	            <messageReceiver mep="http://www.w3.org/ns/wsdl/in-only"
	                             class="org.apache.axis2.rpc.receivers.RPCInOnlyMessageReceiver"/>
	            <messageReceiver mep="http://www.w3.org/ns/wsdl/in-out"
	                             class="org.apache.axis2.rpc.receivers.RPCMessageReceiver"/>
	        </messageReceivers>
	    </service>
	</serviceGroup>
	
	```

	7. 以上配置成功后，启动 SpringbootApplication
	
	8. 访问 service 的 url：http://127.0.0.1:8001/services/userService?wsdl，查看效果
	
	9. 编写单元测试类，查看接口是否能访问成功
	
	```
	
	@Test
    public void get() throws Exception {
        RPCServiceClient rpcServiceClient = new RPCServiceClient();
        Options options = rpcServiceClient.getOptions();
        EndpointReference endpointReference = new EndpointReference("http://localhost:8001/services/userService");
        options.setTo(endpointReference);
        QName opName = new QName("http://services.axis2.fleet.com", "get");
        Object[] args = new Object[]{1};
        Class[] returnTypes = new Class[]{User.class};
        Object[] objects = rpcServiceClient.invokeBlocking(opName, args, returnTypes);
        System.out.println("查询到用户:" + JSON.toJSONString(objects[0]));
    }

    @Test
    public void getName() throws Exception {
        RPCServiceClient rpcServiceClient = new RPCServiceClient();
        Options options = rpcServiceClient.getOptions();
        EndpointReference endpointReference = new EndpointReference("http://localhost:8001/services/userService");
        options.setTo(endpointReference);
        QName opName = new QName("http://services.axis2.fleet.com", "getName");
        Object[] args = new Object[]{1};
        Class[] returnTypes = new Class[]{String.class};
        Object[] objects = rpcServiceClient.invokeBlocking(opName, args, returnTypes);
        System.out.println("查询到用户名:" + objects[0]);
    }
	
	```
	
3. 客户端

	1. 环境搭建。下载 axis2-1.7.9，并在本地新增环境变量

	```

	新增环境变量
	变量名：AXIS2_HOME
	变量值：D:\axis2-1.7.9
	Path 增加：%AXIS2_HOME%\bin

	```

	2. 在父 maven 项目下，创建一个新的 Spring Boot 客户端项目 `spring-boot-axis2-client`
	
	3. 配置 pom.xml 文件
	
	```
	
	<dependencies>
	    <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-spring</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-ant-plugin</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-corba</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-jaxws</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-transport-http</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-transport-local</artifactId>
            <version>1.7.9</version>
        </dependency>
        <dependency>
            <groupId>org.apache.axis2</groupId>
            <artifactId>axis2-xmlbeans</artifactId>
            <version>1.7.9</version>
        </dependency>
	</dependencies>
	
	```

	4. 命令行 `wsdl2java -uri http://127.0.0.1:8001/services/userService?wsdl -p com.fleet.axis2.client.service -o D:\service` 生成服务，复制生成的文件到创建的项目中

	5. 编写单元测试类，查看接口是否能访问成功
	
	```
	
	@Test
    public void get() throws Exception {
        UserServiceStub.Get get = new UserServiceStub.Get();
        get.setId(1L);
        UserServiceStub userServiceStub = new UserServiceStub();
        GetResponseHandler getResponseHandler = new GetResponseHandler();
        UserServiceStub.GetResponse getResponse = userServiceStub.get(get);
        getResponseHandler.receiveResultGet(getResponse);
        userServiceStub.startget(get, getResponseHandler);
        UserServiceStub.User user = getResponse.get_return();
        System.out.println("查询到用户:" + JSON.toJSONString(user));
    }

    static class GetResponseHandler extends UserServiceCallbackHandler {
        public void receiveResultGet(UserServiceStub.GetResponse result) {
            super.receiveResultget(result);
        }
    }

    @Test
    public void getName() throws Exception {
        UserServiceStub.GetName getName = new UserServiceStub.GetName();
        getName.setId(1L);
        UserServiceStub userServiceStub = new UserServiceStub();
        GetNameResponseHandler getNameResponseHandler = new GetNameResponseHandler();
        UserServiceStub.GetNameResponse getNameResponse = userServiceStub.getName(getName);
        getNameResponseHandler.receiveResultgetName(getNameResponse);
        userServiceStub.startgetName(getName, getNameResponseHandler);
        String name = getNameResponse.get_return();
        System.out.println("查询到用户:" + name);
    }

    static class GetNameResponseHandler extends UserServiceCallbackHandler {
        public void receiveResultgetName(UserServiceStub.GetNameResponse result) {
            super.receiveResultgetName(result);
        }
    }
	
	```
