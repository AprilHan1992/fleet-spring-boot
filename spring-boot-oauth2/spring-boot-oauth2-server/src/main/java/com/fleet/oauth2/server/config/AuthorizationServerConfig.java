package com.fleet.oauth2.server.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * @author April Han
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .passwordEncoder(new BCryptPasswordEncoder())
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client_id_1001")
                .secret(new BCryptPasswordEncoder().encode("client_secret_1001"))
                // 设置授权模式
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .resourceIds("resource_id")
                .scopes("app", "web")
                // 设置 access_token 有效期为15分钟
                .accessTokenValiditySeconds(900)
                // 设置 refresh_token 有效期为30天
                .refreshTokenValiditySeconds(2592000)
                .redirectUris("http://localhost:8001/callback")
                .and()
                .withClient("client_id_1002")
                .secret(new BCryptPasswordEncoder().encode("client_secret_1002"))
                // 设置授权模式
                .authorizedGrantTypes("implicit")
                .resourceIds("resource_id")
                .scopes("app", "web")
                // 设置 access_token 有效期为30天
                .accessTokenValiditySeconds(2592000)
                .redirectUris("http://localhost:8001/callback")
                .and()
                .withClient("client_id_1003")
                .secret(new BCryptPasswordEncoder().encode("client_secret_1003"))
                // 设置授权模式
                .authorizedGrantTypes("password", "refresh_token")
                .resourceIds("resource_id")
                .scopes("app", "web")
                // 设置 access_token 有效期为15分钟
                .accessTokenValiditySeconds(900)
                // 设置 refresh_token 有效期为30天
                .refreshTokenValiditySeconds(2592000);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }
}
