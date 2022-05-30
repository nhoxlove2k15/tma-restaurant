package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.request.BillRequestDto;
import com.example.tmarestaurant.dto.request.MenuRequestDto;
import com.example.tmarestaurant.dto.response.BillResponseDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.model.Bill;
import com.example.tmarestaurant.model.Comment;
import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.model.Rating;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface MenuService {
    MenuResponseDto addMenu(MenuRequestDto menuRequestDto);
    MenuResponseDto getMenuById(Long menuId);
    Menu getMenu(Long menuId);
    List<MenuResponseDto> getMenus();
    void deleteMenu(Long menuId);
    void editMenu(Long menuId, MenuRequestDto menuRequestDto);
    List<MenuResponseDto> searchMenuByNameAndDescription(String queryString);
    List<MenuResponseDto> sortMenuByField(String field, String mode);
    List<MenuResponseDto> getMenus(int offset, int pageSize);
    int caculatedPoint(List<Rating> ratings, List<Comment> comments);
}
