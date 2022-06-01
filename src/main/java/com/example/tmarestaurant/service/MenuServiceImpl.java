package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.mapper;
import com.example.tmarestaurant.dto.request.MenuRequestDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.model.Comment;
import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.model.Rating;
import com.example.tmarestaurant.repository.MenuRepository;
import com.example.tmarestaurant.utils.RestaurantConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MenuServiceImpl implements MenuService{

    private  final MenuRepository menuRepository;
    private  final LikeService likeService;
    private final RatingService ratingService;
    private final CommentService commentService;

    @Autowired
    public MenuServiceImpl( MenuRepository menuRepository,@Lazy LikeService likeService, @Lazy RatingService ratingService , @Lazy CommentService commentService) {
        this.menuRepository = menuRepository;
        this.likeService = likeService;
        this.ratingService = ratingService;
        this.commentService = commentService;
    }

    @Override
    public MenuResponseDto addMenu(MenuRequestDto menuRequestDto) {
        String name = menuRequestDto.getName();
        List<Menu> menus = menuRepository.findAll().stream()
                .filter(c1 -> c1.getName().equals(name) )
                .collect(Collectors.toList());

        if (menus.size() != 0) {
            throw new IllegalStateException(RestaurantConstant.MENU_ENTITY + RestaurantConstant.ERR_ENTITY_EXISTED);
        }
        Menu menu = new Menu();
        menu.setDescription(menuRequestDto.getDescription());
        menu.setPrice(menuRequestDto.getPrice());
        menu.setName(menuRequestDto.getName());
        menu.getCategory().setId((long) menuRequestDto.getCategoryId());

        try {
            menuRepository.save(menu);
        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.MENU_ENTITY);
        }
        return mapper.menuToMenuResponseDto(menu);
    }

    @Override
    public MenuResponseDto getMenuById(Long menuId) {
        Menu menu = getMenu(menuId);
        return mapper.menuToMenuResponseDto(menu);
    }

    @Override
    public Menu getMenu(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(
                        () -> new IllegalArgumentException(RestaurantConstant.ERR_GET_ENTITY + RestaurantConstant.MENU_ENTITY)
                );
        menu.setLikedCount(likeService.getLikedCount(menu.getId()));
        List<Rating> ratings = ratingService.getRatingsByMenu(menu.getId());
        List<Comment> comments = commentService.getCommentsByMenu(menu.getId());
        menu.setRatings(ratings);
        menu.setComments(comments);

        if(ratings.size() != 0 || comments.size() !=0 )
            menu.setPoint(caculatedPoint(ratings,comments));

        try {
            menuRepository.save(menu);
        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.USER_ENTITY);
        }
        return menu;
    }

    @Override
    public int caculatedPoint(List<Rating> ratings, List<Comment> comments) {

        int sum = 0 ;
        for (Rating rating : ratings) {
            sum += rating.getPoint();
        }
        for (Comment comment : comments) {
            sum += comment.getPoint();
        }
        sum = sum / (comments.size() + ratings.size());
        return sum;
    }

    @Override
    public List<MenuResponseDto> getMenus() {
        List<Menu> menus = StreamSupport
                .stream(menuRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return mapper.menusToMenuResponseDtos(menus);

    }

    @Override
    public void deleteMenu(Long menuId) {
        try {
            menuRepository.deleteById(menuId);

        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.MENU_ENTITY);
        }

    }

    @Override
    public void editMenu(Long menuId, MenuRequestDto menuRequestDto) {
        Menu menuToEdit = getMenu(menuId);
        menuToEdit.setDescription(menuRequestDto.getDescription());
        menuToEdit.setPrice(menuRequestDto.getPrice());
        try {
            menuRepository.save(menuToEdit);


        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.MENU_ENTITY);
        }

    }

    @Override
    public List<MenuResponseDto> searchMenuByNameAndDescription(String queryString) {

        List<Menu> results;
        try {
             results = menuRepository.getMenuByName(queryString);
        } catch (Exception e) {
            throw new IllegalStateException(RestaurantConstant.ERR_WRONG_DATABASE + RestaurantConstant.MENU_ENTITY);
        }

        return mapper.menusToMenuResponseDtos(results);
    }

    @Override
    public List<MenuResponseDto> sortMenuByField(String field, String mode) {
        Sort.Direction modeSort ;
        if (mode.equals("asc")) {
            modeSort = Sort.Direction.ASC;
        } else {
            modeSort = Sort.Direction.DESC;
        }
        List<Menu> menus = StreamSupport
                .stream(menuRepository.findAll(Sort.by(modeSort,field)).spliterator(), false)
                .collect(Collectors.toList());
        return mapper.menusToMenuResponseDtos(menus);
    }

    @Override
    public List<MenuResponseDto> getMenus(int offset, int pageSize) {

        List<Menu> menus = StreamSupport
                .stream(menuRepository.findAll(PageRequest.of(offset,pageSize)).spliterator(), false)
                .collect(Collectors.toList());
        return mapper.menusToMenuResponseDtos(menus);
    }
}
