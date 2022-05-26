package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.UserRegisterDto;
import com.example.tmarestaurant.dto.response.UserResponseDto;
import com.example.tmarestaurant.service.UserService;
import com.example.tmarestaurant.utils.RestaurantResponse;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private UserResponseDto userResponseDto;
    private RestaurantResponse response;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/addUser")
    public RestaurantResponse<UserResponseDto> addUser(@RequestBody final UserRegisterDto userRequestDto) {
        try {
            userResponseDto = userService.addUser(userRequestDto);
        } catch (Exception e) {
            response = new RestaurantResponse(null,"Add successfully", HttpStatus.BAD_REQUEST);
            return response;

        }
        response = new RestaurantResponse(userResponseDto.getId(),"Add successfully", HttpStatus.OK);
        return response;
    }
    @GetMapping("get/{id}")
    public RestaurantResponse<UserResponseDto> getUser(@PathVariable final Long id) {
        try {
            userResponseDto = userService.getUserById(id);
        } catch (Exception e) {
            RestaurantResponse response = new RestaurantResponse(null,"Add successfully", HttpStatus.BAD_REQUEST);
            return response;
        }
        RestaurantResponse response = new RestaurantResponse(userResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
    @GetMapping("/getAll")
    public RestaurantResponse<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        try {
            userResponseDtos = userService.getUsers();
        } catch (Exception e) {
            response = new RestaurantResponse(null,"Add successfully", HttpStatus.BAD_REQUEST);
            return response;
        }

        response = new RestaurantResponse(userResponseDtos,"Add successfully", HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public RestaurantResponse deleteUser(@PathVariable final Long id) throws JSONException {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            response = new RestaurantResponse(null,"delete sucessfully", HttpStatus.BAD_REQUEST);
            return response;
        }

        response = new RestaurantResponse(null,"delete sucessfully", HttpStatus.OK);
        return response;
    }
    @PostMapping("/edit/{id}")
    public RestaurantResponse editUser(@PathVariable final Long id,
                                       @RequestBody final UserRegisterDto userRequestDto) {

        try {
            userService.editUser(id,userRequestDto);
        } catch (Exception e) {
            response = new RestaurantResponse(null,"update sucessfully", HttpStatus.OK);
            return response;
        }

        response = new RestaurantResponse(id,"update sucessfully", HttpStatus.OK);
        return response;
    }
}
