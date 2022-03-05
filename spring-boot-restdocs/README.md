# spring-boot-restdocs

> Restdocs 自动生成接口文档

## 步骤

1. 创建一个新的 Spring Boot 项目

2. 配置 pom.xml 文件，添加依赖

```

<dependencies>
    <dependency>
        <groupId>org.springframework.restdocs</groupId>
        <artifactId>spring-restdocs-mockmvc</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.asciidoctor</groupId>
            <artifactId>asciidoctor-maven-plugin</artifactId>
            <version>2.0.0</version>
            <executions>
                <execution>
                    <id>generate-docs</id>
                    <phase>prepare-package</phase>
                    <goals>
                        <goal>process-asciidoc</goal>
                    </goals>
                    <configuration>
                        <backend>html</backend>
                        <doctype>book</doctype>
                        <attributes>
                            <snippets>${project.build.directory}/generated-snippets</snippets>
                        </attributes>
                    </configuration>
                </execution>
            </executions>
            <dependencies>
                <dependency>
                    <groupId>org.springframework.restdocs</groupId>
                    <artifactId>spring-restdocs-asciidoctor</artifactId>
                    <version>${spring-restdocs.version}</version>
                </dependency>
            </dependencies>
        </plugin>
    </plugins>
</build>

```

3. 编写接口类

```

@RestController
@RequestMapping("/user")
public class UserController {

    @CrossOrigin
    @RequestMapping(value = "/insert")
    public boolean insert(@RequestBody User user) {
        return true;
    }

    @RequestMapping(value = "/delete")
    public boolean delete(@RequestParam Long id) {
        return true;
    }

    @CrossOrigin
    @RequestMapping(value = "/update")
    public boolean update(@RequestBody User user) {
        return true;
    }

    @CrossOrigin
    @RequestMapping(value = "/get")
    public User get(@RequestParam Long id) {
        User user = new User();
        user.setId(1L);
        user.setName("fleet");
        return user;
    }
}

```

4. 编写测试类

```

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTests {

    public MockMvc mockMvc;

    @Resource
    private WebApplicationContext webApplicationContext;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
    }

    @Test
    public void test() {
    }
}

```

5. 编写 UserController 单元测试类

```

public class UserControllerTests extends BaseTests {

    private static final ResponseFieldsSnippet responseFieldsSnippet = responseFields(
            fieldWithPath("id").description("用户id"),
            fieldWithPath("name").description("用户名")
    );

    @Test
    public void insertUser() throws Exception {
        User user = new User();
        user.setName("fleet");
        super.mockMvc.perform(post("/user/insert").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(user)))
                .andExpect(status().isOk())
                .andDo(document("insert"));
    }

    @Test
    public void deleteUser() throws Exception {
        super.mockMvc.perform(get("/user/delete?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("delete"));
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        super.mockMvc.perform(post("/user/update").contentType(MediaType.APPLICATION_JSON).param("name", "fleet").content(JSON.toJSONString(user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("update"));
    }

    @Test
    public void getUser() throws Exception {
        super.mockMvc.perform(get("/user/get").param("id", "1"))
                .andExpect(status().isOk())
                .andDo(document("get", responseFieldsSnippet));
    }
}

```

6. 运行单元测试后，会在 target/generated-snippets 生成相应的 .adoc 文件

7. 在 src/main 下新建 asciidoc 目录，新建 index.adoc 文件，并编写文件

```

= 用 Spring REST Docs 构建文档

This is an example output for a service running at http://localhost:8000

== 用户接口

=== 新增用户

.http-request
include::{snippets}/insert/http-request.adoc[]
.http-response
include::{snippets}/insert/http-response.adoc[]

=== 查询用户

.http-request
include::{snippets}/get/http-request.adoc[]
.http-response
include::{snippets}/get/http-response.adoc[]

```

8. 执行 `mvn package` 命令打包后, 会在 /target/generated-docs 生成相应的 index.html 文档
