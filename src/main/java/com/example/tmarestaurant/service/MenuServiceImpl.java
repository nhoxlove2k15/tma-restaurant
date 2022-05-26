package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.mapper;
import com.example.tmarestaurant.dto.request.MenuRequestDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.model.User;
import com.example.tmarestaurant.repository.MenuRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class MenuServiceImpl implements MenuService{
//    @Autowired @Lazy
    private  final MenuRepository menuRepository;
//    @Autowired @Lazy
    private  final LikeService likeService;


    @Autowired
    public MenuServiceImpl(@NonNull MenuRepository menuRepository, @NonNull @Lazy LikeService likeService) {
        this.menuRepository = menuRepository;
        this.likeService = likeService;
    }

//    public LikeService getLikeService() {
//        return likeService;
//    }
//    @PostConstruct
//    public void init() {
//
//        likeService.setMenuService(this);
//    }
//    public void setLikeService(LikeServiceImpl likeService) {
//        this.likeService = likeService;
//    }

//    @Autowired
//    public MenuServiceImpl(MenuRepository menuRepository) {
//        this.menuRepository = menuRepository;
//    }

    @Override
    public MenuResponseDto addMenu(MenuRequestDto menuRequestDto) {
        Menu menu = new Menu();
        menu.setDescription(menuRequestDto.getDescription());
        menu.setPrice(menuRequestDto.getPrice());
        // need category repository
//        menu.setCategory(menuRequs);
        menuRepository.save(menu);
        return mapper.menuToMenuResponseDto(menu);
    }

    @Override
    public MenuResponseDto getMenuById(Long menuId) {
        Menu menu = getMenu(menuId);
//        int a = 2 ;

        return mapper.menuToMenuResponseDto(menu);
    }

    @Override
    public Menu getMenu(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(
                        () -> new IllegalArgumentException("menu with id " + menuId + " could not be found")
                );
        System.out.println("========================================================================================" + 1);

        System.out.println("========================================================================================" + 2);
        menu.setLikedCount(likeService.getLikedCount(menu.getId()));
        menuRepository.save(menu);

        return menu;
    }

    @Override
    public List<MenuResponseDto> getMenus() {
        List<Menu> menus = StreamSupport
                .stream(menuRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return mapper.menusToMenuResponseDtos(menus);

    }

    @Override
    public MenuResponseDto deleteMenu(Long menuId) {
        Menu menu = getMenu(menuId);
        menuRepository.deleteById(menuId);
        return  mapper.menuToMenuResponseDto(menu);
    }

    @Override
    public MenuResponseDto editMenu(Long menuId, MenuRequestDto menuRequestDto) {
        Menu menuToEdit = getMenu(menuId);
        menuToEdit.setDescription(menuRequestDto.getDescription());
        menuToEdit.setPrice(menuRequestDto.getPrice());
        menuRepository.save(menuToEdit);

        return mapper.menuToMenuResponseDto(menuToEdit);
    }
}
