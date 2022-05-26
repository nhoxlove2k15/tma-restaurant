package com.example.tmarestaurant.repository;

import com.example.tmarestaurant.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {
//    @Query(value = "Select menu_id FROM menu_likes as m GROUP BY :user_id", nativeQuery = true)
//    List<BigInteger> getLikesByUserId(@Param("user_id") Long user_id);
//    @Query(value = "Select menu_id FROM menu_likes as m GROUP BY :user_id", nativeQuery = true)
//    List<BigInteger> getLikesByUserId(@Param("user_id") Long user_id);
}
