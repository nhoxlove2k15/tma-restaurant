package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.request.BillRequestDto;
import com.example.tmarestaurant.dto.request.MenuRequestDto;
import com.example.tmarestaurant.dto.response.BillResponseDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.model.Bill;
import com.example.tmarestaurant.model.Menu;

import java.util.List;

public interface MenuService {
    MenuResponseDto addMenu(MenuRequestDto menuRequestDto);
    MenuResponseDto getMenuById(Long menuId);
    Menu getMenu(Long menuId);
    List<MenuResponseDto> getMenus();
    void deleteMenu(Long menuId);
    void editMenu(Long menuId, MenuRequestDto menuRequestDto);
    List<MenuResponseDto> searchMenuByNameAndDescription(String queryString);
//    public BillResponseDto addAuthorToBill(Long BillId, Long authorId);
//    public BillResponseDto deleteAuthorFromBill(Long BillId, Long authorId);
//    public BillResponseDto addCategoryToBill(Long BillId, Long categoryId);
//    public BillResponseDto removeCategoryFromBill(Long BillId, Long categoryId);
}
