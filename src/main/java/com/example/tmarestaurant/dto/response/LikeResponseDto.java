package com.example.tmarestaurant.dto.response;

import com.example.tmarestaurant.model.Menu;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
// We can see like from User and Menu have "likedCount" field, so it response 1 entity
public class LikeResponseDto {
    @JsonAlias(value = "id")
    private Long id;
    @JsonAlias(value = "menu")
    private Menu menu;
}
