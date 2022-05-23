package com.example.tmarestaurant.model;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "fullname",nullable = false)
    private String fullname;
    @Column(name = "username",nullable = false,unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "salt")
    private String salt;
    @Column(name = "phone",unique = true)
    private String phone;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role")
    private UserRole role = UserRole.CUSTOMER;

    @OneToMany( mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bill> bills = new ArrayList<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Like> likes = new HashSet<>();
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Rating> ratings = new HashSet<>();
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();



    public User(String fullname, String username, String password, String phone, UserRole role) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }


}
