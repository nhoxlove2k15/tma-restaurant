package com.example.tmarestaurant.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Entity
@Table(name = "menu_ratings")
public class Rating {
    @Id
    @SequenceGenerator(
            name = "rating_sequence",
            sequenceName = "rating_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rating_sequence"
    )
    private Long id;

    @Column(name = "point")
    private double point;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")

    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    private Menu menu;

//    @Column(name = "user_id")
//    private Long userId;
//    @Column(name = "menu_id")
//    private Long menuId;


    public Rating() {
        this.user = new User();
        this.menu = new Menu();
    }

    public Rating(double point, Long userId, Long menuId) {
        this.point = point;
        this.getUser().setId(userId);
        this.getMenu().setId(menuId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
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
}
