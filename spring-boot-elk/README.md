# spring-boot-elk

> 使用 elk 日志收集

## 环境搭建

1. 下载安装 jdk1.8 

2. 下载安装 elasticsearch，下载地址：[https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.6.0-windows-x86_64.zip](https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-7.6.0-windows-x86_64.zip "elasticsearch-7.6.0")

2. 下载安装 logstash，下载地址： [https://artifacts.elastic.co/downloads/logstash/logstash-7.6.0.tar.gz](https://artifacts.elastic.co/downloads/logstash/logstash-7.6.0.tar.gz "logstash-7.6.0")

3. 下载安装 kibana，下载地址： [https://artifacts.elastic.co/downloads/kibana/kibana-7.6.0-windows-x86_64.zip](https://artifacts.elastic.co/downloads/kibana/kibana-7.6.0-windows-x86_64.zip "kibana-7.6.0")
 
4. dos命令进入 elasticsearch 安装目录下，启动 elasticsearch 

```

cd bin

```

```

elasticsearch.bat

```

启动没有报错，在浏览器输入 [http://localhost:9200](http://localhost:9200) ，显示信息表示启动成功


```

{
	"name" : "node-1",
	"cluster_name" : "custom-es",
	"cluster_uuid" : "ZyeuhIZdQzmhu7v4obPzSg",
	"version" : {
	    "number" : "7.6.0",
	    "build_flavor" : "default",
	    "build_type" : "zip",
	    "build_hash" : "22e1767283e61a198cb4db791ea66e3f11ab9910",
	    "build_date" : "2019-09-27T08:36:48.569419Z",
	    "build_snapshot" : false,
	    "lucene_version" : "8.2.0",
	    "minimum_wire_compatibility_version" : "6.8.0",
	    "minimum_index_compatibility_version" : "6.0.0-beta1"
  	},
  	"tagline" : "You Know, for Search"
}

```

5. 配置 config/logstash.conf

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
    elasticsearch {
		hosts => ["http://127.0.0.1:9200"]
		index => "logstash-%{+YYYY.MM.dd}"
	}
    stdout { 
		codec => rubydebug
	}
}

```

6. dos命令进入 logstash 安装目录下，启动 logstash 

```

cd bin

```

```

logstash -f ../config/logstash.conf

```

7. 配置 config/kibana.yml

```

server.port: 5601
server.host: "127.0.0.1"
elasticsearch.hosts: ["http://localhost:9200"]

```

8. dos命令进入 kibana 安装目录下，启动 kibana 

```

cd bin

```

```

kibana.bat

```

启动没有报错，在浏览器输入 [http://localhost:5601](http://localhost:5601) ，显示页面表示启动成功

9. 配置 logback-spring.xml

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

10. 启动项目，调用 [http://localhost:8000/log](http://localhost:8000/log) 接口，可在 dos 窗口查看输出日志

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

11. 打开kibana管理页面，查看相关信息
