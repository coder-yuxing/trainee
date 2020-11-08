package com.yuxing.trainee.uac.infrastructure.dao.mapper;

import com.yuxing.trainee.uac.infrastructure.dao.model.UserClientRelationPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户与客户端关系表
 *
 * @author yuxing
 * @since 2020/11/08
 */
public interface UserClientRelationMapper {


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return UserClientRelation
     */
    UserClientRelationPO getById(Long id);

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
    * @param UserClientRelation 分组
    * @return 影响的条数
    */
    int updateById(UserClientRelationPO UserClientRelation);

    /**
     * 插入
     *
     * @param UserClientRelation 分组
     * @return 影响的条数
     */
    int insert(UserClientRelationPO UserClientRelation);

    /**
     * 根据 userId 查询客户端关系列表
     *
     * @param userId 用户ID
     * @return 用户与客户端的关系列表
     */
    List<UserClientRelationPO> getByUserId(@Param("userId") Long userId);
}