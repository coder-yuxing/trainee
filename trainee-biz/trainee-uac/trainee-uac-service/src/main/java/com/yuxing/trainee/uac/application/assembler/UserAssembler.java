package com.yuxing.trainee.uac.application.assembler;

import com.yuxing.trainee.uac.api.dto.UserDTO;
import com.yuxing.trainee.uac.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Date;

@Mapper
public interface UserAssembler {

    UserAssembler INSTANCE = Mappers.getMapper( UserAssembler.class );

    UserDTO toDto(User user);

    default Long map(Date date) {
        return date == null ? null : date.getTime();
    }
}
