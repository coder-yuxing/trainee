package com.yuxing.trainee.search.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * 供应商文档映射类
 *
 * @author yuxing
 */
@Data
@Document(indexName = "supplier")
public class EsSupplier {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String name;

    private List<EsContracts> contracts;

    private ServiceAreas serviceAreas;

    private Boolean enabled;

    /**
     * 供应商类型 材料供应商(1) 施工供应商(2)
     */
    private Integer type;

    @Data
    @AllArgsConstructor
    public static class EsContracts {

        @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
        private String name;

        @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
        private String phone;
    }

    @Data
    public static class ServiceAreas {

        /**
         * 是否服务全国
         */
        private Boolean wholeCountry;

        /**
         * 服务地区
         */
        private List<Long> cities;
    }
}
