package com.yuxing.trainee.uac.application.facade.impl;

import com.yuxing.trainee.uac.api.dto.UserDTO;
import com.yuxing.trainee.uac.application.assembler.UserAssembler;
import com.yuxing.trainee.uac.application.facade.UserFacadeService;
import com.yuxing.trainee.uac.domain.entity.User;
import com.yuxing.trainee.uac.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yuxing
 */
@Service
@AllArgsConstructor
public class UserFacadeServiceImpl implements UserFacadeService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getByPhone(String phone) {
        User user = userRepository.getByPhone(phone);
        return UserAssembler.INSTANCE.toDto(user);
    }

}
