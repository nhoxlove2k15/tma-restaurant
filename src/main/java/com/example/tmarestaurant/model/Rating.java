package com.example.tmarestaurant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "menu_ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "point")
    private double point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false,updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id",insertable = false,updatable = false)
    private Menu menu;

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "menu_id")
    private Long menuId;

    public Rating(double point, Long userId, Long menuId) {
        this.point = point;
        this.userId = userId;
        this.menuId = menuId;
    }
}
