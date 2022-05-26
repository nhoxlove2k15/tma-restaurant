package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.mapper;
import com.example.tmarestaurant.dto.request.UserRegisterDto;
import com.example.tmarestaurant.dto.response.UserResponseDto;
import com.example.tmarestaurant.model.Comment;
import com.example.tmarestaurant.model.Rating;
import com.example.tmarestaurant.model.User;
import com.example.tmarestaurant.repository.UserRepository;
import com.example.tmarestaurant.utils.MyConstant;
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
    private BillService billService;

    @Autowired
    public UserServiceImpl(@Lazy UserRepository userRepository, @Lazy LikeService likeService, @Lazy RatingService ratingService,
                           @Lazy CommentService commentService, @Lazy BillService billService) {
        this.userRepository = userRepository;
        this.likeService = likeService;
        this.ratingService = ratingService;
        this.commentService = commentService;
        this.billService = billService;
    }

    @Override
    public UserResponseDto addUser(UserRegisterDto userRegisterDto) {
        String username = userRegisterDto.getUsername();
        List<User> users = userRepository.findAll().stream()
                .filter(c1 -> c1.getUsername().equals(username))
                .collect(Collectors.toList());
//        System.out.println("=================================== comment service" + comments);
        if (users.size() != 0) {
            throw new IllegalStateException(MyConstant.USER_ENTITY + MyConstant.ERR_ENTITY_EXISTED);
        }
        User user = new User();
        user.setFullname(userRegisterDto.getFullname());
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(userRegisterDto.getPassword());
        user.setPhone(userRegisterDto.getPhone());
        // encode password and check username is exist
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.USER_ENTITY);
        }

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
                        () -> new IllegalArgumentException(MyConstant.ERR_GET_ENTITY + MyConstant.USER_ENTITY)
                );
//        user.setM
        user.setRatings(ratingService.getRatingsByUser(user.getId()));
        user.setLikes(likeService.getLikes(user.getId()));
        user.setComments(commentService.getCommentsByUser(user.getId()));
        user.setBills(billService.getBillsByUser(userId));
//        System.out.println("--------------------------------------------- user service " + user.getRatings());

        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.USER_ENTITY);
        }
    }

    @Override
    public void editUser(Long userId, UserRegisterDto userRegisterDto) {
        User userToEdit = getUser(userId);
        userToEdit.setFullname(userRegisterDto.getFullname());
        userToEdit.setPhone(userRegisterDto.getPhone());
        try {
            userRepository.save(userToEdit);
        } catch (Exception e) {
            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.USER_ENTITY);
        }

    }
}
