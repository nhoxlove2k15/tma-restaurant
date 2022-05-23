package com.example.tmarestaurant.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private double discount;
    @Column(name = "menu_origin", columnDefinition = "json")
    private String menuOrigin;

}
