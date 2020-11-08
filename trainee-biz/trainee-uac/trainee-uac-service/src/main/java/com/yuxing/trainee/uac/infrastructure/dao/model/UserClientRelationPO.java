package com.yuxing.trainee.uac.infrastructure.dao.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户与客户端关系表
 *
 * @author yuxing
 * @date 2020/11/08
 */
@Getter
@Setter
@ToString
public class UserClientRelationPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 客户端标识
     */
    private String clientId;
}