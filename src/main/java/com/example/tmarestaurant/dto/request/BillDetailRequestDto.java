package com.example.tmarestaurant.dto.request;

import com.example.tmarestaurant.utils.MenuOrigin;
import com.example.tmarestaurant.utils.MenuOriginConverter;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BillDetailRequestDto {
    private Long id;
    //    private Bill bill;
    @JsonAlias(value = "discount")
    private double discount ;
    @JsonAlias(value = "menu_origin")
    private List<MenuOrigin> menuOrigin = new ArrayList<>();
}
