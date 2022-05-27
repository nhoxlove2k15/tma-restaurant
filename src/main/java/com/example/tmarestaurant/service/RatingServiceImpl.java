package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.mapper;
import com.example.tmarestaurant.dto.request.RatingRequestDto;
import com.example.tmarestaurant.dto.response.RatingResponseDto;
import com.example.tmarestaurant.model.*;
import com.example.tmarestaurant.repository.LikeRepository;
import com.example.tmarestaurant.repository.RatingRepository;
import com.example.tmarestaurant.utils.MyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class RatingServiceImpl implements RatingService {
    private  final RatingRepository ratingRepository ;
    private  final UserService userService;
    private  final MenuService menuService;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, UserService userService, MenuService menuService) {
        this.ratingRepository = ratingRepository;
        this.userService = userService;
        this.menuService = menuService;
    }

    @Override
    public RatingResponseDto addRating(RatingRequestDto ratingRequestDto) {
        Long userId = ratingRequestDto.getUserId();
        Long menuId = ratingRequestDto.getMenuId();

        List<Rating> ratingss = ratingRepository.findAll().stream()
                .filter(c1 -> c1.getUser().getId() == userId && c1.getMenu().getId() == menuId)
                .collect(Collectors.toList());
//        System.out.println("=================================== comment service" + comments);
        if (ratingss.size() != 0) {
            throw new IllegalStateException(MyConstant.RATING_ENTITY + MyConstant.ERR_ENTITY_EXISTED);
        }
        Rating rating = new Rating();


        rating.getMenu().setId(menuId);
        rating.getUser().setId(userId);
        rating.setPoint(ratingRequestDto.getPoint());
        try {
            ratingRepository.save(rating);
        } catch (Exception e) {
            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.RATING_ENTITY);
        }

        return mapper.ratingToRatingResponseDto(rating);
    }

    @Override
    public RatingResponseDto getRatingById(Long ratingId) {
        return mapper.ratingToRatingResponseDto(getRating(ratingId));
    }

    @Override
    public Rating getRating(Long ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(
                () -> new IllegalStateException(MyConstant.ERR_GET_ENTITY + MyConstant.RATING_ENTITY)
        );
        return rating;
    }

    @Override
    public List<Rating> getRatingsByUser(Long userId) {
        List<Rating> ratings = StreamSupport
                .stream(ratingRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        List<Rating> results = new ArrayList<>();
        for (Rating rating : ratings) {
            if (rating.getUser().getId() == userId) {
                rating.setUser(null);
                results.add(rating);
            }
        }
//        System.out.println("----------------------------------------------- rating service size " + results.size());
//        System.out.println("----------------------------------------------- rating service size " + results.get(0).getMenu());

        return results;
    }

    @Override
    public List<Rating> getRatingsByMenu(Long menuId) {
        List<Rating> ratings = StreamSupport
                .stream(ratingRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        List<Rating> results = new ArrayList<>();
        for (Rating rating : ratings) {
            if (rating.getMenu().getId() == menuId) {
                rating.setMenu(null);
                results.add(rating);
            }
        }
        return results;
    }

    @Override
    public List<RatingResponseDto> getRatings() {
        List<Rating> ratings = StreamSupport
                .stream(ratingRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return mapper.ratingToRatingResponseDtos(ratings);
    }

    @Override
    public void deleteRating(Long ratingId) {
//        Rating rating = new Rating();
        try {
            ratingRepository.deleteById(ratingId);
        } catch (Exception e) {
            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.RATING_ENTITY);

        }
//        return mapper.ratingToRatingResponseDto(rating);
    }


//    @Override
//    public RatingResponseDto deleteRating(RatingRequestDto ratingRequestDto) {
//        Long userId = ratingRequestDto.getUserId();
//        Long menuId = ratingRequestDto.getMenuId();
//        User user = userService.getUser(userId);
//        Menu menu = menuService.getMenu(menuId);
//        Rating rating = new Rating();
//        if (user != null && menu != null) {
//            ratingRepository.deleteById(ratingRequestDto.getId());
//
//        }
//        return mapper.ratingToRatingResponseDto(rating);
//    }



    @Override
    public void editRating(Long ratingId, RatingRequestDto ratingRequestDto) {
        Rating ratingToEdit = getRating(ratingId);
        ratingToEdit.setPoint(ratingRequestDto.getPoint());
        try {
            ratingRepository.save(ratingToEdit);
        } catch (Exception e) {
            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.COMMENT_ENTITY);

        }
//        return mapper.ratingToRatingResponseDto(ratingToEdit);

    }
}
