package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.MenuRequestDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.service.MenuService;
import com.example.tmarestaurant.utils.MyConstant;
import com.example.tmarestaurant.utils.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;
    private MenuResponseDto menuResponseDto;
    private RestaurantResponse response;


    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    @PostMapping("/addMenu")
    public RestaurantResponse<MenuResponseDto> addMenu(@RequestBody final MenuRequestDto menuRequestDto) {
        try {
            menuResponseDto = menuService.addMenu(menuRequestDto);

        } catch (Exception e) {
            response = new RestaurantResponse(false, e.getMessage(), null);
            return response;
        }

        response = new RestaurantResponse(true,MyConstant.ACTION_CREATE , MyConstant.MENU_ENTITY, menuResponseDto.getId());
        return response;
    }
    @GetMapping("get/{id}")
    public RestaurantResponse<MenuResponseDto> getMenu(@PathVariable final Long id) {
        try {
            menuResponseDto = menuService.getMenuById(id);
        } catch (Exception e) {
            response = new RestaurantResponse(false, e.getMessage(), null);
            return response;
        }

        response = new RestaurantResponse(true,MyConstant.ACTION_GET , MyConstant.MENU_ENTITY, menuResponseDto);
        return response;
    }

    @GetMapping("/getAll")
    public RestaurantResponse<List<MenuResponseDto>> getMenus() {
        List<MenuResponseDto> menuResponseDtos = new ArrayList<>();
        try {
             menuResponseDtos  = menuService.getMenus();
        } catch (Exception e) {
            response = new RestaurantResponse(false, e.getMessage(), null);
            return response;
        }

        response = new RestaurantResponse(true,MyConstant.ACTION_GET , MyConstant.MENU_ENTITY, menuResponseDtos);
        return response;
    }
    @GetMapping("/search/{queryString}")
    public RestaurantResponse<List<MenuResponseDto>> searchMenuByName(@PathVariable final String queryString){
        List<MenuResponseDto> menuResponseDtos ;
        try {
            menuResponseDtos = menuService.searchMenuByNameAndDescription(queryString);

        } catch (Exception e) {
            response = new RestaurantResponse(false, e.getMessage(), null);
            return response;
        }
        response = new RestaurantResponse(true,MyConstant.ACTION_GET , MyConstant.MENU_ENTITY, menuResponseDtos);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public RestaurantResponse<MenuResponseDto> deleteMenu(@PathVariable final Long id) {
        try {
            menuService.deleteMenu(id);
        } catch (Exception e) {
            response = new RestaurantResponse(false, e.getMessage(), null);
            return response;
        }

        response = new RestaurantResponse(true,MyConstant.ACTION_DELETE , MyConstant.MENU_ENTITY, null);
        return response;
    }
    @PatchMapping("/edit/{id}")
    public RestaurantResponse<MenuResponseDto> editMenu(@PathVariable final Long id,
                                                        @RequestBody final MenuRequestDto MenuRequestDto) {
        try {
            menuService.editMenu(id,MenuRequestDto);
        } catch (Exception e) {
            response = new RestaurantResponse(false, e.getMessage(), null);
            return response;
        }
        response = new RestaurantResponse(true,MyConstant.ACTION_UPDATE , MyConstant.MENU_ENTITY, id);

        return response;
    }
}
