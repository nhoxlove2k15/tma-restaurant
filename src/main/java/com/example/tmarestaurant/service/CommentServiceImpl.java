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
import com.example.tmarestaurant.utils.MyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        List<Comment> comments = commentRepository.findAll().stream()
                .filter(c1 -> c1.getUser().getId() == userId && c1.getMenu().getId() == menuId)
                .collect(Collectors.toList());
//        System.out.println("=================================== comment service" + comments);
        if (comments.size() != 0) {
            throw new IllegalStateException(MyConstant.COMMENT_ENTITY + MyConstant.ERR_ENTITY_EXISTED);
        }
        Comment comment = new Comment();
        comment.getMenu().setId(menuId);
        comment.getUser().setId(userId);
        comment.setContent(commentRequestDto.getContent());
        try {
            commentRepository.save(comment);
        } catch (Exception e) {
            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.COMMENT_ENTITY);
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
                () ->  new IllegalStateException(MyConstant.ERR_GET_ENTITY + MyConstant.COMMENT_ENTITY)
        );

        return comment;
    }

    @Override
    public List<Comment> getCommentsByUser(Long userId) {

        List<Comment> comments = StreamSupport
                .stream(commentRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
//        List<Comment> copyComments = new ArrayList<>();
//        for (Comment comment : comments) {
//            copyComments.add(comment);
//        }
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
    public void deleteComment(CommentRequestDto commentRequestDto) {
        try {
            commentRepository.deleteById(commentRequestDto.getId());
        } catch (Exception e) {
            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.COMMENT_ENTITY);
        }
    }

    @Override
    public void editComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment commentToEdit = getComment(commentId);
        commentToEdit.setContent(commentRequestDto.getContent());
        try {
            commentRepository.save(commentToEdit);

        } catch (Exception e) {
            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.COMMENT_ENTITY);
        }
    }
}
