package com.yuxing.trainee.search.infrastructure.util;

import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yuxing
 */
public class SearchHitsUtils {

    private SearchHitsUtils() {}

    public static <T> List<T> parseHits(SearchHits<T> searchHits) {
        if (!searchHits.hasSearchHits()) {
            return Collections.emptyList();
        }
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    public static <T> Map<String, List<Pager.Aggregation>> parseAggregations(SearchHits<T> searchHits) {
        if (!searchHits.hasAggregations()) {
            return Collections.emptyMap();
        }

        List<org.elasticsearch.search.aggregations.Aggregation> aggregations = Objects.requireNonNull(searchHits.getAggregations()).asList();
        Map<String, List<Pager.Aggregation>> result = new HashMap<>(aggregations.size());
        for (org.elasticsearch.search.aggregations.Aggregation agg : aggregations) {
            Terms longTerms = (Terms) agg;
            String name = longTerms.getName();
            List<? extends Terms.Bucket> buckets = longTerms.getBuckets();
            List<Pager.Aggregation> collect = buckets.stream()
                    .map(b -> new Pager.Aggregation(b.getKey(), b.getDocCount()))
                    .collect(Collectors.toList());
            result.put(name, collect);
        }
        return result;
    }
}
