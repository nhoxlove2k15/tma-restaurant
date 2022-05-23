package com.example.tmarestaurant.repository;

import com.example.tmarestaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {
}
