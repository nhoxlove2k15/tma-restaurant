package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.UserRegisterDto;
import com.example.tmarestaurant.dto.response.UserResponseDto;
import com.example.tmarestaurant.service.UserService;
import com.example.tmarestaurant.utils.MyConstant;
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
            response = new RestaurantResponse(false , e.getMessage(), null);
            return response;

        }
        response = new RestaurantResponse(true, MyConstant.ACTION_CREATE, MyConstant.USER_ENTITY , userResponseDto.getId());
        return response;
    }
    @GetMapping("get/{id}")
    public RestaurantResponse<UserResponseDto> getUser(@PathVariable final Long id) {
        try {
            userResponseDto = userService.getUserById(id);
        } catch (Exception e) {
            response = new RestaurantResponse(false , e.getMessage(), null);
            return response;
        }
        response = new RestaurantResponse(true, MyConstant.ACTION_GET, MyConstant.USER_ENTITY , userResponseDto);
        return response;
    }
    @GetMapping("/getAll")
    public RestaurantResponse<List<UserResponseDto>> getUsers() {
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        try {
            userResponseDtos = userService.getUsers();
        } catch (Exception e) {
            response = new RestaurantResponse(false , e.getMessage(), null);
            return response;
        }

        response = new RestaurantResponse(true, MyConstant.ACTION_GET, MyConstant.USER_ENTITY , userResponseDtos);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public RestaurantResponse deleteUser(@PathVariable final Long id) throws JSONException {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            response = new RestaurantResponse(false , e.getMessage(), null);
            return response;
        }

        response = new RestaurantResponse(true, MyConstant.ACTION_DELETE, MyConstant.USER_ENTITY , null);

        return response;
    }
    @PatchMapping("/edit/{id}")
    public RestaurantResponse editUser(@PathVariable final Long id,
                                       @RequestBody final UserRegisterDto userRequestDto) {

        try {
            userService.editUser(id,userRequestDto);
        } catch (Exception e) {
            response = new RestaurantResponse(false , e.getMessage(), null);

            return response;
        }
        response = new RestaurantResponse(true, MyConstant.ACTION_UPDATE, MyConstant.USER_ENTITY , id);
        return response;
    }
}
