package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.LikeRequestDto;
import com.example.tmarestaurant.dto.response.LikeResponseDto;
import com.example.tmarestaurant.service.LikeService;
import com.example.tmarestaurant.utils.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/addLike")
    public RestaurantResponse<LikeResponseDto> getLike(@RequestBody final LikeRequestDto likeRequestDto) {
        LikeResponseDto likeResponseDto = likeService.addLike(likeRequestDto);
        RestaurantResponse response = new RestaurantResponse(null, "Add successfully", HttpStatus.OK);
        return response;
    }

    @GetMapping("/getAll/{id}")
    public RestaurantResponse<List<LikeResponseDto>> getLikes(@PathVariable final Long id) {
        List<LikeResponseDto> likeResponseDtos = null;
        RestaurantResponse response = new RestaurantResponse(likeResponseDtos,"Get list successfully", HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/delete")
    public RestaurantResponse deleteLike(@RequestBody final LikeRequestDto likeRequestDto) {
        LikeResponseDto likeResponseDto = likeService.deleteLike(likeRequestDto);
        RestaurantResponse response = new RestaurantResponse(null, "Delete successfully", HttpStatus.OK);
        return response;
    }
    @GetMapping("/getCount/{id}")
    public RestaurantResponse getLikedCount(@PathVariable final Long id) {
        int count = likeService.getLikedCount(id);
        RestaurantResponse response = new RestaurantResponse(count,"Liked Count by menu_id", HttpStatus.OK);
        return response;
    }
}
