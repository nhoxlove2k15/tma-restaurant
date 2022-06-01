package com.example.tmarestaurant.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data


public class RestaurantResponse<T> {

    private Boolean status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int recordCount;
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rootError;

    public RestaurantResponse(Boolean status, String action, String entity, T data) {
        this.status = status;
        this.message = action + " " + entity;
        this.data = data;

    }

    public RestaurantResponse(Boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public RestaurantResponse(Boolean status, String message, int recordCount, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.recordCount = recordCount;
    }
    public RestaurantResponse(Boolean status, String action, String entity, int recordCount, T data) {
        this.status = status;
        this.message = action + " " + entity;
        this.data = data;
        this.recordCount = recordCount;
    }

}
