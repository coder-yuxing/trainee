package com.yuxing.trainee.uac.domain.repository;

import com.yuxing.trainee.uac.domain.entity.User;

/**
 * 用户仓储接口
 *
 * @author yuxing
 */
public interface UserRepository {

    /**
     * 根据手机号查询用户
     *
     * @param phone 手机号
     * @return 用户
     */
    User getByPhone(String phone);
}
