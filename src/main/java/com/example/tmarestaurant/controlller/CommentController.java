package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.client.Connection;
import com.example.tmarestaurant.dto.request.CommentRequestDto;
import com.example.tmarestaurant.dto.response.CommentResponseDto;
import com.example.tmarestaurant.service.CommentService;
import com.example.tmarestaurant.utils.RestaurantConstant;
import com.example.tmarestaurant.utils.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addComment")
    public RestaurantResponse<CommentResponseDto> addComment(@RequestBody final CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto;
        RestaurantResponse response ;
        Connection connection = new Connection();
        connection.connectToMLServer();
        try {
            commentResponseDto = commentService.addComment(commentRequestDto,connection);
        } catch (Exception e) {
            response = new RestaurantResponse(false,e.getMessage(), null);
            return response;
        }
        response = new RestaurantResponse(true, RestaurantConstant.ACTION_CREATE , RestaurantConstant.COMMENT_ENTITY, commentResponseDto.getId());

        return response;
    }

    @GetMapping("get/{id}")
    public RestaurantResponse<CommentResponseDto> getComment(@PathVariable final Long id) {
        CommentResponseDto commentResponseDto;
        RestaurantResponse response;
        try {
            commentResponseDto = commentService.getCommentById(id);
        } catch (Exception e) {
            response = new RestaurantResponse(false,e.getMessage(), null);
            return response;
        }

        response = new RestaurantResponse(true, RestaurantConstant.ACTION_GET , RestaurantConstant.COMMENT_ENTITY, commentResponseDto);


        return response;
    }

    @GetMapping("/getAll")
    public RestaurantResponse<List<CommentResponseDto>> getComments() {
        List<CommentResponseDto> commentResponseDtos = commentService.getComments();
        RestaurantResponse response = new RestaurantResponse(true, RestaurantConstant.ACTION_GET , RestaurantConstant.COMMENT_ENTITY, commentResponseDtos.size(),commentResponseDtos);
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public RestaurantResponse<CommentResponseDto> deleteComment(@PathVariable final Long id) {

        RestaurantResponse response ;
        try {
            commentService.deleteComment(id);
        } catch (Exception e) {
            response = new RestaurantResponse(false,e.getMessage(), null);

            return response;
        }

        response = new RestaurantResponse(true, RestaurantConstant.ACTION_DELETE , RestaurantConstant.COMMENT_ENTITY, null);
        return response;
    }

    @PatchMapping("/edit/{id}")
    public RestaurantResponse<CommentResponseDto> editComment(@PathVariable final Long id,
                                                            @RequestBody final CommentRequestDto commentRequestDto) {
        RestaurantResponse response ;
        try {
            commentService.editComment(id,commentRequestDto);
        } catch (Exception e) {
            response = new RestaurantResponse(false,e.getMessage(), null);
            return response;
        }
        response = new RestaurantResponse(true, RestaurantConstant.ACTION_UPDATE , RestaurantConstant.COMMENT_ENTITY, id);
        return response;
    }
}
