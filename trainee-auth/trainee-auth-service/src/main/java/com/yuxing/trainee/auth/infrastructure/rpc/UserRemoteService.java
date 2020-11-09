package com.yuxing.trainee.auth.infrastructure.rpc;

import com.yuxing.trainee.uac.api.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${auth.remote-service.uac}", contextId = "userRemoteService")
public interface UserRemoteService {

    @GetMapping("/feign_uac/user/by_phone")
    UserDTO getByPhone(@RequestParam String phone);
}
