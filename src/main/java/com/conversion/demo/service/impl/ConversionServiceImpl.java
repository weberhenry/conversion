package com.conversion.demo.service.impl;

import com.conversion.demo.model.ConversionModel;
import com.conversion.demo.repository.ConversionRepository;
import com.conversion.demo.service.ConversionService;
import com.conversion.demo.util.ConversionUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ConversionServiceImpl implements ConversionService {

    @Autowired
    ConversionRepository conversionRepository;

    @Autowired
    ConversionUtil conversionUtil;

    @Override
    public Iterable<ConversionModel> save(
            List<ConversionModel> conversions) throws Exception {

        return conversionRepository.saveAll(conversions);
    }

    @Override
    public String convertMetric(
            String metric, String convertFrom, String convertTo, String value
    ) throws Exception {

        log.info(new Object(){}.getClass().getEnclosingMethod().getName());

        ConversionModel conversionModel =
                conversionRepository.findByMetricAndConvertFromAndConvertTo(
                        metric,
                        convertFrom,
                        convertTo
                );

        return conversionUtil.convertMetric(conversionModel.getFormula(),value);
    }

    @Override
    public List<String> getMetricCategories() {

        return conversionRepository.findAll()
                .stream()
                .map(ConversionModel::getMetric)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getFromMetricsInCategory(final String metricCategory) {

        return conversionRepository.findAll()
                .stream()
                .filter(c -> Objects.equals(c.getMetric(),metricCategory))
                .map(ConversionModel::getConvertFrom)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getFromMetricsInCategoryExclFromMetric(
            final String metricCategory,
            final String fromMetric
    ) {

        return conversionRepository.findAll()
                .stream()
                .filter(c -> Objects.equals(c.getMetric(),metricCategory))
                .filter(c -> !Objects.equals(c.getConvertTo(),fromMetric))
                .map(ConversionModel::getConvertTo)
                .distinct()
                .collect(Collectors.toList());
    }
}
