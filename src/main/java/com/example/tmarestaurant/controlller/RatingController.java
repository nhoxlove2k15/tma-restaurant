package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.RatingRequestDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.dto.response.RatingResponseDto;
import com.example.tmarestaurant.service.RatingService;
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

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }
    @PostMapping("/addRating")
    public ResponseEntity<RatingResponseDto> addRating(@RequestBody final RatingRequestDto ratingRequestDto) {
        RatingResponseDto ratingResponseDto = ratingService.addRating(ratingRequestDto);
        return new ResponseEntity<>(ratingResponseDto, HttpStatus.OK);
    }
    @GetMapping("get/{id}")
    public RestaurantResponse<RatingResponseDto> getRating(@PathVariable final Long id) {
        RatingResponseDto ratingResponseDto = ratingService.getRatingById(id);
        RestaurantResponse response = new RestaurantResponse(ratingResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
    @GetMapping("/getAll")
    public RestaurantResponse<List<RatingResponseDto>> getRatings() {
        List<RatingResponseDto> ratingResponseDtos = null;
        RestaurantResponse response = new RestaurantResponse(ratingResponseDtos,"Add successfully", HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public RestaurantResponse<RatingResponseDto> deleteRating(@PathVariable final Long id) {
        RatingResponseDto ratingResponseDto = ratingService.deleteRating(id);
        RestaurantResponse response = new RestaurantResponse(ratingResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
    @PostMapping("/edit/{id}")
    public RestaurantResponse<RatingResponseDto> editRating(@PathVariable final Long id,
                                                        @RequestBody final RatingRequestDto RatingRequestDto) {
        RatingResponseDto ratingResponseDto = ratingService.editRating(id,RatingRequestDto);
        RestaurantResponse response = new RestaurantResponse(ratingResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
}
