package com.plc.restaurant.model.repository;

import com.plc.restaurant.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Optional<Offer> findByDate(LocalDate date);

    Optional<Offer> findByDateAndRestaurant_Id(LocalDate date, int id);
}