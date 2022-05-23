package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.request.UserRegisterDto;
import com.example.tmarestaurant.dto.response.UserResponseDto;
import com.example.tmarestaurant.model.User;

import java.util.List;

public interface UserService {
    public UserResponseDto addUser (UserRegisterDto userRegisterDto);
    public List<UserResponseDto> getUsers();
    public UserResponseDto getUserById(Long userId);
    public User getUser(Long UserId);
    public UserResponseDto deleteUser(Long userId);
    public UserResponseDto editUser(Long userId, UserRegisterDto userRegisterDto);
//    public UserResponseDto addZipcodeToUsers(Long UsersId, Long zipcodeId);
//    public UserResponseDto deleteZipcodeFromUsers(Long UsersId);
}
