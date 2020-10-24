package com.yuxing.trainee.search.application.assembler;

import com.yuxing.trainee.search.api.dto.EsSupplierDTO;
import com.yuxing.trainee.search.api.query.EsSupplierQuery;
import com.yuxing.trainee.search.domain.entity.EsSupplier;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuxing
 */
public class EsSupplierAssembler {

    private EsSupplierAssembler() {}

    public static com.yuxing.trainee.search.domain.entity.EsSupplierQuery toEsQuery(EsSupplierQuery query) {
        com.yuxing.trainee.search.domain.entity.EsSupplierQuery esQuery = new com.yuxing.trainee.search.domain.entity.EsSupplierQuery();
        BeanUtils.copyProperties(query, esQuery);
        return esQuery;
    }

    public static List<EsSupplierDTO> toDto(List<EsSupplier> suppliers) {
        if (suppliers.isEmpty()) {
            return Collections.emptyList();
        }
        return suppliers.stream().map(EsSupplierAssembler::toDto).collect(Collectors.toList());
    }

    public static EsSupplierDTO toDto(EsSupplier supplier) {
        EsSupplierDTO dto = new EsSupplierDTO();
        BeanUtils.copyProperties(supplier, dto);
        return dto;
    }

}
