package com.example.tmarestaurant.dto;

import com.example.tmarestaurant.dto.request.BillDetailRequestDto;
import com.example.tmarestaurant.dto.response.BillResponseDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.dto.response.UserResponseDto;
import com.example.tmarestaurant.model.Bill;
import com.example.tmarestaurant.model.BillDetail;
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
        System.out.println("================================" + billDetailRequestDto.toString());
//        System.out.println("================================" + billDetailRequestDto.);

        System.out.println("================================" + billDetail.toString());
        return billDetail;
    }
}
