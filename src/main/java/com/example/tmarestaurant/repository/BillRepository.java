package com.example.tmarestaurant.repository;

import com.example.tmarestaurant.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
