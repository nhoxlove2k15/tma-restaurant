package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.request.BillRequestDto;
import com.example.tmarestaurant.dto.request.MenuRequestDto;
import com.example.tmarestaurant.dto.response.BillResponseDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.model.Bill;
import com.example.tmarestaurant.model.Menu;

import java.util.List;

public interface MenuService {
    public MenuResponseDto addMenu(MenuRequestDto menuRequestDto);
    public MenuResponseDto getMenuById(Long menuId);
    public Menu getMenu(Long menuId);
    public List<MenuResponseDto> getMenus();
    public MenuResponseDto deleteMenu(Long menuId);
    public MenuResponseDto editMenu(Long menuId, MenuRequestDto menuRequestDto);
//    public BillResponseDto addAuthorToBill(Long BillId, Long authorId);
//    public BillResponseDto deleteAuthorFromBill(Long BillId, Long authorId);
//    public BillResponseDto addCategoryToBill(Long BillId, Long categoryId);
//    public BillResponseDto removeCategoryFromBill(Long BillId, Long categoryId);
}
