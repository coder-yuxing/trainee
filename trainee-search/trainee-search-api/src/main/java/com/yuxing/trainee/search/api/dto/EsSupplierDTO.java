package com.yuxing.trainee.search.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author yuxing
 */
@Data
public class EsSupplierDTO {

    private Long id;

    private String name;

    private List<EsSupplierDTO.EsContractsDTO> contracts;

    private EsSupplierDTO.ServiceAreasDTO serviceAreas;

    private Boolean enabled;

    /**
     * 供应商类型 材料供应商(1) 施工供应商(2)
     */
    private Integer type;

    @Data
    @AllArgsConstructor
    public static class EsContractsDTO {

        private String name;

        private String phone;
    }

    @Data
    public static class ServiceAreasDTO {

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
