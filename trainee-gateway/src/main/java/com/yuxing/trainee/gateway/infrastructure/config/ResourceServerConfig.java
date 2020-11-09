package com.yuxing.trainee.gateway.infrastructure.config;

import cn.hutool.core.util.ArrayUtil;
import com.yuxing.trainee.auth.api.contant.AuthConstant;
import com.yuxing.trainee.gateway.infrastructure.auth.AuthorizationManager;
import com.yuxing.trainee.gateway.infrastructure.config.filter.WhitelistRequestJwtFilter;
import com.yuxing.trainee.gateway.infrastructure.config.handler.RestAuthenticationEntryPoint;
import com.yuxing.trainee.gateway.infrastructure.config.handler.RestfulAccessDeniedHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 资源服务器配置
 *
 * @author yuxing
 */
@Configuration
@AllArgsConstructor
@EnableWebFluxSecurity
public class ResourceServerConfig {

    private final WhiteListConfig whiteListConfig;
    private final AuthorizationManager authorizationManager;
    private final WhitelistRequestJwtFilter whitelistRequestJwtFilter;
    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        // 自定义处理JWT请求头过期或签名错误的结果
        http.oauth2ResourceServer().authenticationEntryPoint(this.restAuthenticationEntryPoint);
        // 对白名单路径，直接移除JWT请求头
        http.addFilterBefore(this.whitelistRequestJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        http.authorizeExchange()
                // 白名单配置
                .pathMatchers(ArrayUtil.toArray(this.whiteListConfig.getWhitelist(),String.class)).permitAll()
                // 鉴权管理器配置
                .anyExchange().access(this.authorizationManager)
                .and().exceptionHandling()
                // 处理未授权
                .accessDeniedHandler(this.restfulAccessDeniedHandler)
                // 处理未认证
                .authenticationEntryPoint(this.restAuthenticationEntryPoint)
                .and().csrf().disable();
        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstant.AUTHORITIES);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
