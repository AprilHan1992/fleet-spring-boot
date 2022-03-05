# spring-boot-sonar

> 代码质量管理

## 步骤

1. 搭建环境，下载 SonarQube 软件，解压软件

2. 进入 conf 目录，配置 sonar.properties 文件

```

sonar.jdbc.url=jdbc:mysql://localhost:3306/sonar?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&useConfigs=maxPerformance&useSSL=false
sonar.jdbc.username=root
sonar.jdbc.password=

```

3. 根据系统类型，进入 bin 目录，启动 Sonar

4. 启动成功后，访问 `http://localhost:9000` ,用管理员账号（admin/admin）登录

5. 创建一个新的 Spring Boot 项目

6. 配置 pom.xml 文件，添加依赖

```
<build>
    <plugins>
        <plugin>
            <groupId>org.sonarsource.scanner.maven</groupId>
            <artifactId>sonar-maven-plugin</artifactId>
            <version>3.9.0.2155</version>
        </plugin>
    </plugins>
</build>

```

7. 执行命令行 `mvn clean compile sonar:sonar`

8. 再次刷新 `http://localhost:9000` ,查看效果