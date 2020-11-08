package com.yuxing.trainee.uac.application.facade;

import com.yuxing.trainee.uac.api.dto.UserDTO;

public interface UserFacadeService {

    UserDTO getByPhone(String phone);
}
