package com.example.tmarestaurant.dto;

import com.example.tmarestaurant.dto.request.BillDetailRequestDto;
import com.example.tmarestaurant.dto.response.BillResponseDto;
import com.example.tmarestaurant.dto.response.LikeResponseDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.dto.response.UserResponseDto;
import com.example.tmarestaurant.model.*;

import java.util.ArrayList;
import java.util.List;

public class mapper {
    public static UserResponseDto userToUserResponseDto(User user) {

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFullname(user.getFullname());
        userResponseDto.setUsername(user.getUsername());
//        userResponseDto.setBills(user.getBills());
//        userResponseDto.setComments(user.getComments());
//        userResponseDto.setRatings(user.getRatings());

        userResponseDto.setRole(user.getRole());
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
//        menuResponseDto.setComments(menu.getComments());
//        menuResponseDto.setRatings(menu.getRatings());

//        menuResponseDto.setLikes(menu.getLikes());
        menuResponseDto.setPrice(menu.getPrice());
        menuResponseDto.setLikedCount(menu.getLikedCount());

        return menuResponseDto;
    }
    public static List<MenuResponseDto> menusToMenuResponseDtos(List<Menu> menus) {
        List<MenuResponseDto> menuResponseDtos = new ArrayList<>();
        for(Menu menu : menus) {
            menuResponseDtos.add(menuToMenuResponseDto(menu));
        }
        return menuResponseDtos;
    }

    public static BillResponseDto billToBillResponseDto(Bill bill) {
        BillResponseDto billResponseDto = new BillResponseDto();

        billResponseDto.setId(bill.getId());
        billResponseDto.setTotalprice(bill.getTotalprice());
        billResponseDto.setBillDetails(bill.getBillDetails());
//        billResponseDto.setUser(bill.getUser());

        return  billResponseDto;
    }

    public static List<BillResponseDto> billsToResponseDtos(List<Bill> bills) {
        List<BillResponseDto> billResponseDtos = new ArrayList<>();
        for (Bill bill : bills) {
            billResponseDtos.add(billToBillResponseDto(bill));
        }
        return billResponseDtos;
    }
    public static BillDetail billDetailRequestToBillDetail(BillDetailRequestDto billDetailRequestDto) {
        BillDetail billDetail = new BillDetail();
        billDetail.setDiscount(billDetailRequestDto.getDiscount());
        billDetail.setId(billDetailRequestDto.getId());
        billDetail.setMenuOrigin(billDetailRequestDto.getMenuOrigin());
        return billDetail;
    }

    public static LikeResponseDto likeToLikeResponseDto(Like like) {
        LikeResponseDto likeResponseDto = new LikeResponseDto();
//        MenuResponseDto re = menuToMenuResponseDto(like.getMenu());
        System.out.println("===============================================" + like.getMenu() );

        likeResponseDto.setMenu(like.getMenu());
        System.out.println("===============================================" + likeResponseDto.getMenu() );

//        System.out.println("===============================================" + re );

        return likeResponseDto;
    }
    public static List<LikeResponseDto> likeResponseDtos (List<Like> likes) {
        List<LikeResponseDto> likeResponseDtos = new ArrayList<>();

        for (Like like : likes) {
            likeResponseDtos.add(likeToLikeResponseDto(like));
        }
        System.out.println("===============================================" + "likes menus ");
        return likeResponseDtos;
    }
}
