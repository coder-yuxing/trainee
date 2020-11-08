package com.yuxing.trainee.uac.infrastructure.dao.mapper;

import com.yuxing.trainee.uac.infrastructure.dao.model.UserPO;
import org.apache.ibatis.annotations.Param;

/**
 * 用户持久化对象数据访问接口
 *
 * @author yuxing
 * @since 2020/11/07
 */
public interface UserMapper {

    /**
     * 查询
     *
     * @param id 主键ID
     * @return 用户持久化对象
     */
    UserPO getById(@Param("id")  Long id);

    /**
     * 删除
     *
     * @param id 主键ID
     * @return 影响的条数
     */
    int deleteById(@Param("id")  Long id);

    /**
     * 插入
     *
     * @param userPO 用户持久化对象
     * @return 影响的条数
     */
    int insert(UserPO userPO);

    /**
     * 更新
     *
     * @param userPO 用户持久化对象
     * @return 影响的条数
     */
    int updateById(UserPO userPO);

    /**
     * 根据手机号查询用户
     *
     * @param phone 手机号
     * @return 用户
     */
    UserPO getByPhone(String phone);
}