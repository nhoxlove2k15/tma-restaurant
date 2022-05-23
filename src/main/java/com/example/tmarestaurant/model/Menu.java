package com.example.tmarestaurant.model;

import com.example.tmarestaurant.utils.ImageConverter;
import com.example.tmarestaurant.utils.MenuOrigin;
import com.example.tmarestaurant.utils.MenuOriginConverter;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name= "name")
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "images")
    private String images;
    // ["url1","url2","url3"]
    @Convert(converter = MenuOriginConverter.class)
    @Column(name = "imagesjson")
    private List<MenuOrigin> imagesjson;

    @Column(name = "point")
    private double point;
    @Column(name = "liked_count")
    private int likedCount;
    @OneToMany(mappedBy = "menu",fetch = FetchType.LAZY)
    private Set<Like> likes = new HashSet<>();
    @OneToMany(mappedBy = "menu",fetch = FetchType.LAZY)
    private Set<Rating> ratings = new HashSet<>();
    @OneToMany(mappedBy = "menu",fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;
    @Column(name = "category_id")
    private Long categoryId;



//    public Menu(String name, String description, double price, String images) {
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.images = images;
//    }

    public Menu(String name, String description, double price, String images, Long categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.images = images;
        this.categoryId = categoryId;
    }

//    public Menu(String name, String description, double price, String images, List<String> imagesjson, Long categoryId) {
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.images = images;
//        this.imagesjson = imagesjson;
//        this.categoryId = categoryId;
//    }

    public Menu(String name, String description, double price, String images, List<MenuOrigin> imagesjson, Long categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.images = images;
        this.imagesjson = imagesjson;
        this.categoryId = categoryId;
    }
}
