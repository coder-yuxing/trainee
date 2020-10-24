package com.yuxing.trainee.search.api.query;

import lombok.Data;

import java.util.List;

@Data
public class EsSupplierQuery {

    private Integer page;

    private Integer rows;

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
