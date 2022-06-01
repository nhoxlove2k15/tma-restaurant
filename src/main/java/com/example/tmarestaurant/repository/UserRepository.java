package com.example.tmarestaurant.repository;

import com.example.tmarestaurant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByFullname(String fullName);
}
