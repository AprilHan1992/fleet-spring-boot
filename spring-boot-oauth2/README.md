# spring-boot-oauth2

> Oauth2 授权管理

## 步骤

1. 创建一个新的父 maven 项目
	
	1. 删除 src 文件夹
	
	2. 配置 pom.xml 文件
	
	```
	
	<modules>
        <module>spring-boot-oauth2-server</module>
        <module>spring-boot-oauth2-resource</module>
        <module>spring-boot-oauth2-client</module>
    </modules>
	
	```

2. 搭建授权服务器

	1. 在父 maven 项目下，创建一个新的 Spring Boot 授权服务器项目 `spring-boot-oauth2-server`
	
	2. 配置 pom.xml 文件，添加依赖

	```
	
	<dependencies>
	    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security.oauth</groupId>
            <artifactId>spring-security-oauth2</artifactId>
            <version>2.3.6.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
	</dependencies>
	
	```

	3. 配置 application.yml 文件，配置端口号
	
	```
	
	server:
	  port: 8000
	spring:
	  redis:
	    # redis数据库索引（默认为0）
	    database: 0
	    # redis服务器地址
	    host: localhost
	    # redis服务器连接端口
	    port: 6379
	    # redis服务器连接密码（默认为空）
	    password:
	    # 连接超时时间（毫秒）
	    timeout: 5000
	    jedis:
	      pool:
	        # 连接池最大连接数（使用负值表示没有限制）
	        max-active: 5000
	        # 连接池中的最大空闲连接
	        max-idle: 30
	        # 连接池中的最小空闲连接
	        min-idle: 5
	        # 连接池最大阻塞等待时间（使用负值表示没有限制）
	        max-wait: 3000
	
	```
	
	4. 新建配置文件 `WebSecurityConfig` 继承 `WebSecurityConfigurerAdapter`
	
	```
	
	@Configuration
	@EnableWebSecurity
	@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	    @Resource
	    private AuthenticationSuccess authenticationSuccess;
	
	    @Resource
	    private AuthenticationFailure authenticationFailure;
	
	    @Resource
	    private LogoutSuccess logoutSuccess;
	
	    @Resource
	    private AccessDenied accessDenied;
	
	    @Resource
	    private UserService userService;
	
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userService)
	                .passwordEncoder(new BCryptPasswordEncoder());
	    }
	
	    @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	                .anyRequest().authenticated()
	                .and()
	                .formLogin()
	                .loginProcessingUrl("/login").permitAll()
	                .usernameParameter("username")
	                .passwordParameter("password")
	                .successHandler(authenticationSuccess).permitAll()
	                .failureHandler(authenticationFailure).permitAll()
	                .and()
	                .logout()
	                .logoutUrl("/logout")
	                .logoutSuccessHandler(logoutSuccess).permitAll()
	                .and()
	                .exceptionHandling()
	                .accessDeniedHandler(accessDenied)
	                .and()
	                .httpBasic()
	                .and()
	                .csrf().disable();
	    }
	}
	
	```
	
	5. 新建配置文件 `AuthorizationServerConfig` 继承 `AuthorizationServerConfigurerAdapter`
	
	```
	
	@Configuration
	@EnableAuthorizationServer
	public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	    @Resource
	    @Qualifier("authenticationManagerBean")
	    private AuthenticationManager authenticationManager;
	
	    @Resource
	    private RedisConnectionFactory redisConnectionFactory;
	
	    @Bean
	    public TokenStore tokenStore() {
	        return new RedisTokenStore(redisConnectionFactory);
	    }
	
	    @Override
	    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	        security.allowFormAuthenticationForClients()
	                .passwordEncoder(new BCryptPasswordEncoder())
	                .tokenKeyAccess("permitAll()")
	                .checkTokenAccess("isAuthenticated()");
	    }
	
	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        clients.inMemory()
	                .withClient("client_id_1001")
	                .secret(new BCryptPasswordEncoder().encode("client_secret_1001"))
	                // 设置授权模式
	                .authorizedGrantTypes("authorization_code", "refresh_token")
	                .resourceIds("resource_id")
	                .scopes("app", "web")
	                // 设置 access_token 有效期为15分钟
	                .accessTokenValiditySeconds(900)
	                // 设置 refresh_token 有效期为30天
	                .refreshTokenValiditySeconds(2592000)
	                .redirectUris("http://localhost:8001/callback")
	                .and()
	                .withClient("client_id_1002")
	                .secret(new BCryptPasswordEncoder().encode("client_secret_1002"))
	                // 设置授权模式
	                .authorizedGrantTypes("implicit")
	                .resourceIds("resource_id")
	                .scopes("app", "web")
	                // 设置 access_token 有效期为30天
	                .accessTokenValiditySeconds(2592000)
	                .redirectUris("http://localhost:8001/callback")
	                .and()
	                .withClient("client_id_1003")
	                .secret(new BCryptPasswordEncoder().encode("client_secret_1003"))
	                // 设置授权模式
	                .authorizedGrantTypes("password", "refresh_token")
	                .resourceIds("resource_id")
	                .scopes("app", "web")
	                // 设置 access_token 有效期为15分钟
	                .accessTokenValiditySeconds(900)
	                // 设置 refresh_token 有效期为30天
	                .refreshTokenValiditySeconds(2592000);
	    }
	
	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
	        endpoints.authenticationManager(authenticationManager)
	                .tokenStore(tokenStore())
	                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
	    }
	}
	
	```
	
	6. 新建配置文件 `RedisConfig` 继承 `CachingConfigurerSupport`
	
	```

	@Configuration
	@EnableCaching
	public class RedisConfig extends CachingConfigurerSupport {
	
	    /**
	     * 实例化 RedisTemplate<String, Object> 对象
	     */
	    @Bean
	    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
	        RedisTemplate<String, Object> template = new RedisTemplate<>();
	        template.setConnectionFactory(connectionFactory);
	
	        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
	        // 使用StringRedisSerializer来序列化和反序列化redis的key
	        template.setKeySerializer(stringRedisSerializer);
	        template.setHashKeySerializer(stringRedisSerializer);
	
	        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
	        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
	        template.setValueSerializer(jackson2JsonRedisSerializer);
	        template.setHashValueSerializer(jackson2JsonRedisSerializer);
	
	        // 开启事务
	        template.setEnableTransactionSupport(true);
	        template.afterPropertiesSet();
	        return template;
	    }
	}
	
	```

	7. 创建 `UserService` 接口并继承 `UserDetailsService`
	
	```
	
	public interface UserService extends UserDetailsService {
	}
	
	```
	
	8. 实现 `UserService` 接口
	
	```

	@Component
	@Service
	public class UserServiceImpl implements UserService {
	
	    /**
	     * 模拟用户
	     *
	     * @param username
	     * @return
	     * @throws UsernameNotFoundException
	     */
	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        String password = new BCryptPasswordEncoder().encode("123456");
	        if (StringUtils.isEmpty(username)) {
	            return null;
	        }
	
	        // 放入角色项，需要加前缀 ROLE_ ，hasRole 校验时可以不加 ROLE_ 前缀
	        // 放入权限项，hasAuthority 校验时必须为权限项全字符串
	        List<GrantedAuthority> authorities = new ArrayList<>();
	        if ("user".equals(username)) {
	            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	            authorities.add(new SimpleGrantedAuthority("USER:GET"));
	        } else if ("admin".equals(username)) {
	            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	            authorities.add(new SimpleGrantedAuthority("USER:GET"));
	        } else {
	            return null;
	        }
	
	        return new User(username, password, authorities);
	    }
	}
	
	```

