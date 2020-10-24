package com.yuxing.trainee.search.domain.entity;

import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Data
public class EsSupplierQuery {

    public Query toQuery() {
        BoolQueryBuilder builder = new BoolQueryBuilder();
        builder.filter(QueryBuilders.termQuery("type", this.type));
        builder.filter(QueryBuilders.termQuery("enabled", this.enabled));
        if (!CollectionUtils.isEmpty(this.serviceAreas)) {
            BoolQueryBuilder shouldFilterBuilder = new BoolQueryBuilder();
            shouldFilterBuilder.should(QueryBuilders.termsQuery("serviceAreas.cities", this.serviceAreas));
            shouldFilterBuilder.should(QueryBuilders.termQuery("serviceAreas.wholeCountry", true));
            builder.filter(shouldFilterBuilder);
        }
        if (StringUtils.isNotBlank(this.keyword)) {
            final String analyzer = "ik_smart";
            final String minimumShouldMatch = "75%";
            // 关键字查询
            String[] fieldNames = new String[] {"name", "contracts.name", "contracts.phone"};
            MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery(this.keyword, fieldNames);
            multiMatchQuery.analyzer(analyzer);
            multiMatchQuery.operator(Operator.AND);
            multiMatchQuery.minimumShouldMatch(minimumShouldMatch);
            builder.must(multiMatchQuery);
        }
        return new NativeSearchQuery(builder);
    }

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
