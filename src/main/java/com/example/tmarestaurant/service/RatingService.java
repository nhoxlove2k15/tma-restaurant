package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.request.MenuRequestDto;
import com.example.tmarestaurant.dto.request.RatingRequestDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.dto.response.RatingResponseDto;
import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.model.Rating;

import java.util.List;

public interface RatingService {
    RatingResponseDto addRating(RatingRequestDto ratingRequestDto);
    RatingResponseDto getRatingById(Long ratingId);
    Rating getRating(Long ratingId);
    List<Rating> getRatingsByUser(Long userId);
    List<Rating> getRatingsByMenu(Long menuId);

    void deleteRating(Long ratingId);
    void editRating(Long ratingId, RatingRequestDto ratingRequestDto);
}
