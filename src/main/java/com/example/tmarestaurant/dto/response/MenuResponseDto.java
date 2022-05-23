package com.example.tmarestaurant.dto.response;

import com.example.tmarestaurant.model.Comment;
import com.example.tmarestaurant.model.Like;
import com.example.tmarestaurant.model.Rating;
import lombok.Data;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

@Data
public class MenuResponseDto {

    private Long id;
    private String description;

    private double price;

    private JSONObject images;
    private double point;
    private int likedCount;
    private Set<Like> likes = new HashSet<>();

    private Set<Rating> ratings = new HashSet<>();

    private Set<Comment> comments = new HashSet<>();

    private Long category;
}
