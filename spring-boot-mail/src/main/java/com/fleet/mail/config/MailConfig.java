package com.fleet.mail.config;

import com.fleet.mail.util.MailUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author April Han
 */
@Configuration
public class MailConfig {

    /**
     * 注入 MailUtil 操作类
     */
    @Bean
    public MailUtil mailUtil() {
        return new MailUtil();
    }
}
