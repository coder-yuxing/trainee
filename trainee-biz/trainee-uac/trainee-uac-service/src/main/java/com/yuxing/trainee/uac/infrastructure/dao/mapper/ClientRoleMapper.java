package com.yuxing.trainee.uac.infrastructure.dao.mapper;

import com.yuxing.trainee.uac.infrastructure.dao.model.ClientRolePO;

/**
 * 权限(角色)
 *
 * @author yuxing
 * @since 2020/11/08
 */
public interface ClientRoleMapper {


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return ClientRole
     */
    ClientRolePO getById(Long id);

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
    * @param ClientRole 分组
    * @return 影响的条数
    */
    int updateById(ClientRolePO ClientRole);

    /**
     * 插入
     *
     * @param ClientRole 分组
     * @return 影响的条数
     */
    int insert(ClientRolePO ClientRole);
}