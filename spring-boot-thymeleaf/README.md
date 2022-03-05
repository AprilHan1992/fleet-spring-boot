# spring-boot-thymeleaf

> Spring Boot 结合 Thymeleaf 模板引擎

## 环境搭建

> 配置 IDEA 修改 Thymeleaf 的 html 模板不需要重启应用

1. Edit Configurations -> configuration 按图示配置

	![avatar](https://img-blog.csdnimg.cn/20200603122941857.png)
	![avatar](https://img-blog.csdnimg.cn/20200603122729484.png)

2. Help -> Find action 输入 `Registry` 按图示配置

	![avatar](https://img-blog.csdnimg.cn/20200603122900456.png)
	![avatar](https://img-blog.csdnimg.cn/20200603133121656.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzgyOTM5NQ==,size_16,color_FFFFFF,t_70)

3. File -> Settings -> Build,Execution,Deployment -> Compiler 按图示配置

	![avatar](https://img-blog.csdnimg.cn/20200603133200941.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzgyOTM5NQ==,size_16,color_FFFFFF,t_70)

4. 在 application.yml 中配置

	```
	spring:
	  thymeleaf:
	    mode: LEGACYHTML5
	    encoding: UTF-8
	    servlet:
	      content-type: text/html
	    cache: false
	    prefix: classpath:/templates/
	    suffix: .html
	```