package com.yuxing.trainee.uac.infrastructure.dao.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户持久化对象实体类
 *
 * @author yuxing
 * @since 2020/11/07
 */
@Getter
@Setter
@ToString
public class UserPO implements Serializable {

    private static final long serialVersionUID = 1L;

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


}