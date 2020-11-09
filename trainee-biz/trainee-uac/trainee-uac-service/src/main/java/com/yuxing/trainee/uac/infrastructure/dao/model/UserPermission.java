package com.yuxing.trainee.uac.infrastructure.dao.model;

import lombok.Data;

@Data
public class UserPermission {

    /**
     * 权限ID
     */
    private Long id;

    /**
     * 权限组所属客户端
     */
    private String clientId;

    /**
     * 权限标识
     */
    private String code;


}
