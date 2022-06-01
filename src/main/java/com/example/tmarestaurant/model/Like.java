package com.example.tmarestaurant.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;


@Entity
@Table(name = "menu_likes")

public class Like {
    @Id
    @SequenceGenerator(
            name = "like_sequence",
            sequenceName = "like_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "like_sequence"
    )
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "user_id")
    User user ;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    Menu menu ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Like() {
        this.user = new User();
        this.menu = new Menu();
    }

    public Like(Long userId, Long menuId) {
        this.user.setId(userId);
        this.menu.setId(menuId);
    }
}
