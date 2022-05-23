package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.MenuRequestDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.service.MenuService;
import com.example.tmarestaurant.utils.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;


    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    @PostMapping("/addMenu")
    public ResponseEntity<MenuResponseDto> addMenu(@RequestBody final MenuRequestDto menuRequestDto) {
        MenuResponseDto menuResponseDto = menuService.addMenu(menuRequestDto);
        return new ResponseEntity<>(menuResponseDto, HttpStatus.OK);
    }
    @GetMapping("get/{id}")
    public RestaurantResponse<MenuResponseDto> getMenu(@PathVariable final Long id) {
        MenuResponseDto menuResponseDto = menuService.getMenuById(id);
        RestaurantResponse response = new RestaurantResponse(menuResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
    @GetMapping("/getAll")
    public RestaurantResponse<List<MenuResponseDto>> getMenus() {
        List<MenuResponseDto> menuResponseDtos = menuService.getMenus();
        RestaurantResponse response = new RestaurantResponse(menuResponseDtos,"Add successfully", HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public RestaurantResponse<MenuResponseDto> deleteMenu(@PathVariable final Long id) {
        MenuResponseDto menuResponseDto = menuService.deleteMenu(id);
        RestaurantResponse response = new RestaurantResponse(menuResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
    @PostMapping("/edit/{id}")
    public RestaurantResponse<MenuResponseDto> editMenu(@PathVariable final Long id,
                                                        @RequestBody final MenuRequestDto MenuRequestDto) {
        MenuResponseDto menuResponseDto = menuService.editMenu(id,MenuRequestDto);
        RestaurantResponse response = new RestaurantResponse(menuResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
}
