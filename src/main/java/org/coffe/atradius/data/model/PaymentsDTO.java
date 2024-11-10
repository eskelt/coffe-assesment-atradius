package org.coffe.atradius.data.model;

import lombok.Data;

@Data
public class PaymentsDTO {
    private String user;
    private Double amount;
}