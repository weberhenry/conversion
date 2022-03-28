package com.conversion.demo.controler;

import com.conversion.demo.service.ConversionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@Api(value="Conversion API", description="Operations pertaining to Metric Conversion")
public class ConversionControler {

    @Autowired
    ConversionService conversionService;

    @GetMapping("/convert/{metric}/{convertFrom}/{convertTo}/{value}")
    @Validated
    public ResponseEntity<?> getMetricConversion  (
            @ApiParam(value = "metric", name="metric") @PathVariable @NotBlank String metric,
            @ApiParam(value = "convertFrom", name="convertFrom") @PathVariable @NotBlank String convertFrom,
            @ApiParam(value = "convertTo", name="convertTo") @PathVariable @NotBlank String convertTo,
            @ApiParam(value = "value", name="value") @PathVariable @NotBlank String value
            ) throws Exception {

        log.info(new Object(){}.getClass().getEnclosingMethod().getName());

        return new ResponseEntity<Object>(
                conversionService.convertMetric(metric, convertFrom, convertTo, value),
                HttpStatus.OK
        );
    }

    @GetMapping("/convert/list")
    public List<String> MetricCategories() {

        return conversionService.getMetricCategories();
    }

    @GetMapping("/convert/{metric}")
    @Validated
    public List<String> MetricsInMetricCategory(
            @ApiParam(value = "metric", name="metric") @PathVariable @NotBlank String metric
    ) {

        return conversionService.getFromMetricsInCategory(
                metric
        );
    }

    @GetMapping("/convert/{metric}/{convertFrom}")
    @Validated
    public List<String> MetricsInMetricCategoryExlFromMetric(
            @ApiParam(value = "metric", name="metric") @PathVariable @NotBlank String metric,
            @ApiParam(value = "convertFrom", name="convertFrom") @PathVariable @NotBlank String convertFrom
    ) {

        return conversionService.getFromMetricsInCategoryExclFromMetric(
                metric,
                convertFrom
        );
    }
}
