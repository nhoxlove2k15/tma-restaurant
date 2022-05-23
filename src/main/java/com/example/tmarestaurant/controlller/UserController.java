package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.UserRegisterDto;
import com.example.tmarestaurant.dto.response.UserResponseDto;
import com.example.tmarestaurant.service.UserService;
import com.example.tmarestaurant.utils.RestaurantResponse;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public RestaurantResponse<UserResponseDto> addUser(@RequestBody final UserRegisterDto userRequestDto) {
        UserResponseDto userResponseDto = userService.addUser(userRequestDto);
        RestaurantResponse response = new RestaurantResponse(userResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
    @GetMapping("get/{id}")
    public RestaurantResponse<UserResponseDto> getUser(@PathVariable final Long id) {
        UserResponseDto userResponseDto = userService.getUserById(id);
        RestaurantResponse response = new RestaurantResponse(userResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
    @GetMapping("/getAll")
    public RestaurantResponse<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> userResponseDtos = userService.getUsers();
        RestaurantResponse response = new RestaurantResponse(userResponseDtos,"Add successfully", HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public RestaurantResponse deleteUser(@PathVariable final Long id) throws JSONException {
        UserResponseDto userResponseDto = userService.deleteUser(id);
        RestaurantResponse response = new RestaurantResponse(null,"delete sucessfully", HttpStatus.OK);
        return response;
    }
    @PostMapping("/edit/{id}")
    public RestaurantResponse editUser(@PathVariable final Long id,
                                       @RequestBody final UserRegisterDto userRequestDto) {

        RestaurantResponse response = new RestaurantResponse(null,"update sucessfully", HttpStatus.OK);

        return response;
    }
}
