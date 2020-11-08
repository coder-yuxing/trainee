package com.yuxing.trainee.uac.domain.entity;

import com.yuxing.trainee.uac.domain.valueobject.ClientPermission;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * 用户
 *
 * @author yuxing
 */
@Data
public class User {

    private Long id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 可访问客户端及权限
     */
    private Set<ClientPermission> permissions;

}
