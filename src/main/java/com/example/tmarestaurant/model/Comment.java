package com.example.tmarestaurant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "menu_comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "content", columnDefinition = "text")
    private String content;
    @Column(name = "point")
    private double point;
    @Column(name = "is_toxic")
    private boolean isToxic;

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

    public Comment(String content, Long userId, Long menuId) {
        this.content = content;
        this.userId = userId;
        this.menuId = menuId;
    }
}
