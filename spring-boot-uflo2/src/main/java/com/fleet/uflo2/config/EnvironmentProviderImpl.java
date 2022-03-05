package com.fleet.uflo2.config;


import com.bstek.uflo.env.EnvironmentProvider;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;

@Configuration
public class EnvironmentProviderImpl implements EnvironmentProvider {

    @Resource
    private SessionFactory sessionFactory;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public PlatformTransactionManager getPlatformTransactionManager() {
        return platformTransactionManager;
    }

    @Override
    public String getLoginUser() {
        return "anonymous";
    }

    @Override
    public String getCategoryId() {
        return null;
    }
}
