package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.mapper;
import com.example.tmarestaurant.dto.request.CommentRequestDto;
import com.example.tmarestaurant.dto.response.CommentResponseDto;
import com.example.tmarestaurant.model.Comment;
import com.example.tmarestaurant.model.Like;
import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.model.User;
import com.example.tmarestaurant.repository.CommentRepository;
import com.example.tmarestaurant.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class CommentServiceImpl implements CommentService {
    private  final CommentRepository commentRepository ;
    private  final UserService userService;
    private  final MenuService menuService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, MenuService menuService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.menuService = menuService;
    }

    @Override
    public CommentResponseDto addComment(CommentRequestDto commentRequestDto) {
        Long userId = commentRequestDto.getUserId();
        Long menuId = commentRequestDto.getMenuId();
//        User user = userService.getUser(userId);
//        Menu menu = menuService.getMenu(menuId);
        Comment comment = new Comment();


            comment.getMenu().setId(menuId);
            comment.getUser().setId(userId);
            comment.setContent(commentRequestDto.getContent());

            commentRepository.save(comment);

        return mapper.commentToCommentResponseDto(comment);
    }

    @Override
    public CommentResponseDto getCommentById(Long commentId) {
        return mapper.commentToCommentResponseDto(getComment(commentId));
    }

    @Override
    public Comment getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();

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
                comment.setUser(null);
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
            if(comment.getUser().getId() == menuId) {
                comment.setMenu(null);
                results.add(comment);
            }
        }
        return results;
    }

//    @Override
//    public List<CommentResponseDto> getComments() {
//        List<Comment> commentResponseDtos = StreamSupport
//                .stream(commentRepository.findAll().spliterator(),false)
//                .collect(Collectors.toList());
//        return mapper.commentToCommentResponseDtos(commentResponseDtos);
//    }

    @Override
    public CommentResponseDto deleteComment(CommentRequestDto commentRequestDto) {
        Long userId = commentRequestDto.getUserId();
        Long menuId = commentRequestDto.getMenuId();
        User user = userService.getUser(userId);
        Menu menu = menuService.getMenu(menuId);
        Comment comment = new Comment();
        if (user != null && menu != null) {
            commentRepository.deleteById(commentRequestDto.getId());

        }
        return mapper.commentToCommentResponseDto(comment);
    }

    @Override
    public CommentResponseDto editComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment commentToEdit = getComment(commentId);
        commentToEdit.setContent(commentRequestDto.getContent());
        commentRepository.save(commentToEdit);
        return mapper.commentToCommentResponseDto(commentToEdit);
    }
}
