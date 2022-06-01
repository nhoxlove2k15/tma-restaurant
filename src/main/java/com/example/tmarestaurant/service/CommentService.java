package com.example.tmarestaurant.service;

import com.example.tmarestaurant.client.Connection;
import com.example.tmarestaurant.dto.request.CommentRequestDto;
import com.example.tmarestaurant.dto.response.CommentResponseDto;
import com.example.tmarestaurant.model.Comment;
import java.util.List;

public interface CommentService {
    CommentResponseDto addComment(CommentRequestDto commentRequestDto, Connection connection);
    CommentResponseDto getCommentById(Long commentId);
    Comment getComment(Long commentId);
    List<Comment> getCommentsByUser(Long userId);
    List<Comment> getCommentsByMenu(Long menuId);
    List<CommentResponseDto> getComments();
    void deleteComment(Long commentId);
    void editComment(Long commentId, CommentRequestDto commentRequestDto);
}
