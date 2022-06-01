package com.example.tmarestaurant.repository;

import com.example.tmarestaurant.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
public interface LikeRepository extends JpaRepository<Like,Long> {
    @Query(value = "DELETE FROM menu_likes m WHERE m.user_id = :user_id AND m.menu_id = :menu_id", nativeQuery = true)
    void deleteLikesByIds(@Param("user_id") Long userId, @Param("menu_id") Long menuId);
    @Query(value = "Select COUNT(user_id), :menu_id FROM menu_likes as m GROUP BY :menu_id", nativeQuery = true)
    List<Object[]> getCount(@Param("menu_id") Long menu_id);
    @Query(value = "Select menu_id FROM menu_likes as m GROUP BY :user_id", nativeQuery = true)
    List<BigInteger> getLikesByUserId(@Param("user_id") Long user_id);

}
