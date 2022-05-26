package com.example.tmarestaurant.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RestaurantResponse<T> {
    private T data;
    private String message;
    private HttpStatus status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rootError;

    public RestaurantResponse(T data, String message, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public RestaurantResponse(T data, String message, HttpStatus status, String rootError) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.rootError = rootError;
    }
}
