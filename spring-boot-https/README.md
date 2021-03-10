# spring-boot-https

> HTTPS

## 步骤

1. 创建一个新的 Spring Boot 项目

2. 借助 Java 自带的 JDK 管理工具 keytool 来生成一个免费的 https 证书，并将生成的 https 证书复制到 resources 目录下

`keytool -genkey -alias tomcathttps -keyalg RSA -keystore ./server.keystore`

3. 配置 application.yml 文件

```

server:
  ssl:
    # 证书
    key-store: classpath:server.keystore
    # 密钥库密码
    key-store-password: fleetsoft
    key-store-type: JKS
    key-alias: tomcathttps
    enabled: true

```

4. 将HTTP请求重定向到HTTPS（可选）

```

@Configuration
public class HttpsConfig {

    /**
     * 强制 http(80) 跳转到 https(8000)
     */
    @Bean
    public Connector connector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(80);
        connector.setSecure(false);
        connector.setRedirectPort(8000);
        return connector;
    }

    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector) {
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcatServletWebServerFactory.addAdditionalTomcatConnectors(connector);
        return tomcatServletWebServerFactory;
    }
}

```
 
5. 运行主程序，在浏览器中打开 `https://localhost:8000/hello` 查看结果
