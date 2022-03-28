package com.conversion.demo.controler;

import com.conversion.demo.DemoApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConversionControlerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    final String METRIC = "Length";
    final String CONVERTFROM = "Millimetre";
    final String CONVERTTO = "Kilometre";
    final String VALUE = "1";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Controller Integration Test - get Metric Conversion")
    void getMetricConversion() {

        // Given
        String uriSuffix = "/convert/%s/%s/%s/%s";
        String uri = createURLWithPort(uriSuffix);
        String urlParsed = String.format(uri,METRIC, CONVERTFROM,CONVERTTO,VALUE);

        // When
        ResponseEntity<String> response = restTemplate.getForEntity(urlParsed,String.class);

        // Then
        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Test
    @DisplayName("Controller Integration Test - get Metric Categories")
    void MetricCategories() {

        // Given
        String uriSuffix = "/convert/list";
        String uri = createURLWithPort(uriSuffix);

        // When
        ResponseEntity<String[]> response = restTemplate.getForEntity(uri,String[].class);
        List<String> metricCategories = Arrays.asList(response.getBody());

        // Then
        assertEquals(metricCategories.size(),5);
    }

    @Test
    @DisplayName("Controller Integration Test - get Metrics in Metric Category")
    void MetricsInMetricCategory() {

        // Given
        String uriSuffix = "/convert/%s";
        String uri = createURLWithPort(uriSuffix);
        String urlParsed = String.format(uri,METRIC);

        // When
        ResponseEntity<String[]> response = restTemplate.getForEntity(urlParsed,String[].class);
        List<String> list = Arrays.asList(response.getBody());

        // Then
        assertEquals(list.size(),2);
    }

    @Test
    @DisplayName("Controller Integration Test - get Metrics in Metric Category excl from metric")
    void MetricsInMetricCategoryExlFromMetric() {

        // Given
        String uriSuffix = "/convert/%s/%s";
        String uri = createURLWithPort(uriSuffix);
        String urlParsed = String.format(uri,METRIC,CONVERTFROM);

        // When
        ResponseEntity<String[]> response = restTemplate.getForEntity(urlParsed,String[].class);
        List<String> list = Arrays.asList(response.getBody());

        // Then
        assertEquals(list.size(),1);
    }

    private String createURLWithPort(String uriSuffix) {

        return "http://localhost:" + port + "/conversion" + uriSuffix;
    }
}
