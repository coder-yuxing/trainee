package com.yuxing.trainee.uac.infrastructure.dao.mapper;

import com.yuxing.trainee.uac.infrastructure.dao.model.ClientPermissionPO;

/**
 * 客户端权限
 *
 * @author yuxing
 * @since 2020/11/08
 */
public interface ClientPermissionMapper {


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return ClientPermission
     */
    ClientPermissionPO getById(Long id);

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
    * @param ClientPermission 分组
    * @return 影响的条数
    */
    int updateById(ClientPermissionPO ClientPermission);

    /**
     * 插入
     *
     * @param ClientPermission 分组
     * @return 影响的条数
     */
    int insert(ClientPermissionPO ClientPermission);
}