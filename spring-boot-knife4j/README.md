# spring-boot-knife4j

> Knife4j 自动生成接口文档

## 步骤

1. 创建一个新的 Spring Boot 项目

2. 配置 pom.xml 文件，添加依赖

```

<dependencies>
    <dependency>
        <groupId>com.github.xiaoymin</groupId>
        <artifactId>knife4j-spring-boot-starter</artifactId>
        <version>2.0.4</version>
    </dependency>
</dependencies>

```

3. 新增一个 knife4j 的配置类，这个配置类和使用 swagger 几乎是一样的

```

@Configuration
@EnableSwagger2
@EnableKnife4j
public class Knife4jConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("用户模块").select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("接口文档").description("接口文档").version("1.0").build();
    }
}

```

4. 配置接口 controller 和 业务数据模型

```

@Api(tags = "用户相关api")
@RestController
public class UserController {

    @ApiOperation("添加用户信息")
    @ApiImplicitParam(name = "user", value = "User对象", required = true)
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public boolean insert(@RequestBody User user) {
        return true;
    }

    @ApiOperation("根据用户id删除用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public boolean delete(@RequestParam Long id) {
        return true;
    }

    @ApiOperation("修改用户信息")
    @ApiImplicitParam(name = "user", value = "User对象", required = true)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public boolean update(@RequestBody User user) {
        return true;
    }

    @ApiOperation("根据用户id查询用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public User get(@RequestParam Long id) {
        User user = new User();
        user.setId(1L);
        user.setName("fleet");
        return user;
    }
}

```

```

@ApiModel(value = "用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "用户名", required = true, example = "fleet")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

```

5. 运行主程序，访问地址 [http://localhost:8000/doc.html](http://localhost:8000/doc.html)，查看结果
