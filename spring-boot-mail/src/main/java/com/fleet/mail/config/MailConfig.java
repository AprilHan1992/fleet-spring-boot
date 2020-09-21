package com.fleet.mail.config;

import com.fleet.mail.util.MailUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Properties;

@Configuration
@ConfigurationProperties("spring.mail")
public class MailConfig {

    /**
     * 邮件服务器地址
     */
    private String host;

    /**
     * 邮件服务器端口
     */
    private Integer port;

    /**
     * 邮件协议
     */
    private String protocol;

    /**
     * 邮箱地址
     */
    private String username;

    /**
     * 邮箱密码
     */
    private String password;

    /**
     * 默认编码
     */
    private String defaultEncoding;

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port != null ? port : 25);
        javaMailSender.setProtocol(protocol);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setDefaultEncoding(StringUtils.isNotEmpty(defaultEncoding) ? defaultEncoding : "UTF-8");
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.smtp.auth", "true");
        javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
        javaMailProperties.setProperty("mail.smtp.starttls.required", "true");
        javaMailProperties.setProperty("mail.smtp.ssl.trust", "smtp.sina.com");
        javaMailProperties.setProperty("mail.smtp.timeout", "10000");
        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }

    @Bean
    public static SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.LEGACYHTML5);
        templateResolver.setCacheable(false);
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    /**
     * 注入 MailUtil 操作类
     *
     * @param javaMailSender
     * @return
     */
    @Bean
    public MailUtil mailUtil(JavaMailSenderImpl javaMailSender, TemplateEngine templateEngine) {
        MailUtil mailUtil = new MailUtil();
        mailUtil.setJavaMailSender(javaMailSender);
        mailUtil.setTemplateEngine(templateEngine);
        return mailUtil;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }
}
