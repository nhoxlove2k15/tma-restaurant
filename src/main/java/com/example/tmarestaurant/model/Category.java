package com.example.tmarestaurant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Menu> menus;
}
