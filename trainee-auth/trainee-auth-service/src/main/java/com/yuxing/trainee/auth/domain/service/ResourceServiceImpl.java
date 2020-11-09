package com.yuxing.trainee.auth.domain.service;

import com.yuxing.trainee.auth.api.contant.AuthConstant;
import com.yuxing.trainee.auth.api.dto.ResourceConfig;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 资源与角色匹配关系管理业务类
 *
 * @author yuxing
 */
@Service
@AllArgsConstructor
public class ResourceServiceImpl {

    private Map<String, ResourceConfig> resourceRolesMap;

    private final RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public void initData() {
        resourceRolesMap = new TreeMap<>();
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("trainee.miaosha");

        resourceRolesMap.put("/api/miaosha/hello", new ResourceConfig("/api/miaosha/hello", treeSet));
        redisTemplate.opsForHash().putAll(AuthConstant.RESOURCE_KEY, resourceRolesMap);
    }
}
