package com.conversion.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConversionUtil {

    /*------------------------------------------------------------------
     Use SpEL to eval mathematical expression
    */
    ExpressionParser parser = new SpelExpressionParser();

    public String convertMetric(String formula, String value) throws Exception {

        log.info(new Object(){}.getClass().getEnclosingMethod().getName());

        // formula parameter containing place holder "%s"
        // to be replaced by value parameter
        String expression = String.format(formula,value);

        return parser.parseExpression(expression).getValue().toString();
    }
}
