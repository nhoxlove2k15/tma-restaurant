package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.mapper;
import com.example.tmarestaurant.dto.request.MenuRequestDto;
import com.example.tmarestaurant.dto.response.MenuResponseDto;
import com.example.tmarestaurant.model.Comment;
import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.model.User;
import com.example.tmarestaurant.repository.MenuRepository;
import com.example.tmarestaurant.utils.MyConstant;
import lombok.var;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class MenuServiceImpl implements MenuService{
//    @Autowired @Lazy
    private  final MenuRepository menuRepository;
//    @Autowired @Lazy
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

//    public LikeService getLikeService() {
//        return likeService;
//    }
//    @PostConstruct
//    public void init() {
//
//        likeService.setMenuService(this);
//    }
//    public void setLikeService(LikeServiceImpl likeService) {
//        this.likeService = likeService;
//    }

//    @Autowired
//    public MenuServiceImpl(MenuRepository menuRepository) {
//        this.menuRepository = menuRepository;
//    }

    @Override
    public MenuResponseDto addMenu(MenuRequestDto menuRequestDto) {
        String name = menuRequestDto.getName();
        List<Menu> menus = menuRepository.findAll().stream()
                .filter(c1 -> c1.getName().equals(name) )
                .collect(Collectors.toList());
//        System.out.println("=================================== comment service" + comments);
        if (menus.size() != 0) {
            throw new IllegalStateException(MyConstant.MENU_ENTITY + MyConstant.ERR_ENTITY_EXISTED);
        }
        Menu menu = new Menu();
        menu.setDescription(menuRequestDto.getDescription());
        menu.setPrice(menuRequestDto.getPrice());
        menu.setName(menuRequestDto.getName());
        menu.setRatings(ratingService.getRatingsByMenu(menu.getId()));
        // need category repository
//        menu.setCategory(menuRequs);
        menu.getCategory().setId((long) menuRequestDto.getCategoryId());
        try {
            menuRepository.save(menu);
        } catch (Exception e) {
            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.MENU_ENTITY);
        }
        return mapper.menuToMenuResponseDto(menu);
    }

    @Override
    public MenuResponseDto getMenuById(Long menuId) {
        Menu menu = getMenu(menuId);
//        int a = 2 ;

        return mapper.menuToMenuResponseDto(menu);
    }

    @Override
    public Menu getMenu(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(
                        () -> new IllegalArgumentException(MyConstant.ERR_GET_ENTITY + MyConstant.MENU_ENTITY)
                );
//        System.out.println("========================================================================================" + 1);
//
//        System.out.println("========================================================================================" + 2);
        menu.setLikedCount(likeService.getLikedCount(menu.getId()));
        menu.setRatings(ratingService.getRatingsByMenu(menu.getId()));
        menu.setComments(commentService.getCommentsByMenu(menu.getId()));
//        try {
//            menuRepository.save(menu);
//        } catch (Exception e) {
//            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.MENU_ENTITY);
//
//        }

        return menu;
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
            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.MENU_ENTITY);
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
            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.MENU_ENTITY);
        }

    }

    @Override
    public List<MenuResponseDto> searchMenuByNameAndDescription(String queryString) {
//        Map<Long,Long> map = new HashMap();

//        if (menu != null) {
        List<Menu> results;
        try {
             results = menuRepository.getMenuByName(queryString);
        } catch (Exception e) {
            throw new IllegalStateException(MyConstant.ERR_WRONG_DATABASE + MyConstant.MENU_ENTITY);

        }

        return mapper.menusToMenuResponseDtos(results);
    }
}
