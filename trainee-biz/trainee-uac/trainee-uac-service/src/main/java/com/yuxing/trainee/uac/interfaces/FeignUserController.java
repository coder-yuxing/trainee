package com.yuxing.trainee.uac.interfaces;

import com.yuxing.trainee.uac.api.dto.UserDTO;
import com.yuxing.trainee.uac.application.facade.UserFacadeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关接口
 *
 * @author yuxing
 */
@RestController
@RequestMapping("/feign_uac")
@AllArgsConstructor
public class FeignUserController {

    private final UserFacadeService userFacadeService;

    @GetMapping("/user/by_phone")
    public UserDTO getByPhone(@RequestParam String phone) {
        return userFacadeService.getByPhone(phone);
    }


}
