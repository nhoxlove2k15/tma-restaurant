package com.example.tmarestaurant.model;

import com.example.tmarestaurant.utils.MenuOrigin;
import com.example.tmarestaurant.utils.MenuOriginConverter;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "bill_details")
//@Table(name = "bill_details")
@Table
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
//    private Bill bill;
    @Column(name = "discount")
    private double discount = 0.25;
    @Convert(converter = MenuOriginConverter.class)
    @Column(name = "menu_origin")
    private List<MenuOrigin> menuOrigin = new ArrayList<>();

    public BillDetail(double discount, List<MenuOrigin> menuOrigin) {
        this.discount = discount;
        this.menuOrigin = menuOrigin;
    }

    public BillDetail(double discount) {
        this.discount = discount;
    }
}
