package com.yuxing.trainee.gateway.config;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yuxing.trainee.auth.api.contant.AuthConstant;
import com.yuxing.trainee.auth.api.dto.AuthUser;
import com.yuxing.trainee.auth.api.dto.ResourceConfig;
import com.yuxing.trainee.common.core.Logic;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.*;

/**
 * 鉴权管理器，用于判断是否有资源的访问权限
 *
 * @author yuxing
 */
@Slf4j
@Component
@AllArgsConstructor
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final RedisTemplate<String,Object> redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentications, AuthorizationContext authorizationContext) {
        // 获取请求
        ServerHttpRequest request =  authorizationContext.getExchange().getRequest();
        // 从Redis中获取当前路径可访问角色列表
        URI uri = request.getURI();
        Object obj = redisTemplate.opsForHash().get(AuthConstant.RESOURCE_KEY, uri.getPath());
        ResourceConfig resourceConfig = JSONUtil.parseObj(obj).toBean(ResourceConfig.class);

        // 需要进行权限验证的
        return authentications.filter(Authentication::isAuthenticated)
            .flatMapIterable(a -> {
                if(a.getPrincipal() instanceof Jwt){
                    Jwt jwtValue = (Jwt) a.getPrincipal();
                    JSONObject tokenInfo = JSONUtil.parseObj(jwtValue.getClaims());
                    AuthUser authUser = tokenInfo.getJSONObject(AuthConstant.USER_INFO).toBean(AuthUser.class);
                    Set<String> authoritiesSet = new HashSet<>(tokenInfo.getJSONArray(AuthConstant.AUTHORITIES).toList(String.class));
                    authUser.setAuthorities(authoritiesSet);
                    return Collections.singletonList(authUser);
                }
                return Collections.emptyList();
            })
            .any(c -> {
                // 获取当前权限
                Set<String> authorities = c.getAuthorities();

                Set<String> requiredPermissions = resourceConfig.getRequiredPermissions();
                if (requiredPermissions.isEmpty()) {
                    return true;
                }

                if (authorities.isEmpty()) {
                    return false;
                }

                if (Logic.AND == resourceConfig.getLogic()) {
                    return authorities.containsAll(requiredPermissions);
                }

                if (Logic.OR == resourceConfig.getLogic()) {
                    return requiredPermissions.stream().anyMatch(authorities::contains);
                }
                return false;
            })
            .map(AuthorizationDecision::new).defaultIfEmpty(new AuthorizationDecision(false));
    }
}
