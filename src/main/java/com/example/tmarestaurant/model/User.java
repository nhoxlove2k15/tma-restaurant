package com.example.tmarestaurant.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    //    @OneToMany( mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Bill> bills = new ArrayList<>();
    @JsonIgnore
//    @OneToMany
//    @JoinColumn(name = "user_id")
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
//    @Fetch(FetchMode.JOIN)
      Set<Like> likes = new HashSet<>();
//    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
//    private Set<Rating> ratings = new HashSet<>();
//    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
//    private Set<Comment> comments = new HashSet<>();



    public User(String fullname, String username, String password, String phone, UserRole role) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }


}
