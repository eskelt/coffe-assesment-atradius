package org.coffe.atradius.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {
    private String drink_name;
    private Map<String, Double> prices;
}