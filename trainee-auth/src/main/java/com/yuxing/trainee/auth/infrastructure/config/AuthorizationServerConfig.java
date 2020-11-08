package com.yuxing.trainee.auth.infrastructure.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * 认证服务配置
 *
 * @author yuxing
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final DataSource dataSource;
    private final TokenStore tokenStore;
    private final AuthenticationManager authenticationManager;
    private final JwtAccessTokenConverter accessTokenConverter;
    private final TokenConfig.JwtTokenEnhancer jwtTokenEnhancer;
    /**
     * 配置认证服务器获取客户端数据源(此处通过数据库获取信息)
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(this.jdbcClientDetailsService());
    }

    /**
     * 配置客户端详情服务，支持认证服务器获取客户端配置信息
     */
    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(this.dataSource);
    }


    /**
     * AuthorizationServerEndpointsConfigurer 配置令牌的访问端点及令牌服务如何生成
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // 密码模式需要配置 authenticationManager
        endpoints.authenticationManager(this.authenticationManager)
                // 配置加载用户信息的服务
                .authorizationCodeServices(this.authorizationCodeServices())
                // token 管理服务配置
                .tokenServices(this.tokenServices())
                // 允许POST提交
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     * 授权码模式获取授权码存储策略
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        // 授权码存入数据库
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 令牌管理服务
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 客户端信息服务
        tokenServices.setClientDetailsService(this.jdbcClientDetailsService());
        // 是否支持刷新令牌
        tokenServices.setSupportRefreshToken(true);
        // 令牌存储策略
        tokenServices.setTokenStore(this.tokenStore);
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        // 配置JWT的内容增强器
        enhancerChain.setTokenEnhancers(Arrays.asList(this.jwtTokenEnhancer, this.accessTokenConverter));
        tokenServices.setTokenEnhancer(enhancerChain);

        // 令牌默认有效期 2 小时
        tokenServices.setAccessTokenValiditySeconds(7200);
        // 刷新令牌默认有效期3天
        tokenServices.setRefreshTokenValiditySeconds(259200);
        return tokenServices;
    }


    /**
     * AuthorizationServerSecurityConfigurer 用于配置令牌端点的安全约束，即哪些人可以访问
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        //  /oauth/token_key 公开
        security.tokenKeyAccess("permitAll()")
                // /oauth/check_token 公开
                .checkTokenAccess("permitAll()")
                // 表单认证申请令牌
                .allowFormAuthenticationForClients();
    }



}
