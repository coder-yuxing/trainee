package com.yuxing.trainee.search.application.facade.impl;

import com.yuxing.trainee.search.api.command.CreateEsSupplierCommand;
import com.yuxing.trainee.search.api.command.DeleteEsSupplierCommand;
import com.yuxing.trainee.search.api.command.UpdateEsSupplierCommand;
import com.yuxing.trainee.search.api.dto.EsSupplierDTO;
import com.yuxing.trainee.search.api.query.EsSupplierQuery;
import com.yuxing.trainee.search.application.assembler.EsSupplierAssembler;
import com.yuxing.trainee.search.application.facade.EsSupplierFacadeService;
import com.yuxing.trainee.search.domain.entity.EsSupplier;
import com.yuxing.trainee.search.infrastructure.util.Pager;
import com.yuxing.trainee.search.infrastructure.util.SearchHitsUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * supplier es 门面服务接口实现
 *
 * @author yuxing
 */
@Component
@AllArgsConstructor
public class EsSupplierFacadeServiceImpl implements EsSupplierFacadeService {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public void create(CreateEsSupplierCommand command) {
        // TODO
    }

    @Override
    public void update(UpdateEsSupplierCommand command) {
        // TODO
    }

    @Override
    public void delete(DeleteEsSupplierCommand command) {
        // TODO
    }

    @Override
    public Pager<EsSupplierDTO> search(EsSupplierQuery query) {
        SearchHits<EsSupplier> searchHits = elasticsearchOperations.search(EsSupplierAssembler.toEsQuery(query).toQuery(), EsSupplier.class);
        List<EsSupplier> hits = SearchHitsUtils.parseHits(searchHits);
        Map<String, List<Pager.Aggregation>> aggregations = SearchHitsUtils.parseAggregations(searchHits);
        Pager<EsSupplierDTO> pager = new Pager<>(query.getPage(), query.getRows(), Long.valueOf(searchHits.getTotalHits()).intValue());
        pager.setList(EsSupplierAssembler.toDto(hits));
        pager.setAggregations(aggregations);
        return pager;
    }
}
