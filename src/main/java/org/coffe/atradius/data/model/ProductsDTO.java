package org.coffe.atradius.data.model;

import lombok.Data;

import java.util.Map;

@Data
public class ProductsDTO {
    private String drink_name;
    private Map<String, Double> prices;
}