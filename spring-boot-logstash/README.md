# spring-boot-logstash

> 使用 logstash 日志管理

## 环境搭建

1. 下载安装 jdk1.8 

2. 下载安装 logstash，下载地址： [https://artifacts.elastic.co/downloads/logstash/logstash-7.6.0.tar.gz](https://artifacts.elastic.co/downloads/logstash/logstash-7.6.0.tar.gz "logstash-7.6.0")

3. 配置 config/logstash.yml

```

input {
	tcp {
		host => "127.0.0.1"
		port => 4567
		mode => "server"
		codec => json_lines
	}
}

output {
    stdout {
		codec => rubydebug
	}
}

```

4. dos命令进入 logstash 安装目录下，启动 logstash 

```

cd bin

```

```

logstash -f ../config/logstash.conf

```

5. 配置 logback-spring.xml

```

<appender name="logstash" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
    <destination>127.0.0.1:4567</destination>
    <encoder charset="UTF-8" class="net.logstash.logback.encoder.LogstashEncoder"/>
</appender>

<springProfile name="dev">
    <root level="debug">
        <appender-ref ref="logstash"/>
    </root>
</springProfile>
<springProfile name="pro">
    <root level="error">
        <appender-ref ref="logstash"/>
    </root>
</springProfile>

```

6. 启动项目，调用 [http://localhost:8000/log](http://localhost:8000/log) 接口，可在 dos 窗口查看输出日志

```

{
        "message" => "******debug******",
    "logger_name" => "com.fleet.logstash.controller.TestController",
           "port" => 57395,
    "level_value" => 10000,
    "thread_name" => "http-nio-8080-exec-1",
           "host" => "127.0.0.1",
     "@timestamp" => 2020-06-20T01:41:22.867Z,
       "@version" => "1",
          "level" => "DEBUG"
}
{
        "message" => "******info******",
    "logger_name" => "com.fleet.logstash.controller.TestController",
           "port" => 57395,
    "level_value" => 20000,
    "thread_name" => "http-nio-8080-exec-1",
           "host" => "127.0.0.1",
     "@timestamp" => 2020-06-20T01:41:22.867Z,
       "@version" => "1",
          "level" => "INFO"
}
{
        "message" => "******warn******",
    "logger_name" => "com.fleet.logstash.controller.TestController",
           "port" => 57395,
    "level_value" => 30000,
    "thread_name" => "http-nio-8080-exec-1",
           "host" => "127.0.0.1",
     "@timestamp" => 2020-06-20T01:41:22.868Z,
       "@version" => "1",
          "level" => "WARN"
}
{
        "message" => "******error******",
    "logger_name" => "com.fleet.logstash.controller.TestController",
           "port" => 57395,
    "level_value" => 40000,
    "thread_name" => "http-nio-8080-exec-1",
           "host" => "127.0.0.1",
     "@timestamp" => 2020-06-20T01:41:22.868Z,
       "@version" => "1",
          "level" => "ERROR"
}

```
