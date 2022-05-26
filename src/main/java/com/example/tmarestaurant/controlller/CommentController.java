package com.example.tmarestaurant.controlller;

import com.example.tmarestaurant.dto.request.CommentRequestDto;
import com.example.tmarestaurant.dto.request.RatingRequestDto;
import com.example.tmarestaurant.dto.response.CommentResponseDto;
import com.example.tmarestaurant.dto.response.RatingResponseDto;
import com.example.tmarestaurant.service.CommentService;
import com.example.tmarestaurant.utils.RestaurantResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CommentResponseDto> addComment(@RequestBody final CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.addComment(commentRequestDto);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }
    @GetMapping("get/{id}")
    public RestaurantResponse<CommentResponseDto> getComment(@PathVariable final Long id) {
        CommentResponseDto commentResponseDto = commentService.getCommentById(id);
        RestaurantResponse response = new RestaurantResponse(commentResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
    @GetMapping("/getAll")
    public RestaurantResponse<List<CommentResponseDto>> getComments() {
        List<CommentResponseDto> commentResponseDtos = null;
        RestaurantResponse response = new RestaurantResponse(commentResponseDtos,"Add successfully", HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public RestaurantResponse<CommentResponseDto> deleteComment(@RequestBody final CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.deleteComment(commentRequestDto);
        RestaurantResponse response = new RestaurantResponse(commentResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
    @PostMapping("/edit/{id}")
    public RestaurantResponse<CommentResponseDto> editComment(@PathVariable final Long id,
                                                            @RequestBody final CommentRequestDto CommentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.editComment(id,CommentRequestDto);
        RestaurantResponse response = new RestaurantResponse(commentResponseDto,"Add successfully", HttpStatus.OK);
        return response;
    }
}
