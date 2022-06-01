package com.example.tmarestaurant.service;

import com.example.tmarestaurant.client.Connection;
import com.example.tmarestaurant.dto.mapper;
import com.example.tmarestaurant.dto.request.CommentRequestDto;
import com.example.tmarestaurant.dto.response.CommentResponseDto;
import com.example.tmarestaurant.model.Comment;
import com.example.tmarestaurant.repository.CommentRepository;
import com.example.tmarestaurant.client.CommentResponseFromML;
import com.example.tmarestaurant.utils.RestaurantConstant;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CommentServiceImpl implements CommentService {
    private  final CommentRepository commentRepository ;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentResponseDto addComment(CommentRequestDto commentRequestDto, Connection connection) {
        Long userId = commentRequestDto.getUserId();
        Long menuId = commentRequestDto.getMenuId();
        List<Comment> comments = commentRepository.findAll().stream()
                .filter(c1 -> c1.getUser().getId() == userId && c1.getMenu().getId() == menuId)
                .collect(Collectors.toList());
        if (comments.size() != 0) {
            throw new IllegalStateException(RestaurantConstant.COMMENT_ENTITY + RestaurantConstant.ERR_ENTITY_EXISTED);
        }
        Comment comment = new Comment();
        comment.getMenu().setId(menuId);
        comment.getUser().setId(userId);
        comment.setContent(commentRequestDto.getContent());
        String jsonString = "" ;
        try {
            jsonString = String.valueOf(new JSONObject()
                .put("text", comment.getContent()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonString);
        try {
            CommentResponseFromML responseFromML = connection.getApiRetrofit().postComment(requestBody).execute().body();
            String label = responseFromML.getPredictions().get(0).getLabel();
            double point = responseFromML.getPredictions().get(0).getScore();
            System.out.println("===================== positive" + label + point);
            if (label.equals("NEGATIVE")) {
                comment.setToxic(true);
                if (point > 0.5 ) comment.setPoint(1);
                else comment.setPoint(2);
            }
            else{

                comment.setToxic(false);
                if (point > 0.8) comment.setPoint(5);
                else if (point < 0.5)  comment.setPoint(3);
                else comment.setPoint(4);

            }
        } catch (IOException e) {
            throw new IllegalStateException("something wrong with ml server or network");
        }
        try {
            commentRepository.save(comment);
        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.COMMENT_ENTITY);
        }
        return mapper.commentToCommentResponseDto(comment);
    }

    @Override
    public CommentResponseDto getCommentById(Long commentId) {
        return mapper.commentToCommentResponseDto(getComment(commentId));
    }

    @Override
    public Comment getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () ->  new IllegalStateException(RestaurantConstant.ERR_GET_ENTITY + RestaurantConstant.COMMENT_ENTITY)
        );

        return comment;
    }

    @Override
    public List<Comment> getCommentsByUser(Long userId) {

        List<Comment> comments = StreamSupport
                .stream(commentRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        List<Comment> results = new ArrayList<>();
        for(Comment comment : comments) {
            if(comment.getUser().getId() == userId) {
                results.add(comment);
            }
        }
        return results;
    }

    @Override
    public List<Comment> getCommentsByMenu(Long menuId) {
        List<Comment> comments = StreamSupport
                .stream(commentRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

        List<Comment> results = new ArrayList<>();
        for(Comment comment : comments) {
            if(comment.getMenu().getId() == menuId) {
                results.add(comment);
            }
        }
        return results;
    }

    @Override
    public List<CommentResponseDto> getComments() {
        List<Comment> comments = StreamSupport
                .stream(commentRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());

        return mapper.commentToCommentResponseDtos(comments);
    }

    @Override
    public void deleteComment(Long commentId) {
        try {
            commentRepository.deleteById(commentId);
        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.COMMENT_ENTITY);
        }
    }

    @Override
    public void editComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment commentToEdit = getComment(commentId);
        commentToEdit.setContent(commentRequestDto.getContent());
        try {
            commentRepository.save(commentToEdit);

        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.COMMENT_ENTITY);
        }
    }
}
