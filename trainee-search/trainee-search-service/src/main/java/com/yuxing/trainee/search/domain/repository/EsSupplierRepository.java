package com.yuxing.trainee.search.domain.repository;

import com.yuxing.trainee.search.domain.entity.EsSupplier;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * es supplier 仓储接口
 *
 * @author yuxing
 */
public interface EsSupplierRepository extends ElasticsearchRepository<EsSupplier, Long> {
}
