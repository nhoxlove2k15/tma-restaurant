package com.example.tmarestaurant.repository;

import com.example.tmarestaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    @Query(value = "Select * FROM menus as m Where m.name LIKE %:name% or m.description LIKE %:name%", nativeQuery = true)
    List<Menu> getMenuByName(@Param("name") String name);
}
