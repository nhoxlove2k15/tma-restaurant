package com.example.tmarestaurant.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bill_details")
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Bill bill;
    @Column(name = "discount")
    private double discount;
    @Column(name = "menu_origin", columnDefinition = "json")
    @JsonRawValue
    private List<Menu> menuOrigin;

}
