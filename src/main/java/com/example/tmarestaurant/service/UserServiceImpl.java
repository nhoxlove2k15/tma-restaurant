package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.mapper;
import com.example.tmarestaurant.dto.request.UserRegisterDto;
import com.example.tmarestaurant.dto.response.UserResponseDto;
import com.example.tmarestaurant.model.User;
import com.example.tmarestaurant.repository.UserRepository;
import com.example.tmarestaurant.utils.RestaurantConstant;
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

        if (users.size() != 0) {
            throw new IllegalStateException(RestaurantConstant.USER_ENTITY + RestaurantConstant.ERR_ENTITY_EXISTED);
        }
        User user = new User();
        user.setFullname(userRegisterDto.getFullname());
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(userRegisterDto.getPassword());
        user.setPhone(userRegisterDto.getPhone());

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.USER_ENTITY);
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
                        () -> new IllegalArgumentException(RestaurantConstant.ERR_GET_ENTITY + RestaurantConstant.USER_ENTITY)
                );

        user.setRatings(ratingService.getRatingsByUser(user.getId()));
        user.setLikes(likeService.getLikes(user.getId()));
        user.setComments(commentService.getCommentsByUser(user.getId()));
        user.setBills(billService.getBillsByUser(userId));

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.USER_ENTITY);
        }
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.USER_ENTITY);
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
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.USER_ENTITY);
        }

    }
}
