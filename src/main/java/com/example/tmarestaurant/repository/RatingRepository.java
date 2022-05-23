package com.example.tmarestaurant.repository;

import com.example.tmarestaurant.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Long> {
}
