# spring-boot-hello

> 快速入门 Hello world 示例

## 步骤

1. 创建一个新的 Spring Boot 项目

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

4. 编写接口类

```

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
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

6. 编写测试类

```

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloApplicationTests {

    private MockMvc mockMvc;

    @Resource
    private WebApplicationContext webApplicationContext;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void hello() throws Exception {
        String result = mockMvc.perform(get("/hello")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(result);
    }
}

```
 
7. 运行主程序或测试程序，查看结果
