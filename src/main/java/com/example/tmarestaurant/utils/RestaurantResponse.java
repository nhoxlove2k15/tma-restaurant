package com.example.tmarestaurant.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RestaurantResponse<T> {
    private T data;
    private String message;
    private HttpStatus status;

    public RestaurantResponse(T data, String message, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }
}
