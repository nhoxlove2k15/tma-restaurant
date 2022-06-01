package com.example.tmarestaurant.dto.response;

import com.example.tmarestaurant.model.Category;
import com.example.tmarestaurant.model.Comment;
import com.example.tmarestaurant.model.Like;
import com.example.tmarestaurant.model.Rating;
import lombok.Data;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class MenuResponseDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String images;
    private double point;
    private int likedCount;
    private List<Like> likes = new ArrayList<>();
    private List<Rating> ratings = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private Category category;
}
