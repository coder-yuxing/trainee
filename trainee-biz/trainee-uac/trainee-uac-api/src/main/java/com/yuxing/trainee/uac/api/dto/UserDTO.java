package com.yuxing.trainee.uac.api.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

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
    private Long gmtCreate;

    /**
     * 更新时间
     */
    private Long gmtModified;

    /**
     * 用户权限
     */
    private Set<Permission> permissions;

    @Data
    public static class Permission {
        private String clientId;
        private Set<String> permissions;
    }
}
