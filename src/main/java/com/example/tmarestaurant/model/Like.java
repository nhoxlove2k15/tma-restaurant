package com.example.tmarestaurant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "menu_likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",updatable = false,insertable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id",updatable = false,insertable = false)
    private Menu menu;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "menu_id")
    private Long menuId;

    public Like(Long userId, Long menuId) {
        this.userId = userId;
        this.menuId = menuId;
    }
}
