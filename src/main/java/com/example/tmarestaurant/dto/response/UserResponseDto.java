package com.example.tmarestaurant.dto.response;

import com.example.tmarestaurant.model.*;
import com.fasterxml.jackson.annotation.JsonInclude;
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
        private List<Like> likes = new ArrayList<>();
        private List<Rating> ratings = new ArrayList<>();
        private List<Comment> comments = new ArrayList<>();
    }


