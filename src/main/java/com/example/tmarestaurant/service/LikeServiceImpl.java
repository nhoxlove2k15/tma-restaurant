package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.mapper;
import com.example.tmarestaurant.dto.request.LikeRequestDto;
import com.example.tmarestaurant.dto.response.LikeResponseDto;
import com.example.tmarestaurant.model.Like;
import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.repository.LikeRepository;
import com.example.tmarestaurant.utils.RestaurantConstant;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LikeServiceImpl implements LikeService {
    private  final LikeRepository likeRepository ;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public LikeResponseDto addLike(LikeRequestDto likeRequestDto) {
        Long userId = likeRequestDto.getUserId();
        Long menuId = likeRequestDto.getMenuId();
        List<Like> likes = likeRepository.findAll().stream()
                .filter(c1 -> c1.getUser().getId() == userId && c1.getMenu().getId() == menuId)
                .collect(Collectors.toList());
        if (likes.size() != 0) {
            throw new IllegalStateException(RestaurantConstant.LIKE_ENTITY + RestaurantConstant.ERR_ENTITY_EXISTED);
        }
        Like like = new Like();
        like.getMenu().setId(menuId);
        like.getUser().setId(userId);
        try {
            likeRepository.save(like);
        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.LIKE_ENTITY);
        }
        return mapper.likeToLikeResponseDto(like);
    }

    @Override
    public List<Like> getLikes(Long userId) {
        List<Like> results = new ArrayList<>();
        List<Like> likes = likeRepository.findAll();
        for(Like like : likes) {
            if (like.getUser().getId() == userId) {
                results.add(like);
            }
        }
        return results;
    }

    @Override
    public List<LikeResponseDto> getLikes() {
        List<Like> likes = StreamSupport
                .stream(likeRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return mapper.likeResponseDtos(likes);
    }

    @Override
    public void deleteLike(LikeRequestDto likeRequestDto) {
        Long userId = likeRequestDto.getUserId();
        Long menuId = likeRequestDto.getMenuId();
        try {
            likeRepository.deleteLikesByIds(userId,menuId);
        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.LIKE_ENTITY);

        }
    }

    @Override
    public int getLikedCount(Long menuId) {
        Map<Long,Long> mapMenuId = new HashMap();

        try {
            var result = likeRepository.getCount(menuId);
            if (result != null && !result.isEmpty()) {
                for (Object[] object : result) {
                    mapMenuId.put(((BigInteger) object[1]).longValue(), ((BigInteger) object[0]).longValue());
                }
            }
            int likedCount = 0;
            if (mapMenuId.get(menuId) != null) {
                likedCount = mapMenuId.get(menuId).intValue();
            }
            return likedCount;
        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.LIKE_ENTITY);
        }
    }

    @Override
    public Set<Menu> getMenuLikedByUser(Long userId) {
        List<Long> menu_ids = new ArrayList<>();
        Set<Menu> menus = new HashSet<>();

        var result = likeRepository.getLikesByUserId(userId);
        if(result != null && !result.isEmpty()){

            for (BigInteger num : result) {
                menu_ids.add(num.longValue());
            }
        }
        return menus;
    }
}
