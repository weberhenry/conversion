package com.conversion.demo.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class ConversionServiceTest {

    @Autowired
    ConversionService conversionService;

    final String METRIC = "Length";
    final String FROMMETRIC = "Millimetre";
    final String TOMETRIC = "Kilometre";
    final String VALUE = "1";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Service Integration Test - Available Categories should return 5 records")
    void getMetricCategories() {

        // Given

        // When
        List<String> metricCategories = conversionService.getMetricCategories();

        // Then .............................................
        assertEquals(metricCategories.size(), 5);
    }

    @Test
    @DisplayName("Service Integration Test - Length Metric Conversion should return 1.0E-6")
    void convertMetric() {

        // Given

        // When
        String result = null;
        try {
            result = conversionService.convertMetric(
                    METRIC,
                    FROMMETRIC,
                    TOMETRIC,
                    VALUE
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Then
        assertEquals(result, "1.0E-6");
    }

    @Test
    @DisplayName("Service Integration Test - Get Metrics in Metric Category should return 2")
    public void getFromMetricsInCategory() {

        // Given

        // When
        List<String> list = null;
        try {
            list = conversionService.getFromMetricsInCategory(
                    METRIC
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Then
        assertEquals(list.size(), 2);
    }


    @Test
    @DisplayName("Service Integration Test - Get Metrics in Metric Category Excl From Metric should return 1")
    public void getFromMetricsInCategoryExclFromMetric() {

        // Given

        // When
        List<String> list = null;
        try {
            list = conversionService.getFromMetricsInCategoryExclFromMetric(
                    METRIC,
                    FROMMETRIC
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Then
        assertEquals(list.size(), 1);
    }
}
