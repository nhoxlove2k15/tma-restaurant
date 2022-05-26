package com.example.tmarestaurant.dto.request;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;




@Data
public class MenuRequestDto {


    private String name;
    private String description;
    private double price;
//    private JSONObject images;
//    private double point;
//    private int likedCount;
    @JsonAlias(value = "category_id")
    private int categoryId;


}
