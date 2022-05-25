package com.example.tmarestaurant.model;

import com.example.tmarestaurant.utils.ImageConverter;
import com.example.tmarestaurant.utils.MenuOrigin;
import com.example.tmarestaurant.utils.MenuOriginConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    private Set<MenuOrigin> imagesjson;

    @Column(name = "point")
    private double point;
    @Column(name = "liked_count")
    private int likedCount;
    @JsonIgnore
    @OneToMany(mappedBy = "menu",fetch = FetchType.LAZY)
//    @Fetch(FetchMode.JOIN)
     Set<Like> likes = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "category_id")

    private Category category = new Category();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Set<MenuOrigin> getImagesjson() {
        return imagesjson;
    }

    public void setImagesjson(Set<MenuOrigin> imagesjson) {
        this.imagesjson = imagesjson;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public int getLikedCount() {
        return likedCount;
    }

    public void setLikedCount(int likedCount) {
        this.likedCount = likedCount;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    //    @OneToMany(mappedBy = "menu",fetch = FetchType.EAGER)
//    private Set<Rating> ratings = new HashSet<>();
//    @OneToMany(mappedBy = "menu",fetch = FetchType.EAGER)
//    private Set<Comment> comments = new HashSet<>();

//    @Column(name = "category_id")
//    private Long categoryId;



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
//        this.setCategory() = new Category();
        this.getCategory().setId(categoryId);
    }

//    public Menu(String name, String description, double price, String images, List<String> imagesjson, Long categoryId) {
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.images = images;
//        this.imagesjson = imagesjson;
//        this.categoryId = categoryId;
//    }

    public Menu(String name, String description, double price, String images, Set<MenuOrigin> imagesjson, Long categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.images = images;
        this.imagesjson = imagesjson;
        this.getCategory().setId(categoryId);

//        this.categoryId = categoryId;
    }
}
