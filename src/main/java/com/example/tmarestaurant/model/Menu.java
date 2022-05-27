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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@Entity
@Table(name = "menus")
public class Menu {
    @Id
    @SequenceGenerator(
            name = "menu_sequence",
            sequenceName = "menu_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "menu_sequence"
    )
    private Long id;
    @Column(name= "name",unique = true)
    private String name;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "images")
    private String images;
    // ["url1","url2","url3"]
//    @Convert(converter = MenuOriginConverter.class)
//    @Column(name = "imagesjson")
//    private Set<MenuOrigin> imagesjson;

    @Column(name = "point")
    private double point;
    @Column(name = "liked_count")
    private int likedCount;
    @JsonIgnore
    @OneToMany(mappedBy = "menu",fetch = FetchType.LAZY)
     List<Like> likes = new ArrayList<>();
    @OneToMany(mappedBy = "menu",fetch = FetchType.LAZY)
    private List<Rating> ratings = new ArrayList<>();
    @OneToMany(mappedBy = "menu",fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "category_id")




//    @Column(name = "category_id")
//    private Long categoryId;

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

//    public Set<MenuOrigin> getImagesjson() {
//        return imagesjson;
//    }
//
//    public void setImagesjson(Set<MenuOrigin> imagesjson) {
//        this.imagesjson = imagesjson;
//    }

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


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }





//    public Menu(String name, String description, double price, String images) {
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.images = images;
//    }


    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

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

//    public Menu(String name, String description, double price, String images, Set<MenuOrigin> imagesjson, Long categoryId) {
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.images = images;
//        this.imagesjson = imagesjson;
//        this.getCategory().setId(categoryId);
//
////        this.categoryId = categoryId;
//    }
}
