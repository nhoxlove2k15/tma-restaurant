package com.example.tmarestaurant.repository;

import com.example.tmarestaurant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
