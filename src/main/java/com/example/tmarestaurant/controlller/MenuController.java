package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.MenuRequestDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.service.MenuService;
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
            response = new RestaurantResponse(null,"Add successfully", HttpStatus.BAD_REQUEST);
            return response;
        }

        response = new RestaurantResponse(menuResponseDto.getId(),"Add successfully", HttpStatus.OK);
        return response;
    }
    @GetMapping("get/{id}")
    public RestaurantResponse<MenuResponseDto> getMenu(@PathVariable final Long id) {
        try {
            menuResponseDto = menuService.getMenuById(id);
        } catch (Exception e) {
            response = new RestaurantResponse(null,"Add successfully", HttpStatus.BAD_REQUEST);
            return response;
        }

        response = new RestaurantResponse(menuResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
    @GetMapping("/getAll")
    public RestaurantResponse<List<MenuResponseDto>> getMenus() {
        List<MenuResponseDto> menuResponseDtos = new ArrayList<>();
        try {
             menuResponseDtos  = menuService.getMenus();
        } catch (Exception e) {
            response = new RestaurantResponse(null,"Add successfully", HttpStatus.BAD_REQUEST);
            return response;
        }

        response = new RestaurantResponse(menuResponseDtos,"Add successfully", HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public RestaurantResponse<MenuResponseDto> deleteMenu(@PathVariable final Long id) {
        try {
            menuService.deleteMenu(id);
        } catch (Exception e) {
            response = new RestaurantResponse(null,"Add successfully", HttpStatus.BAD_REQUEST);
            return response;
        }

        response = new RestaurantResponse(null,"Add successfully", HttpStatus.OK);
        return response;
    }
    @PostMapping("/edit/{id}")
    public RestaurantResponse<MenuResponseDto> editMenu(@PathVariable final Long id,
                                                        @RequestBody final MenuRequestDto MenuRequestDto) {
        try {
            menuService.editMenu(id,MenuRequestDto);
        } catch (Exception e) {
            response = new RestaurantResponse(null,"Add successfully", HttpStatus.BAD_REQUEST);
            return response;
        }
        response = new RestaurantResponse(id,"Add successfully", HttpStatus.OK);
        return response;
    }
}
