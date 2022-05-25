package com.example.tmarestaurant.dto.response;

import com.example.tmarestaurant.model.Menu;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class LikeResponseDto {
//    @JsonAlias(value = "user_id")
//    private Long userId;
//    @JsonAlias(value = "menu")
//    private Long menuId;
    @JsonAlias(value = "menu")
    private Menu menu;
}
