package com.yuxing.trainee.auth.infrastructure.config;

import cn.hutool.json.JSONUtil;
import com.yuxing.trainee.auth.api.contant.AuthConstant;
import com.yuxing.trainee.auth.api.dto.AuthUser;
import com.yuxing.trainee.auth.domain.entity.SecurityUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

import java.security.KeyPair;

/**
 * token 相关配置
 *
 * @author yuxing
 */
@Configuration
public class TokenConfig {

    private static final String KEY_SECRET = "123456";

    /**
     * 令牌存储策略
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(this.accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), KEY_SECRET.toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", KEY_SECRET.toCharArray());
    }

    @Component
    public static class JwtTokenEnhancer implements TokenEnhancer {

        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
            DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
            // 在 jwt 中设置额外的用户信息
            if (authentication.getPrincipal() instanceof SecurityUser) {
                SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
                AuthUser user = new AuthUser(securityUser.getId(), securityUser.getUsername(), securityUser.getAvatar(), securityUser.getPhone());
                defaultOAuth2AccessToken.getAdditionalInformation().put(AuthConstant.USER_INFO, JSONUtil.toJsonStr(user));
            }
            return defaultOAuth2AccessToken;
        }
    }

}
