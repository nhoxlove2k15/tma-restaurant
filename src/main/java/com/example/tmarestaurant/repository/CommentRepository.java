package com.example.tmarestaurant.repository;

import com.example.tmarestaurant.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
