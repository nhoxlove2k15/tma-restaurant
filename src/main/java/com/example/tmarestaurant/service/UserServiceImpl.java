package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.mapper;
import com.example.tmarestaurant.dto.request.UserRegisterDto;
import com.example.tmarestaurant.dto.response.UserResponseDto;
import com.example.tmarestaurant.model.Rating;
import com.example.tmarestaurant.model.User;
import com.example.tmarestaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private LikeService likeService;
    private RatingService ratingService;
    private CommentService commentService;

    @Autowired
    public UserServiceImpl(@Lazy UserRepository userRepository, @Lazy LikeService likeService, @Lazy RatingService ratingService, @Lazy CommentService commentService) {
        this.userRepository = userRepository;
        this.likeService = likeService;
        this.ratingService = ratingService;
        this.commentService = commentService;
    }

    @Override
    public UserResponseDto addUser(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setFullname(userRegisterDto.getFullname());
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(userRegisterDto.getPassword());
        user.setPhone(userRegisterDto.getPhone());
        // encode password and check username is exist

        userRepository.save(user);
        return mapper.userToUserResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getUsers() {
        List<User> users = StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return mapper.usersToUserResponseDtos(users);
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        return mapper.userToUserResponseDto(getUser(userId));
    }

    @Override
    public User getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new IllegalArgumentException("user with id: " + userId + " could ot be found")
                );
//        user.setM
        user.setRatings(ratingService.getRatingsByUser(user.getId()));
        user.setLikes(likeService.getLikes(user.getId()));
        user.setComments(commentService.getCommentsByUser(user.getId()));
        System.out.println("--------------------------------------------- user service " + user.getRatings());

        return user;
    }

    @Override
    public UserResponseDto deleteUser(Long userId) {
        User user = getUser(userId);
        UserResponseDto userTemp = mapper.userToUserResponseDto(user);
        userRepository.deleteById(userId);
//        userRepository.delete(user);
//        System.out.println(userTemp.getFullname());
        return userTemp;
    }

    @Override
    public UserResponseDto editUser(Long userId, UserRegisterDto userRegisterDto) {
        User userToEdit = getUser(userId);
        userToEdit.setFullname(userRegisterDto.getFullname());
        userToEdit.setPhone(userRegisterDto.getPhone());
        System.out.println(userToEdit.getFullname());
        userRepository.save(userToEdit);
        return mapper.userToUserResponseDto(userToEdit);
    }
}
