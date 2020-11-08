package com.yuxing.trainee.uac.infrastructure.dao.mapper;

import com.yuxing.trainee.uac.infrastructure.dao.model.ClientPermissionPO;
import com.yuxing.trainee.uac.infrastructure.dao.model.UserRoleRelationPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户与角色关系表
 *
 * @author yuxing
 * @since 2020/11/08
 */
public interface UserRoleRelationMapper {


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return UserRoleRelation
     */
    UserRoleRelationPO getById(Long id);

    /**
     * 删除
     *
     * @param id id
     * @return 影响的条数
     */
    int deleteById(Long id);

    /**
    * 更新
    *
    * @param UserRoleRelation 分组
    * @return 影响的条数
    */
    int updateById(UserRoleRelationPO UserRoleRelation);

    /**
     * 插入
     *
     * @param UserRoleRelation 分组
     * @return 影响的条数
     */
    int insert(UserRoleRelationPO UserRoleRelation);

    /**
     * 查询用户指定客户端下的权限列表
     *
     * @param userId   用户ID
     * @param clientId 客户端ID
     * @return 用户在指定客户端下的权限列表
     */
    List<ClientPermissionPO> listPermByClientId(@Param("userId") Long userId, @Param("clientId") String clientId);
}