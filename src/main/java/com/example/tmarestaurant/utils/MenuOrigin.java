package com.example.tmarestaurant.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MenuOrigin {
    private String name;
    private String description;
    private double price;

    public MenuOrigin(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
