package com.conversion.demo.service;

import com.conversion.demo.model.ConversionModel;

import java.util.List;

public interface ConversionService {

    Iterable<ConversionModel> save(
            List<ConversionModel> conversions
    ) throws Exception;

    String convertMetric(String metric,
                   String converFrom,
                   String converTo,
                   String value
    ) throws Exception;

    List<String> getMetricCategories();

    List<String> getFromMetricsInCategory(String metricCategory);
    List<String> getFromMetricsInCategoryExclFromMetric(String metricCategory, String metric);
}
