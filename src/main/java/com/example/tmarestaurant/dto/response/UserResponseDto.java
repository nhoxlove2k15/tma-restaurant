package com.example.tmarestaurant.dto.response;

import com.example.tmarestaurant.model.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class UserResponseDto {
        private Long id;
        private String fullname;
        private String username;

        private String phone;

        private UserRole role;

        private List<Bill> bills = new ArrayList<>();
        private Set<Like> likes = new HashSet<>();
        private Set<Rating> ratings = new HashSet<>();
        private Set<Comment> comments = new HashSet<>();
    }


