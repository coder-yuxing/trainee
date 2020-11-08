package com.yuxing.trainee.uac.infrastructure.repository.assembler;

import com.yuxing.trainee.uac.domain.entity.User;
import com.yuxing.trainee.uac.infrastructure.dao.model.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAssembler {

    UserAssembler INSTANCE = Mappers.getMapper( UserAssembler.class );

    User toDO(UserPO user);
}
