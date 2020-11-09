package com.yuxing.trainee.seckill.interfaces;

import com.yuxing.trainee.auth.api.dto.AuthUser;
import com.yuxing.trainee.auth.api.manager.LoginUserManager;
import com.yuxing.trainee.common.core.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/seckills")
public class TestController {

    private final LoginUserManager loginUserManager;

    @GetMapping("/hello")
    public Result<String> hello() {
        AuthUser authUser = loginUserManager.getCurrentUser();
        return Result.success("hello, " + authUser.getUsername());
    }
}
