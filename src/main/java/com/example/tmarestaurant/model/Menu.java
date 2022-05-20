package com.example.tmarestaurant.model;

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
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "images", columnDefinition = "json")
    @JsonRawValue
    private List<String> images;
    @Column(name = "point")
    private double point;
    @Column(name = "liked_count")
    private int likedCount;
    @OneToMany(mappedBy = "menu")
    private Set<Like> likes = new HashSet<>();
    @OneToMany(mappedBy = "menu")
    private Set<Rating> ratings = new HashSet<>();
    @OneToMany(mappedBy = "menu")
    private Set<Comment> comments = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
