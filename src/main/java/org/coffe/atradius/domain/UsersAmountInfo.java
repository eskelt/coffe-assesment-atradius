package org.coffe.atradius.domain;

import lombok.Data;

@Data
public class UsersAmountInfo {
    public UsersAmountInfo(String name){
        this.name = name;
    }
    private String name;
    private Double paid;
    private Double owed;
    private Double totalPrice = 0d;
}
