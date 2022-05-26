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
    public RestaurantResponse<CommentResponseDto> addComment(@RequestBody final CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto;
        RestaurantResponse response ;
        try {
            commentResponseDto = commentService.addComment(commentRequestDto);
        } catch (Exception e) {
            response = new RestaurantResponse(null,"Add successfully", HttpStatus.BAD_REQUEST, e.getMessage());
            return response;
        }
        response = new RestaurantResponse(commentResponseDto.getId(),"Add successfully", HttpStatus.OK);
        return response;
    }
    @GetMapping("get/{id}")
    public RestaurantResponse<CommentResponseDto> getComment(@PathVariable final Long id) {
        CommentResponseDto commentResponseDto;
        RestaurantResponse response;
        try {
            commentResponseDto = commentService.getCommentById(id);
        } catch (Exception e) {
            response = new RestaurantResponse(null,"Get failed", HttpStatus.BAD_REQUEST, e.getMessage());
            return response;
        }

        response = new RestaurantResponse(commentResponseDto,"Add successfully", HttpStatus.OK);
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

        RestaurantResponse response ;
        try {
            commentService.deleteComment(commentRequestDto);
        } catch (Exception exception) {
            response = new RestaurantResponse(null,"Failed", HttpStatus.BAD_REQUEST);
            return response;
        }

         response = new RestaurantResponse(null,"Delete successfully", HttpStatus.OK);
        return response;
    }
    @PostMapping("/edit/{id}")
    public RestaurantResponse<CommentResponseDto> editComment(@PathVariable final Long id,
                                                            @RequestBody final CommentRequestDto commentRequestDto) {
        RestaurantResponse response ;
        try {
            commentService.editComment(id,commentRequestDto);

        } catch (Exception e) {
            response = new RestaurantResponse(null,"Update failed", HttpStatus.BAD_REQUEST, e.getMessage());
            return response;
        }

        response = new RestaurantResponse(id,"Add successfully", HttpStatus.OK);
        return response;
    }
}
