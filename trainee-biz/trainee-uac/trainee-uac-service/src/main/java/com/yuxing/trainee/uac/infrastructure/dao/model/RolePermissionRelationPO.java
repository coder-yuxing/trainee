package com.yuxing.trainee.uac.infrastructure.dao.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限与角色关系表
 *
 * @author yuxing
 * @date 2020/11/08
 */
@Getter
@Setter
@ToString
public class RolePermissionRelationPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 权限ID
     */
    private Long permissionId;
}