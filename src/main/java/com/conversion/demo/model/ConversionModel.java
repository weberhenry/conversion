package com.conversion.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
public class ConversionModel {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    private String metric;
    private String convertTo;
    private String convertFrom;
    private String formula;

    public ConversionModel() {
    }
}
