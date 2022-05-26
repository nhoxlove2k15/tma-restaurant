package com.example.tmarestaurant.dto.response;

import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.model.User;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class RatingResponseDto {
    @JsonAlias(value = "id")
    private Long id;
    // We can see rating from Menu or User so it responses 2 entity
    @JsonAlias(value = "menu")
    private Menu menu;
    @JsonAlias(value = "user")
    private User user;
}
