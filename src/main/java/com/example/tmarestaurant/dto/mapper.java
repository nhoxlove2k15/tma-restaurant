package com.example.tmarestaurant.dto;

import com.example.tmarestaurant.dto.request.BillDetailRequestDto;
import com.example.tmarestaurant.dto.response.*;
import com.example.tmarestaurant.model.*;
import java.util.ArrayList;
import java.util.List;

public class mapper {
    public static UserResponseDto userToUserResponseDto(User user) {

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFullname(user.getFullname());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setBills(user.getBills());
        userResponseDto.setLikes(user.getLikes());
        userResponseDto.setRole(user.getRole());
        userResponseDto.setPhone(user.getPhone());

        List<Comment> comments = new ArrayList<>();
        for (Comment comment : user.getComments()) {
            comment.setUser(null);
            comments.add(comment);
        }

        List<Rating> ratings = new ArrayList<>();
        for (Rating rating : user.getRatings()) {
            rating.setUser(null);
            ratings.add(rating);
        }

        List<Like> likes = new ArrayList<>();
        for (Like like : user.getLikes()) {
            like.setUser(null);
            likes.add(like);
        }

        userResponseDto.setComments(comments);
        userResponseDto.setRatings(ratings);
        userResponseDto.setLikes(likes);

        return userResponseDto;
    }
    public static List<UserResponseDto> usersToUserResponseDtos(List<User> users) {

        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for(User user : users) {
            userResponseDtos.add( userToUserResponseDto(user) );
        }
        return userResponseDtos;
    }
    public static MenuResponseDto menuToMenuResponseDto(Menu menu) {
        MenuResponseDto menuResponseDto = new MenuResponseDto();
        menuResponseDto.setId(menu.getId());
        menuResponseDto.setCategory(menu.getCategory());
        menuResponseDto.setDescription(menu.getDescription());
        menuResponseDto.setImages(menu.getImages());
        menuResponseDto.setName(menu.getName());
        menuResponseDto.setPoint(menu.getPoint());

        List<Comment> comments = new ArrayList<>();
        for (Comment comment : menu.getComments()) {
            comment.setMenu(null);
            comments.add(comment);
        }

        List<Rating> ratings = new ArrayList<>();
        for (Rating rating : menu.getRatings()) {
            rating.setMenu(null);
            ratings.add(rating);
        }

        menuResponseDto.setComments(comments);
        menuResponseDto.setRatings(ratings);
        menuResponseDto.setPrice(menu.getPrice());
        menuResponseDto.setLikedCount(menu.getLikedCount());

        return menuResponseDto;
    }
    public static List<MenuResponseDto> menusToMenuResponseDtos(List<Menu> menus) {
        List<MenuResponseDto> menuResponseDtos = new ArrayList<>();
        for(Menu menu : menus) {
            menuResponseDtos.add(menuToMenuResponseDto(menu));
        }
        return menuResponseDtos;
    }

    public static BillResponseDto billToBillResponseDto(Bill bill) {
        BillResponseDto billResponseDto = new BillResponseDto();

        billResponseDto.setId(bill.getId());
        billResponseDto.setTotalprice(bill.getTotalprice());
        billResponseDto.setBillDetails(bill.getBillDetails());

        return  billResponseDto;
    }

    public static List<BillResponseDto> billsToResponseDtos(List<Bill> bills) {
        List<BillResponseDto> billResponseDtos = new ArrayList<>();
        for (Bill bill : bills) {
            billResponseDtos.add(billToBillResponseDto(bill));
        }
        return billResponseDtos;
    }
    public static BillDetail billDetailRequestToBillDetail(BillDetailRequestDto billDetailRequestDto) {
        BillDetail billDetail = new BillDetail();

        billDetail.setDiscount(billDetailRequestDto.getDiscount());
        billDetail.setId(billDetailRequestDto.getId());
        billDetail.setMenuOrigin(billDetailRequestDto.getMenuOrigin());

        return billDetail;
    }

    public static LikeResponseDto likeToLikeResponseDto(Like like) {
        LikeResponseDto likeResponseDto = new LikeResponseDto();
        likeResponseDto.setId(like.getId());
        likeResponseDto.setMenu(like.getMenu());
        return likeResponseDto;
    }
    public static List<LikeResponseDto> likeResponseDtos (List<Like> likes) {
        List<LikeResponseDto> likeResponseDtos = new ArrayList<>();
        for (Like like : likes) {
            likeResponseDtos.add(likeToLikeResponseDto(like));
        }
        return likeResponseDtos;
    }

    public static RatingResponseDto ratingToRatingResponseDto(Rating rating) {
        RatingResponseDto ratingResponseDto = new RatingResponseDto();

        ratingResponseDto.setUser(rating.getUser());
        ratingResponseDto.setMenu(rating.getMenu());
        ratingResponseDto.setId(rating.getId());

        return ratingResponseDto;
    }

    public static List<RatingResponseDto> ratingToRatingResponseDtos(List<Rating> ratings) {
        List<RatingResponseDto> ratingResponseDtos = new ArrayList<>();
        for(Rating rating : ratings) {
            ratingResponseDtos.add(ratingToRatingResponseDto(rating));
        }
        return ratingResponseDtos;
    }

    public static CommentResponseDto commentToCommentResponseDto(Comment comment) {
        CommentResponseDto commentResponseDto = new CommentResponseDto();

        commentResponseDto.setContent(comment.getContent());
        commentResponseDto.setToxic(comment.isToxic());
        commentResponseDto.setPoint(comment.getPoint());
        commentResponseDto.setMenu(comment.getMenu());
        commentResponseDto.setUser(comment.getUser());
        commentResponseDto.setId(comment.getId());

        return commentResponseDto;
    }

    public static List<CommentResponseDto> commentToCommentResponseDtos(List<Comment> comments) {
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for(Comment comment : comments) {
            commentResponseDtos.add(commentToCommentResponseDto(comment));
        }
        return commentResponseDtos;
    }
}