3. 搭建资源服务器

	1. 在父 maven 项目下，创建一个新的 Spring Boot 资源服务器项目 `spring-boot-oauth2-resource`
	
	2. 配置 pom.xml 文件，添加依赖

	```
	
	<dependencies>
	    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security.oauth</groupId>
            <artifactId>spring-security-oauth2</artifactId>
            <version>2.3.6.RELEASE</version>
        </dependency>
	</dependencies>
	
	```
	
	3. 配置 application.yml 文件，配置端口号
	
	```
	
	server:
	  port: 9000
	
	```
	
	4. 新建配置文件 `ResourceServerConfig` 继承 `ResourceServerConfigurerAdapter`
	
	```
	
	@Configuration
	@EnableResourceServer
	@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
	public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	    @Resource
	    private AccessDenied accessDenied;
	
	    @Resource
	    private AuthEntryPoint authEntryPoint;
	
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	    @Primary
	    @Bean
	    public RemoteTokenServices remoteTokenServices() {
	        final RemoteTokenServices tokenServices = new RemoteTokenServices();
	        tokenServices.setCheckTokenEndpointUrl("http://localhost:8000/oauth/check_token");
	        tokenServices.setClientId("client_id_1001");
	        tokenServices.setClientSecret("client_secret_1001");
	        return tokenServices;
	    }
	
	    @Override
	    public void configure(ResourceServerSecurityConfigurer resources) {
	        resources.resourceId("resource_id")
	                .stateless(true);
	
	        resources.authenticationEntryPoint(authEntryPoint);
	        resources.accessDeniedHandler(accessDenied);
	    }
	
	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	                .antMatchers("/guest/**").permitAll()
	                .antMatchers("/admin/**").hasRole("ADMIN")
	                .anyRequest().authenticated()
	                .and()
	                .httpBasic()
	                .and()
	                .csrf().disable();
	    }
	}
	
	```

	5. 编写接口类 `AdminController`
	
	```
	
	@RestController
	@RequestMapping("/admin")
	public class AdminController {
	
	    @RequestMapping("/hello")
	    public R hello() {
	        return R.ok("你好，ADMIN 角色用户！");
	    }
	}
	
	```

	6. 编写接口类 `UserController`
	
	```
	
	@RestController
	@RequestMapping("/user")
	public class UserController {
	
	    @PreAuthorize("hasRole('USER')")
	    @RequestMapping("/hello")
	    public R get() {
	        return R.ok("你好，USER 角色用户！");
	    }
	
	    @PreAuthorize("hasAuthority('USER:GET')")
	    @GetMapping("/get/{id}")
	    public R get(@PathVariable String id) {
	        return R.ok("id : " + id);
	    }
	}

	```

	7. 编写接口类 `GuestController`
	
	```
	
	@RestController
	@RequestMapping("/guest")
	public class GuestController {
	
	    @RequestMapping("/hello")
	    public R hello() {
	        return R.ok("你好，游客！");
	    }
	}

	```

