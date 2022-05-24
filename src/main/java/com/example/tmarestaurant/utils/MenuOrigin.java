package com.example.tmarestaurant.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MenuOrigin {
    private String name;
    private String description;
    private double price;
    private int quantity;

    public MenuOrigin(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
