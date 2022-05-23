package com.example.tmarestaurant.dto.request;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String username;
    private String password;
    private String fullname;
    private String phone;
}
