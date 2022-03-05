package com.fleet.theme.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.theme.SessionThemeResolver;

/**
 * @author April Han
 */
@Component
@Configuration
public class ThemeConfig {

    @Value("${theme}")
    private String theme;

    @Bean
    public ResourceBundleThemeSource themeSource() {
        ResourceBundleThemeSource resourceBundleThemeSource = new ResourceBundleThemeSource();
        resourceBundleThemeSource.setBasenamePrefix("theme.");
        resourceBundleThemeSource.setDefaultEncoding("utf-8");
        return resourceBundleThemeSource;
    }

    @Bean
    public SessionThemeResolver themeResolver() {
        SessionThemeResolver sessionThemeResolver = new SessionThemeResolver();
        sessionThemeResolver.setDefaultThemeName(theme);
        return sessionThemeResolver;
    }
}
