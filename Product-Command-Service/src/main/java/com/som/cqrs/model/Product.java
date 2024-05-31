package com.som.cqrs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String productName;
    private String description;
    private BigDecimal price;
}
