package com.example.tmarestaurant.dto.request;

import com.example.tmarestaurant.utils.MenuOrigin;
import com.example.tmarestaurant.utils.MenuOriginConverter;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class BillDetailRequestDto {
    private Long id;
    @JsonAlias(value = "discount")
    private double discount ;
    @JsonAlias(value = "menu_origin")
    private Set<MenuOrigin> menuOrigin = new HashSet<>();
}
