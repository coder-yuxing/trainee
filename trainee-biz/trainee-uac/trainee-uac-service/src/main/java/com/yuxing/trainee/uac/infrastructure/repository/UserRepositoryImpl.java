package com.yuxing.trainee.uac.infrastructure.repository;

import cn.hutool.core.collection.CollectionUtil;
import com.yuxing.trainee.uac.domain.entity.User;
import com.yuxing.trainee.uac.domain.repository.UserRepository;
import com.yuxing.trainee.uac.infrastructure.dao.mapper.UserClientRelationMapper;
import com.yuxing.trainee.uac.infrastructure.dao.mapper.UserMapper;
import com.yuxing.trainee.uac.infrastructure.dao.mapper.UserRoleRelationMapper;
import com.yuxing.trainee.uac.infrastructure.dao.model.UserClientRelationPO;
import com.yuxing.trainee.uac.infrastructure.dao.model.UserPO;
import com.yuxing.trainee.uac.infrastructure.dao.model.UserPermission;
import com.yuxing.trainee.uac.infrastructure.repository.assembler.UserAssembler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户仓储实现
 *
 * @author yuxing
 */
@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;
    private final UserRoleRelationMapper userRoleRelationMapper;
    private final UserClientRelationMapper userClientRelationMapper;

    @Override
    public User getByPhone(String phone) {
        UserPO userPO = userMapper.getByPhone(phone);
        List<UserClientRelationPO> userClientRelations =  userClientRelationMapper.getByUserId(userPO.getId());
        Set<User.Permission> permissions = userClientRelations.stream().map(u -> userRoleRelationMapper.listPermByClientId(u.getUserId(), u.getClientId()))
                .map(this::toPerms).filter(Objects::nonNull).collect(Collectors.toSet());
        User user = UserAssembler.INSTANCE.toDO(userPO);
        user.setPermissions(permissions);
        return user;
    }

    private User.Permission toPerms(List<UserPermission> list) {
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }
        Set<String> collect = list.stream().map(UserPermission::getCode).collect(Collectors.toSet());
        return new User.Permission(list.get(0).getClientId(), collect);
    }
}
