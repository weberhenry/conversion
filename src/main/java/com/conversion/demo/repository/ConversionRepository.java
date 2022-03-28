package com.conversion.demo.repository;

import com.conversion.demo.model.ConversionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversionRepository extends JpaRepository<ConversionModel,Long> {

    ConversionModel findByMetricAndConvertFromAndConvertTo(
            String metric,
            String convertFrom,
            String convertTo
    );
}
