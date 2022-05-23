package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.request.MenuRequestDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.model.Menu;

import java.util.List;

public interface MenuService {
    public MenuResponseDto addMenu(MenuRequestDto menuRequestDto);
    public MenuResponseDto getMenuById(Long menuId);
    public Menu getMenu(Long menuId);
    public List<MenuResponseDto> getMenus();
    public MenuResponseDto deleteMenu(Long menuId);
    public MenuResponseDto editMenu(Long menuId, MenuRequestDto menuRequestDto);
//    public MenuResponseDto addAuthorToMenu(Long MenuId, Long authorId);
//    public MenuResponseDto deleteAuthorFromMenu(Long MenuId, Long authorId);
//    public MenuResponseDto addCategoryToMenu(Long MenuId, Long categoryId);
//    public MenuResponseDto removeCategoryFromMenu(Long MenuId, Long categoryId);
}
