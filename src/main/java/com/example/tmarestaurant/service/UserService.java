package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.request.UserRegisterDto;
import com.example.tmarestaurant.dto.response.UserResponseDto;
import com.example.tmarestaurant.model.User;

import java.util.List;

public interface UserService {
    UserResponseDto addUser (UserRegisterDto userRegisterDto);
    List<UserResponseDto> getUsers();
    UserResponseDto getUserById(Long userId);
    User getUser(Long UserId);
    void deleteUser(Long userId);
    void editUser(Long userId, UserRegisterDto userRegisterDto);

}
