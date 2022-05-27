package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.RatingRequestDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.dto.response.RatingResponseDto;
import com.example.tmarestaurant.service.RatingService;
import com.example.tmarestaurant.utils.MyConstant;
import com.example.tmarestaurant.utils.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    private final RatingService ratingService;
    private RatingResponseDto ratingResponseDto;
    private RestaurantResponse response ;
    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }
    @PostMapping("/addRating")
    public RestaurantResponse<RatingResponseDto> addRating(@RequestBody final RatingRequestDto ratingRequestDto) {


        try {
            ratingResponseDto = ratingService.addRating(ratingRequestDto);
        } catch (Exception e) {
            response = new RestaurantResponse(false,e.getMessage(), null);
            return response;
        }
        response = new RestaurantResponse(true, MyConstant.ACTION_CREATE, MyConstant.RATING_ENTITY, ratingResponseDto.getId());
        return response;
    }
    @GetMapping("get/{id}")
    public RestaurantResponse<RatingResponseDto> getRating(@PathVariable final Long id) {
        try {
            ratingResponseDto = ratingService.getRatingById(id);
        } catch (Exception e) {
            response = new RestaurantResponse(false,e.getMessage(), null);

            return response;
        }
        response = new RestaurantResponse(true, MyConstant.ACTION_GET, MyConstant.RATING_ENTITY, ratingResponseDto);
        return response;
    }
    @GetMapping("/getAll")
    public RestaurantResponse<List<RatingResponseDto>> getRatings() {
        List<RatingResponseDto> ratingResponseDtos = ratingService.getRatings();
        response = new RestaurantResponse(true, MyConstant.ACTION_GET, MyConstant.RATING_ENTITY, ratingResponseDtos);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public RestaurantResponse<RatingResponseDto> deleteRating(@PathVariable final Long id) {
        try {
            ratingService.deleteRating(id);
        } catch (Exception e) {
            response = new RestaurantResponse(false,e.getMessage(), null);

        }

        response = new RestaurantResponse(true, MyConstant.ACTION_DELETE, MyConstant.RATING_ENTITY, null);
        return response;
    }
    @PostMapping("/edit/{id}")
    public RestaurantResponse<RatingResponseDto> editRating(@PathVariable final Long id,
                                                        @RequestBody final RatingRequestDto RatingRequestDto) {
        try {
            ratingService.editRating(id,RatingRequestDto);
        } catch (Exception e) {
            response = new RestaurantResponse(false,e.getMessage(), null);
            return response;
        }
        response = new RestaurantResponse(true, MyConstant.ACTION_DELETE, MyConstant.RATING_ENTITY, id);
        return response;
    }
}
