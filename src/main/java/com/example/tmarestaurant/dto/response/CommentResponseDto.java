package com.example.tmarestaurant.dto.response;

import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.model.User;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class CommentResponseDto {

    @JsonAlias(value = "content")
    private String content;
    @JsonAlias(value = "point")
    private double point;
    @JsonAlias(value = "is_toxic")
    private boolean isToxic;
    @JsonAlias(value = "user")
    private User user;
    @JsonAlias(value = "menu")
    private Menu menu;

}
