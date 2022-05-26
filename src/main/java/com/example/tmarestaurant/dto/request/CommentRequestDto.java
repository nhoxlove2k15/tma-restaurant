package com.example.tmarestaurant.dto.request;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;



@Data
public class CommentRequestDto {

    @JsonAlias(value = "id")
    private Long id;
    @JsonAlias(value = "content")
    private String content;

    @JsonAlias(value = "user_id")
    private Long userId;
    @JsonAlias(value = "menu_id")
    private Long menuId;

}
