package com.yuxing.trainee.gateway.config.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JWSObject;
import com.yuxing.trainee.auth.api.contant.AuthConstant;
import com.yuxing.trainee.auth.api.dto.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;

/**
 * 将登录用户的JWT转化成用户信息的全局过滤器
 *
 * @author yuxing
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(AuthConstant.TOKEN_HEADER);
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }
        try {
            // 从token中解析用户信息并设置到Header中去
            String realToken = token.replace(AuthConstant.TOKEN_TYPE, StrUtil.EMPTY);
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            log.info("AuthGlobalFilter.filter() user:{}",userStr);
            AuthUser authUser = JSONUtil.parseObj(JSONUtil.parseObj(userStr).getStr(AuthConstant.USER_INFO)).toBean(AuthUser.class);
            List<String> authorities = JSONUtil.parseArray(JSONUtil.parseObj(userStr).get(AuthConstant.AUTHORITIES)).toList(String.class);
            authUser.setAuthorities(new HashSet<>(authorities));
            ServerHttpRequest request = exchange.getRequest().mutate().header(AuthConstant.USER_MSG_HEADER, JSONUtil.toJsonStr(authUser)).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