4. 搭建客户端

	1. 在父 maven 项目下，创建一个新的 Spring Boot 客户端项目 `spring-boot-oauth2-client`
	
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

	4. 创建回调接口
	
	```
	
	@RestController
	public class CallbackController {
	
	    /**
	     * 回调
	     */
	    @GetMapping("/callback")
	    public String callback() {
	        return "回调成功";
	    }
	}
	
	```

5. 先启动授权服务器，再启动资源服务器，最后启动客户端，调用接口，查看结果

	1. 授权码模式（authorization code）
	
	由客户端的 client_id 与 response_type=code 拼接成 url `http://localhost:8000/oauth/authorize?response_type=code&client_id=client_id_1001&scope=app`，在浏览器中实现跳转，输入用户名和密码 `admin/123456` 或 `admin/123456`，成功跳转到 OAuth Approval 页面，Approve 表示接受授权，Deny 反之，确认授权后跳转到回调地址 `http://localhost:8001/callback?code=VNLuML`，获取授权码 `VNLuML`

	客户端 POST 请求 `http://localhost:8000/oauth/token` 地址，参数设置为 `grant_type=authorization_code`， `code=VNLuML`， `client_id=client_id_1001`， `client_secret=client_secret_1001`， `scope=app`， `redirect_uri=http://localhost:8001/callback`，获取到操作所用 token 信息
	
	```
	
	{
	    "access_token": "9de5b6e9-bef7-468e-a0d3-e56e2ede64e0",
	    "token_type": "bearer",
	    "refresh_token": "55493dda-d409-4c70-9b9f-2c9acb4327c2",
	    "expires_in": 899,
	    "scope": "app"
	}
	
	```

	调用接口查看 token 是否有效，测试用户是否有权限访问，接口如下
	`http://localhost:9000/user/hello?access_token=9de5b6e9-bef7-468e-a0d3-e56e2ede64e0`
	`http://localhost:9000/user/get/1?access_token=9de5b6e9-bef7-468e-a0d3-e56e2ede64e0`
	`http://localhost:9000/admin/hello?access_token=9de5b6e9-bef7-468e-a0d3-e56e2ede64e0`
	`http://localhost:9000/guest/hello`

	2. 简化模式（implicit）
	
	由客户端的 client_id 与 response_type=token 拼接成 url `http://localhost:8000/oauth/authorize?response_type=token&client_id=client_id_1002&scope=app`，在浏览器中实现跳转，输入用户名和密码 `admin/123456` 或 `admin/123456`，成功跳转到 OAuth Approval 页面，Approve 表示接受授权，Deny 反之，确认授权后跳转到回调地址 `http://localhost:8001/callback#access_token=728785b1-cf36-4872-945f-a0dee9b7017e&token_type=bearer&expires_in=882`，获取到操作所用 token 信息

	调用接口查看 token 是否有效，测试用户是否有权限访问，接口如下
	`http://localhost:9000/user/hello?access_token=728785b1-cf36-4872-945f-a0dee9b7017e`
	`http://localhost:9000/user/get/1?access_token=728785b1-cf36-4872-945f-a0dee9b7017e`
	`http://localhost:9000/admin/hello?access_token=728785b1-cf36-4872-945f-a0dee9b7017e`
	`http://localhost:9000/guest/hello`
	
	3. 密码模式（resource owner password credentials）
	
	客户端 POST 请求 `http://localhost:8000/oauth/token` 地址，参数设置为 `grant_type=password`， `username=admin`， `password=123456`， `client_id=client_id_1003`， `client_secret=client_secret_1003`， `scope=app`，获取到操作所用 token 信息
	
	```
	
	{
	    "access_token": "7f02201d-fa7c-4039-8472-80a307efc399",
	    "token_type": "bearer",
	    "refresh_token": "8e0f6ceb-22d1-44b8-be2c-14c63bc9b9cc",
	    "expires_in": 899,
	    "scope": "app"
	}
	
	```

	调用接口查看 token 是否有效，测试用户是否有权限访问，接口如下
	`http://localhost:9000/user/hello?access_token=7f02201d-fa7c-4039-8472-80a307efc399`
	`http://localhost:9000/user/get/1?access_token=7f02201d-fa7c-4039-8472-80a307efc399`
	`http://localhost:9000/admin/hello?access_token=7f02201d-fa7c-4039-8472-80a307efc399`
	`http://localhost:9000/guest/hello`

	4. 客户端模式（client credentials）
 
	自行查阅
