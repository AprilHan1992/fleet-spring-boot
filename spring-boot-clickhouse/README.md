# spring-boot-clickhouse

> ClickHouse 数据库

## 步骤

1. 搭建本地测试环境
	
	1.1. docker 安装。根据自己电脑版本，安装对应版本的 docker。然后启动 docker。

	1.2. 安装 ClickHouse

		1.2.1. 拉取 clickhouse 的 docker 镜像
		
		```
		docker pull yandex/clickhouse-server
		docker pull yandex/clickhouse-clinet

		```

		1.2.2. 创建并启动 server 端

		```
		# 默认直接启动即可
		docker run -d --name [启动之后的名称] --ulimit nofile=262144:262144 -p 8123:8123 -p 9000:9000 -p 9009:9009  yandex/clickhouse-server

		例如： docker run -d --name ch-server --ulimit nofile=262144:262144 -p 8123:8123 -p 9000:9000 -p 9009:9009 yandex/clickhouse-server

		```

		1.2.3. `docker ps -a` 查看容器

		1.2.4. `docker exec -it ch-server /bin/bash` 进入容器

		1.2.5. 输入 `clickhouse-client` 进入 clickhouse 命令行

		1.2.6. `show databases;` 查看所有的数据库

		1.2.7. 创建表，插入数据

		```
		create table user(id Int32, name String) engine = Memory;

		insert into user(id,name) values(1, 'fleet');

		```

2. 创建一个新的 Spring Boot 项目

3. 配置 pom.xml 文件，添加依赖

```

<dependencies>
    <dependency>
        <groupId>ru.yandex.clickhouse</groupId>
        <artifactId>clickhouse-jdbc</artifactId>
        <version>0.2.6</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.0.1</version>
    </dependency>
</dependencies>

```

4. 配置 application.yml 文件，配置端口号

```

server:
  port: 8000
clickhouse:
  url: jdbc:clickhouse://192.168.99.100:8123
  properties:
    database: default
    user: default
    password:

```

5. 参数配置

```

@Configuration
@ConfigurationProperties("clickhouse")
public class ClickHouseConfig {

    private String url;

    private Properties properties;

    @Bean(name = "clickHouseProperties")
    public ClickHouseProperties clickHouseProperties() {
        ClickHouseProperties clickHouseProperties = new ClickHouseProperties();
        clickHouseProperties.setDatabase(properties.getDatabase());
        clickHouseProperties.setUser(properties.getUser());
        clickHouseProperties.setPassword(properties.getPassword());
        return clickHouseProperties;
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(ClickHouseProperties clickHouseProperties) {
        return new ClickHouseDataSource(url, clickHouseProperties);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static class Properties {

        private String database;

        private String user;

        private String password;

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

```

6. 创建 Mapper 文件

```

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.fleet.clickhouse.dao.UserDao">
    <resultMap id="BaseResultMap" type="com.fleet.clickhouse.entity.User">
        <id column="id" property="id"/>
        <result column="name" property="name"/>
    </resultMap>

    <sql id="Base_Column_List">
		id, name
	</sql>

    <insert id="insert" parameterType="com.fleet.clickhouse.entity.User">
        insert into user (
        <trim suffixOverrides=",">
            <if test="id != null">id,</if>
            <if test="name != null">name,</if>
        </trim>
        ) values (
        <trim suffixOverrides=",">
            <if test="id != null">#{id, jdbcType=INTEGER},</if>
            <if test="name != null">#{name, jdbcType=VARCHAR},</if>
        </trim>
        )
    </insert>

    <select id="get" parameterType="java.lang.Long" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from user where id = #{id}
    </select>

    <select id="list" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List"/>
        from user
        <where>
            <if test="id != null">and id = #{id}</if>
            <if test="name != null">and name = #{name}</if>
        </where>
    </select>
</mapper>

```

7. 编写 Dao 文件

```

@Mapper
@Repository
public interface UserDao {

    int insert(User user);

    User get(Long id);

    List<User> list(Map<String, Object> map);
}

```

8. 编写 Service 文件

```

public interface UserService {

    int insert(User user);

    User get(Long id);

    List<User> list(Map<String, Object> map);
}

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public List<User> list(Map<String, Object> map) {
        return userDao.list(map);
    }
}

```
 
9. 编写 Controller 文件

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

    @RequestMapping("/get")
    public User get(Long id) {
        return userService.get(id);
    }

    @RequestMapping("/list")
    public List<User> list(Map<String, Object> map) {
        return userService.list(map);
    }
}

```

10. 运行主程序或测试程序，查看结果
