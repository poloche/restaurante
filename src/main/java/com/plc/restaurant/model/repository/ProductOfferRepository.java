package com.plc.restaurant.model.repository;

import com.plc.restaurant.model.ProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOfferRepository extends JpaRepository<ProductOffer, Integer> {
}