# spring-boot-jndi

> JNDI 数据源

## 步骤

1. 修改 Tomcat `conf/server.xml` 配置，在 `<GlobalNamingResources></GlobalNamingResources>` 标签中配置数据源

```

<Resource 
	name="jdbc/testDB"
	url="jdbc:mysql://localhost:3306/fleet-test?serverTimezone=GMT%2B8&amp;useUnicode=true&amp;characterEncoding=utf8"
	username="root"
	password=""
	type="javax.sql.DataSource"
	auth="Container"
	driverClassName="com.mysql.jdbc.Driver" 
	maxActive="30" 
    maxIdle="30" 
    maxWait="10000"
	/>

```

2. 修改 Tomcat `conf/context.xml` 配置，在 `<Context></Context>` 标签中映射数据源

```

<ResourceLink global="jdbc/testDB" name="jdbc/testDB" type="javax.sql.DataSource"/>

```

3. 创建一个新的 Spring Boot 项目

4. 配置 pom.xml 文件，设置打包成 war 包，添加依赖

```

<packaging>war</packaging>

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
	<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>

<build>
    <finalName>jndi</finalName>
</build>

```

5. 配置 application.yml 文件，配置数据源

```

spring:
  datasource:
    jndi-name: jdbc/testDB

```

6. 添加 ServletWebServerFactory 配置

```

@Configuration
public class DataSourceConfig {

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {

        return new TomcatServletWebServerFactory() {
            @Override
            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
                tomcat.enableNaming();
                return super.getTomcatWebServer(tomcat);
            }

            @Override
            protected void postProcessContext(Context context) {
                ContextResource resource = new ContextResource();
                resource.setName("jdbc/testDB");
                resource.setType(DataSource.class.getName());
                resource.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
                resource.setProperty("url", "jdbc:mysql://localhost:3306/fleet-test?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8");
                resource.setProperty("username", "root");
                resource.setProperty("password", "");
                context.getNamingResources().addResource(resource);

                super.postProcessContext(context);
            }
        };
    }

    @Bean
    public DataSource dataSource() throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName("java:/comp/env/jdbc/testDB");
        bean.setProxyInterface(DataSource.class);
        bean.setLookupOnStartup(false);
        bean.afterPropertiesSet();
        return (DataSource) bean.getObject();
    }
}

```

7. 编写服务层

```

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 具名参数
     */
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int insert(User user) {
//        // 方式一
//        String sql = "insert into `user`(`name`) values (?)";
//        return jdbcTemplate.update(sql, user.getName());

//        // 方式二
//        String sql = "insert into `user`(`name`) values (?)";
//        return jdbcTemplate.update(sql, new Object[]{user.getName()});

//        // 方式三
//        String sql = "insert into `user`(`name`) values (:name)";
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", user.getName());
//        return namedParameterJdbcTemplate.update(sql, params);

//        // 方式四
//        String sql = "insert into `user`(`name`) values (:name)";
//        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
//        return namedParameterJdbcTemplate.update(sql, paramSource);

        // 方式五（获取新增主键）
        String sql = "insert into `user`(`name`) values (:name)";
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int c = namedParameterJdbcTemplate.update(sql, paramSource, generatedKeyHolder);
        int id = Objects.requireNonNull(generatedKeyHolder.getKey()).intValue();
        logger.info("新增主键：" + id);
        return c;
    }

    @Override
    public int delete(Long id) {
        // 方式一
        String sql = "delete from `user` where `id` = ?";
        return jdbcTemplate.update(sql, id);

//        // 方式二
//        String sql = "delete from `user` where `id` = ?";
//        return jdbcTemplate.update(sql, new Object[]{id});
    }

    @Override
    public int[] deletes(Long[] ids) {
        List<Object[]> batchArgs = new ArrayList<>();
        for (Long id : ids) {
            batchArgs.add(new Object[]{id});
        }
        String sql = "delete from `user` where `id` = ?";
        return jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    @Override
    public int update(User user) {
        String sql = "update `user` set `name` = ? where `id` = ?";
        return jdbcTemplate.update(sql, user.getName(), user.getId());
    }

    @Override
    public User get(Long id) {
        String sql = "select `id`, `name` from `user` where `id` = ?";
        BeanPropertyRowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(sql, mapper, id);
    }

    @Override
    public List<User> list() {
        String sql = "select `id`, `name` from `user`";
        BeanPropertyRowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public int count() {
        String sql = "select count(`id`) from `user`";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}

```

8. 编写接口层

```

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/insert")
    public void insert(@RequestBody User user) {
        userService.insert(user);
    }

    @RequestMapping("/delete")
    public void delete(Long id) {
        userService.delete(id);
    }

    @RequestMapping("/update")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping("/get")
    public User get(Long id) {
        return userService.get(id);
    }

    @RequestMapping("/list")
    public List<User> list() {
        return userService.list();
    }

    @RequestMapping("/count")
    public int count() {
        return userService.count();
    }
}

```

9. 编写启动类

```

@SpringBootApplication
public class JndiApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(JndiApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(JndiApplication.class, args);
    }
}

```
 
10. 打成 war 包，并部署在 Tomcat 服务器下，查看效果
