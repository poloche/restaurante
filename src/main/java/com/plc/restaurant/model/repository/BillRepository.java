package com.plc.restaurant.model.repository;

import com.plc.restaurant.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Integer> {
}