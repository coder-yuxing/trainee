package com.yuxing.trainee.miaosha.interfaces;

import com.yuxing.trainee.common.core.Result;
import com.yuxing.trainee.miaosha.application.facade.LoginFacadeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录接口
 *
 * @author yuxing
 */
@RestController
@AllArgsConstructor
@RequestMapping("/miaosha")
public class LoginController {

    private final LoginFacadeService loginFacadeService;

    @PostMapping("/login")
    public Result<?> login(@RequestParam String phone, @RequestParam String password) {
        return Result.success(loginFacadeService.login(phone, password));
    }
}
