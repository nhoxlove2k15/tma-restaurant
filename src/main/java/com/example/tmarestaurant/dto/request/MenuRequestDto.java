package com.example.tmarestaurant.dto.request;

import com.example.tmarestaurant.model.Category;
import com.example.tmarestaurant.model.Comment;
import com.example.tmarestaurant.model.Like;
import com.example.tmarestaurant.model.Rating;
import lombok.Data;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
public class MenuRequestDto {


    private String description;
    private double price;
//    private JSONObject images;
//    private double point;
//    private int likedCount;
    private Long category;


}
