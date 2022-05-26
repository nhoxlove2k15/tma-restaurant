package com.example.tmarestaurant.service;

import com.example.tmarestaurant.dto.mapper;
import com.example.tmarestaurant.dto.request.LikeRequestDto;
import com.example.tmarestaurant.dto.response.LikeResponseDto;
import com.example.tmarestaurant.model.Like;
import com.example.tmarestaurant.model.Menu;
import com.example.tmarestaurant.model.User;
import com.example.tmarestaurant.repository.LikeRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class LikeServiceImpl implements LikeService {


    private  final LikeRepository likeRepository ;
    private  final UserService userService;
    private  final MenuService menuService;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository,UserService userService, MenuService menuService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.menuService = menuService;
    }



    @Override
    public LikeResponseDto addLike(LikeRequestDto likeRequestDto) {
        Long userId = likeRequestDto.getUserId();
        Long menuId = likeRequestDto.getMenuId();
//        User user = userService.getUser(userId);
//        Menu menu = menuService.getMenu(menuId);
        Like like = new Like();


            like.getMenu().setId(menuId);
            like.getUser().setId(userId);
            likeRepository.save(like);

        return mapper.likeToLikeResponseDto(like);
    }

    @Override
    public List<Like> getLikes(Long userId) {

        List<Like> results = new ArrayList<>();
//        List<Like>  likes = StreamSupport
//                    .stream(likeRepository.findAll().spliterator(),false)
//                    .collect(Collectors.toList());
//            likes = likeRepository.findAll();
//            System.out.println("=============================================== like service size : " + likes.size() );
//            System.out.println("=============================================== like service size : " + likes.toString() );


//            List<Menu> menus = new ArrayList<>();
//            for(int i = 0 ; i < likes.size() ; i++) {
//                Like like = likes.get(i);
//                System.out.println("=============================================== like service 123: " + 1 );
//
//                System.out.println("=============================================== like service : " + like.getMenu().toString() );
//
//
//            }
        System.out.println("=============================================== like service " + 1);

//        Like like = em.createQuery("SELECT a FROM Author a LEFT JOIN FETCH a.books", Author.class).getResultList();
        List<Like> likes = likeRepository.findAll();

        for(Like like : likes) {
            if (like.getUser().getId() == userId) {
                like.setUser(null);
                results.add(like);
            }
        }
//        System.out.println("==================================== like service" + likes.get(0).getUser().toString());
//        System.out.println("==================================== like service" + likes.get(0).getMenu().toString());
//
//
//        System.out.println("=============================================== like service" + 4);

//        System.out.println("=============================================== like service" + like.getMenu());
//        like.setMenu(menuService.getMenu(like.getMenu().getId()));
//        System.out.println("=============================================== like service" + like.getMenu());
//
//        results.add(like);
//        System.out.println("=============================================== like service : " + results.get(0).getMenu().toString() );
        return results;
    }

    @Override
    public LikeResponseDto deleteLike(LikeRequestDto likeRequestDto) {
        Long userId = likeRequestDto.getUserId();
        Long menuId = likeRequestDto.getMenuId();

        Like like = new Like();

        likeRepository.deleteLikesByIds(userId,menuId);


        return mapper.likeToLikeResponseDto(like);
    }

    @Override
    public int getLikedCount(Long menuId) {
//        Menu menu = menuService.getMenu(menuId);
        Map<Long,Long>map = new HashMap();

//        if (menu != null) {
            var result = likeRepository.getCount(menuId);
            if(result != null && !result.isEmpty()){

                for (Object[] object : result) {

                    map.put( ((BigInteger) object[1]).longValue() , ( (BigInteger) object[0]).longValue())  ;
                }
            }
//            System.out.println("============================" + map.toString() + "============================");
//
//            System.out.println("============================" + map.get(menuId) + "============================");
//            System.out.println("======================================== a" + a.toString() + a.get(0).toString() + a.get(0).getClass() );

//        }
        int likedCount = 0 ;
        if (map.get(menuId) != null ){
            likedCount = map.get(menuId).intValue();
//            System.out.println("==================================" + likedCount);

        }
        return likedCount;
    }

    @Override
    public Set<Menu> getMenuLikedByUser(Long userId) {
        List<Long> menu_ids = new ArrayList<>();
        Set<Menu> menus = new HashSet<>();

//        if (menu != null) {
        var result = likeRepository.getLikesByUserId(userId);
        if(result != null && !result.isEmpty()){

            for (BigInteger num : result) {
                menu_ids.add(num.longValue());
            }
        }
//        for (int i = 0 ; i < menu_ids.size() ; i++) {
//            Menu menu = menuService.getMenu(menu_ids.get(i));
//            menus.add(menu);
//        }


        return menus;
    }
}
