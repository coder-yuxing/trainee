package com.yuxing.trainee.search.api.query;

import com.yuxing.trainee.common.core.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author yuxing
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EsSupplierQuery extends BaseQuery {

    /**
     * 类型
     */
    private Integer type;

    /**
     * 启停用
     */
    private Boolean enabled;

    /**
     * 服务地区
     */
    private List<Long> serviceAreas;

    /**
     * 关键字
     */
    private String keyword;
}
