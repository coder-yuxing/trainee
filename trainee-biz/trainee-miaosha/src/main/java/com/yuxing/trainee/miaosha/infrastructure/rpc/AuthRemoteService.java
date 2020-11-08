package com.yuxing.trainee.miaosha.infrastructure.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "${miaosha.remote-service.auth}", contextId = "authRemoteService")
public interface AuthRemoteService {

    @PostMapping(value = "/oauth/token")
    Object postAccessToken(@RequestParam Map<String, String> parameters);
}
