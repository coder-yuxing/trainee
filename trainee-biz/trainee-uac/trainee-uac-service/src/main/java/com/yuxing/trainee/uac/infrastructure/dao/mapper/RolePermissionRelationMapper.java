package com.yuxing.trainee.uac.infrastructure.dao.mapper;

import com.yuxing.trainee.uac.infrastructure.dao.model.RolePermissionRelationPO;

/**
 * 权限与角色关系表
 *
 * @author yuxing
 * @since 2020/11/08
 */
public interface RolePermissionRelationMapper {


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return RolePermissionRelation
     */
    RolePermissionRelationPO getById(Long id);

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
    * @param RolePermissionRelation 分组
    * @return 影响的条数
    */
    int updateById(RolePermissionRelationPO RolePermissionRelation);

    /**
     * 插入
     *
     * @param RolePermissionRelation 分组
     * @return 影响的条数
     */
    int insert(RolePermissionRelationPO RolePermissionRelation);
}