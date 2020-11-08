package com.yuxing.trainee.miaosha.application.facade.impl;

import com.yuxing.trainee.miaosha.application.facade.LoginFacadeService;
import com.yuxing.trainee.miaosha.infrastructure.constant.SystemConstant;
import com.yuxing.trainee.miaosha.infrastructure.rpc.AuthRemoteService;
import com.yuxing.trainee.miaosha.infrastructure.rpc.UserRemoteService;
import com.yuxing.trainee.uac.api.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class LoginFacadeServiceImpl implements LoginFacadeService {

    private final UserRemoteService userRemoteService;
    private final AuthRemoteService authRemoteService;

    @Override
    public Object login(String phone, String password) {
        UserDTO user = userRemoteService.getByPhone(phone);
        if (user == null) {
            throw new RuntimeException();
        }
        Set<UserDTO.Permission> permissions = user.getPermissions();
        boolean anyMatch = permissions.stream().anyMatch(p -> SystemConstant.CLIENT_ID.equals(p.getClientId()));
        if (!anyMatch) {
            throw new RuntimeException();
        }
        Map<String, String> param = new HashMap<>(5);
        param.put("client_id", SystemConstant.CLIENT_ID);
        param.put("client_secret", SystemConstant.CLIENT_SECRET);
        param.put("grant_type", "password");
        param.put("username", phone);
        param.put("password", password);
        return authRemoteService.postAccessToken(param);
    }
}
