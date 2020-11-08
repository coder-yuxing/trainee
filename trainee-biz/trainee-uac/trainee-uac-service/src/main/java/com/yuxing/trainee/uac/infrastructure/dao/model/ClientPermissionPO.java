package com.yuxing.trainee.uac.infrastructure.dao.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 客户端权限
 *
 * @author yuxing
 * @date 2020/11/08
 */
@Getter
@Setter
@ToString
public class ClientPermissionPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Long id;
    /**
     * 权限组所属客户端
     */
    private String clientId;
    /**
     * 权限组名称
     */
    private String name;
    /**
     * 权限标识
     */
    private String code;
}