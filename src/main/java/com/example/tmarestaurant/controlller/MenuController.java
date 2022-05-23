package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.MenuRequestDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.service.MenuService;
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
        MenuResponseDto MenuResponseDto = menuService.addMenu(menuRequestDto);
        return new ResponseEntity<>(MenuResponseDto, HttpStatus.OK);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<MenuResponseDto> getMenu(@PathVariable final Long id) {
        MenuResponseDto MenuResponseDto = menuService.getMenuById(id);
        return new ResponseEntity<>(MenuResponseDto, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<MenuResponseDto>> getMenus() {
        List<MenuResponseDto> MenuResponseDtos = menuService.getMenus();
        return new ResponseEntity<>(MenuResponseDtos, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MenuResponseDto> deleteMenu(@PathVariable final Long id) {
        MenuResponseDto MenuResponseDto = menuService.deleteMenu(id);
        return new ResponseEntity<>(MenuResponseDto, HttpStatus.OK);
    }
    @PostMapping("/edit/{id}")
    public ResponseEntity<MenuResponseDto> editMenu(@PathVariable final Long id,
                                                        @RequestBody final MenuRequestDto MenuRequestDto) {
        MenuResponseDto MenuResponseDto = menuService.editMenu(id,MenuRequestDto);
        return new ResponseEntity<>(MenuResponseDto, HttpStatus.OK);
    }
}
