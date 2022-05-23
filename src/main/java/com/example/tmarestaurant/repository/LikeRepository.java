package com.example.tmarestaurant.repository;

import com.example.tmarestaurant.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
