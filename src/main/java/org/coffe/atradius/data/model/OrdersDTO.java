package org.coffe.atradius.data.model;

import lombok.Data;

@Data
public class OrdersDTO {
    private String user;
    private String drink;
    private String size;
}