package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.request.BillRequestDto;
import com.example.tmarestaurant.dto.request.LikeRequestDto;
import com.example.tmarestaurant.dto.response.BillResponseDto;
import com.example.tmarestaurant.dto.response.LikeResponseDto;
import com.example.tmarestaurant.model.Bill;
import com.example.tmarestaurant.model.Like;
import com.example.tmarestaurant.model.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface LikeService {
    LikeResponseDto addLike(LikeRequestDto likeRequestDto);
//    LikeResponseDto getLikeById(Long billId);
//    LikeResponseDto getLike(Long billId);
    List<Like> getLikes(Long userId);
    void deleteLike(LikeRequestDto likeRequestDto);
    int getLikedCount(Long menuId);
    Set<Menu> getMenuLikedByUser(Long userId);

//    LikeResponseDto editLike(Long billId, LikeRequestDto billRequestDto);
}
