package com.example.tmarestaurant.dto;

import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.dto.response.UserResponseDto;
import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.model.User;

import java.util.ArrayList;
import java.util.List;

public class mapper {
    public static UserResponseDto userToUserResponseDto(User user) {

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFullname(user.getFullname());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setBills(user.getBills());
        userResponseDto.setComments(user.getComments());
        userResponseDto.setRole(user.getRole());
        userResponseDto.setRatings(user.getRatings());
        userResponseDto.setPhone(user.getPhone());
        return userResponseDto;
    }
    public static List<UserResponseDto> usersToUserResponseDtos(List<User> users) {

        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for(User user : users) {
            userResponseDtos.add( userToUserResponseDto(user) );
        }
        return userResponseDtos;
    }
    public static MenuResponseDto menuToMenuResponseDto(Menu menu) {
        MenuResponseDto menuResponseDto = new MenuResponseDto();
        menuResponseDto.setId(menu.getId());
        menuResponseDto.setCategory(menu.getCategory());
        menuResponseDto.setDescription(menu.getDescription());
        menuResponseDto.setImages(menu.getImages());
        menuResponseDto.setComments(menu.getComments());
        menuResponseDto.setLikes(menu.getLikes());
        menuResponseDto.setRatings(menu.getRatings());
        menuResponseDto.setPrice(menu.getPrice());

        return menuResponseDto;
    }
    public static List<MenuResponseDto> menusToMenuResponseDtos(List<Menu> menus) {
        List<MenuResponseDto> menuResponseDtos = new ArrayList<>();
        for(Menu menu : menus) {
            menuResponseDtos.add(menuToMenuResponseDto(menu));
        }
        return menuResponseDtos;
    }
}
