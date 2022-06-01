package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.MenuRequestDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.service.MenuService;
import com.example.tmarestaurant.utils.RestaurantConstant;
import com.example.tmarestaurant.utils.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

        response = new RestaurantResponse(true, RestaurantConstant.ACTION_CREATE , RestaurantConstant.MENU_ENTITY, menuResponseDto.getId());
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

        response = new RestaurantResponse(true, RestaurantConstant.ACTION_GET , RestaurantConstant.MENU_ENTITY, menuResponseDto);
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

        response = new RestaurantResponse(true, RestaurantConstant.ACTION_GET , RestaurantConstant.MENU_ENTITY,menuResponseDtos.size() ,menuResponseDtos);
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
        response = new RestaurantResponse(true, RestaurantConstant.ACTION_GET , RestaurantConstant.MENU_ENTITY, menuResponseDtos.size() ,menuResponseDtos);
        return response;
    }

    @GetMapping("/sort/{field}/{mode}")
    public RestaurantResponse<List<MenuResponseDto>> searchMenuByName(@PathVariable final String field,
                                                                      @PathVariable final String mode){
        List<MenuResponseDto> menuResponseDtos ;
        try {
            menuResponseDtos = menuService.sortMenuByField(field,mode);

        } catch (Exception e) {
            response = new RestaurantResponse(false, e.getMessage(), null);
            return response;
        }
        response = new RestaurantResponse(true, RestaurantConstant.ACTION_GET , RestaurantConstant.MENU_ENTITY,menuResponseDtos.size() ,menuResponseDtos);
        return response;
    }

    @GetMapping("/getAll/{offset}/{pageSize}")
    public RestaurantResponse<List<MenuResponseDto>> getMenusWithPaging(@PathVariable final int offset,
                                                                        @PathVariable final int pageSize){
        List<MenuResponseDto> menuResponseDtos ;
        try {
            menuResponseDtos = menuService.getMenus(offset,pageSize);
        } catch (Exception e) {
            response = new RestaurantResponse(false, e.getMessage(), null);
            return response;
        }
        response = new RestaurantResponse(true, RestaurantConstant.ACTION_GET , RestaurantConstant.MENU_ENTITY, menuResponseDtos.size() ,menuResponseDtos);
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
        response = new RestaurantResponse(true, RestaurantConstant.ACTION_DELETE , RestaurantConstant.MENU_ENTITY, null);
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
        response = new RestaurantResponse(true, RestaurantConstant.ACTION_UPDATE , RestaurantConstant.MENU_ENTITY, id);
        return response;
    }

}
