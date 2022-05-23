package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.UserRegisterDto;
import com.example.tmarestaurant.dto.response.UserResponseDto;
import com.example.tmarestaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/addUser")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody final UserRegisterDto userRequestDto) {
        UserResponseDto UserResponseDto = userService.addUser(userRequestDto);
        return new ResponseEntity<>(UserResponseDto, HttpStatus.OK);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable final Long id) {
        UserResponseDto UserResponseDto = userService.getUserById(id);
        return new ResponseEntity<>(UserResponseDto, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> UserResponseDtos = userService.getUsers();
        return new ResponseEntity<>(UserResponseDtos, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable final Long id) {
        UserResponseDto UserResponseDto = userService.deleteUser(id);
        return new ResponseEntity<>(UserResponseDto, HttpStatus.OK);
    }
    @PostMapping("/edit/{id}")
    public ResponseEntity<UserResponseDto> editUser(@PathVariable final Long id,
                                                        @RequestBody final UserRegisterDto userRequestDto) {
        UserResponseDto UserResponseDto = userService.editUser(id,userRequestDto);
        return new ResponseEntity<>(UserResponseDto, HttpStatus.OK);
    }
}
