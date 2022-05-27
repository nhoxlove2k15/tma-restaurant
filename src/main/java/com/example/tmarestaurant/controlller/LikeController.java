package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.LikeRequestDto;
import com.example.tmarestaurant.dto.response.LikeResponseDto;
import com.example.tmarestaurant.dto.response.RatingResponseDto;
import com.example.tmarestaurant.service.LikeService;
import com.example.tmarestaurant.utils.MyConstant;
import com.example.tmarestaurant.utils.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;
    private LikeResponseDto likeResponseDto;
    private RestaurantResponse response;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/addLike")
    public RestaurantResponse<LikeResponseDto> getLike(@RequestBody final LikeRequestDto likeRequestDto) {
        try {
            likeResponseDto = likeService.addLike(likeRequestDto);
        } catch (Exception e) {
            response = new RestaurantResponse(false, e.getMessage() , null);
            return response;

        }
        response = new RestaurantResponse(true, MyConstant.ACTION_GET , MyConstant.LIKE_ENTITY , likeResponseDto.getId());
        return response;
    }
    @GetMapping("/getAll")
    public RestaurantResponse<List<LikeResponseDto>> getLikes() {
        List<LikeResponseDto> likeResponseDtos = likeService.getLikes();
        response = new RestaurantResponse(true, MyConstant.ACTION_GET, MyConstant.LIKE_ENTITY, likeResponseDtos);
        return response;
    }

    @GetMapping("/getAll/{id}")
    public RestaurantResponse<List<LikeResponseDto>> getLikes(@PathVariable final Long id) {
        List<LikeResponseDto> likeResponseDtos = null;
        RestaurantResponse response = new RestaurantResponse(true, MyConstant.ACTION_GET , MyConstant.LIKE_ENTITY , null);
        return response;
    }
    @DeleteMapping("/delete")
    public RestaurantResponse deleteLike(@RequestBody final LikeRequestDto likeRequestDto) {
        try {
            likeService.deleteLike(likeRequestDto);

        } catch (Exception e) {
            response = new RestaurantResponse(false, e.getMessage() , null);
            return response;

        }
        response = new RestaurantResponse(true, MyConstant.ACTION_DELETE , MyConstant.LIKE_ENTITY , null);
        return response;

    }
    @GetMapping("/getCount/{id}")
    public RestaurantResponse getLikedCount(@PathVariable final Long id) {
        int count ;
        try {
            count = likeService.getLikedCount(id);

        } catch (Exception e) {
            RestaurantResponse response = new RestaurantResponse(false,e.getMessage(), null);
            return response;
        }
        RestaurantResponse response = new RestaurantResponse(true,"Liked Count by menu_id", count);
        return response;
    }
}
