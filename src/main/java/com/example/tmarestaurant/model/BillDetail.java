package com.example.tmarestaurant.model;

import com.example.tmarestaurant.utils.MenuOrigin;
import com.example.tmarestaurant.utils.MenuOriginConverter;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@Entity
@Table(name = "bill_details")
public class BillDetail {
    @Id
    @SequenceGenerator(
            name = "bill_detail_sequence",
            sequenceName = "bill_detail_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bill_detail_sequence"
    )
    private Long id;
//    private Bill bill;
    @Column(name = "discount")
    private double discount = 0 ;
    @Convert(converter = MenuOriginConverter.class)
    @Column(name = "menu_origin")
    private Set<MenuOrigin> menuOrigin = new HashSet<>();

    public BillDetail(Set<MenuOrigin> menuOrigin) {
        this.menuOrigin = menuOrigin;
    }

    public BillDetail(double discount) {
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


    public Set<MenuOrigin> getMenuOrigin() {
        return menuOrigin;
    }

    public void setMenuOrigin(Set<MenuOrigin> menuOrigin) {
        this.menuOrigin = menuOrigin;
    }
}
