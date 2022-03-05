# spring-boot-https

> HTTPS

## 步骤

1. 创建一个新的 Spring Boot 项目

2. 借助 Java 自带的 JDK 管理工具 keytool 来生成一个免费的 https 证书，并将生成的 https 证书复制到 resources 目录下

`keytool -genkeypair -alias tomcat -keyalg RSA -keysize 1024 -sigalg SHA256withRSA -dname "CN=fleet,OU=fleet,O=fleet,L=ShenZhen,ST=GuangDong,C=China" -keypass fleetsoft -validity 365 -storetype JKS -keystore ./localhost.keystore -storepass fleetsoft `

```

属性说明：
	-alias 别名
	-keyalg 密钥算法（如 RSA）
	-keysize 密钥位大小
	-sigalg 签名算法
	-dname 唯一判别名
	-keypass 密钥口令
	-validity 有效天数
	-storetype 密钥库类型
	-keystore 密钥库名称
	-storepass 密钥库口令

```

3. 配置 application.yml 文件

```

server:
  ssl:
    # 开启 https
    enabled: true
    # 证书
    key-store: classpath:localhost.keystore
    key-store-type: JKS
    # 别名
    key-alias: tomcat
    # 密码
    key-password: fleetsoft
    key-store-password: fleetsoft

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
