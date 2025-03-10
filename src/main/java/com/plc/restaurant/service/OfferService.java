package com.plc.restaurant.service;

import com.plc.restaurant.controller.dto.OfferCreationDto;
import com.plc.restaurant.controller.dto.OfferDto;
import com.plc.restaurant.controller.dto.ProductDto;
import com.plc.restaurant.controller.dto.RestaurantDto;
import com.plc.restaurant.model.*;
import com.plc.restaurant.model.repository.OfferRepository;
import com.plc.restaurant.model.repository.ProductOfferRepository;
import com.plc.restaurant.model.repository.ProductRepository;
import com.plc.restaurant.model.repository.RestaurantRepository;
import com.plc.restaurant.service.exeption.RestaurantNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OfferService {
    private final ProductRepository productRepository;
    private final RestaurantRepository restaurantRepository;
    private final OfferRepository offerRepository;
    private final ProductOfferRepository productOfferRepository;

    public OfferService(ProductRepository productRepository, RestaurantRepository restaurantRepository, OfferRepository offerRepository, ProductOfferRepository productOfferRepository) {
        this.productRepository = productRepository;
        this.restaurantRepository = restaurantRepository;
        this.offerRepository = offerRepository;
        this.productOfferRepository = productOfferRepository;
    }

    public OfferCreationDto save(Integer restaurantId, OfferDto dto) throws RestaurantNotFoundException {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isEmpty()) {
            throw new RestaurantNotFoundException(restaurantId);
        }

        dto.setRestaurant(RestaurantDto.fromEntity(optionalRestaurant.get()));

        Offer offer = dto.toOffer();
        offer.setRestaurant(optionalRestaurant.get());
        offerRepository.save(offer);

        dto.setOfferId(offer.getOfferId());
        List<Integer> notAvailableProduct = new ArrayList<>();
        List<ProductOffer> products = dto.getProducts().stream().map(prodId -> {
            Optional<Product> product = productRepository.findById(prodId);
            if (product.isPresent()) {
                ProductOfferId id = new ProductOfferId();
                id.setProductId(product.get().getId());
                id.setOfferId(offer.getOfferId());

                ProductOffer productOffer = new ProductOffer();
                productOffer.setId(id);
                productOffer.setOffer(offer);
                productOffer.setProduct(product.get());
                productOffer.setQuantity(0);
                productOfferRepository.save(productOffer);
                return productOffer;
            } else {
                notAvailableProduct.add(prodId);
            }
            return null;
        }).filter(Objects::nonNull).toList();

        dto.setProducts(products.stream().map(po -> po.getProduct().getId()).toList());
        return OfferCreationDto.builder()
                .offerDto(dto)
                .productsNotAvailable(notAvailableProduct)
                .build();
    }

    public OfferDto getOffer(Integer restaurantId, LocalDate date) {
        Optional<Offer> offer = offerRepository.findByDateAndRestaurant_Id(date, restaurantId);
        return offer.map(value -> OfferDto.builder()
                .offerId(value.getOfferId())
                .date(date)
                .detailedProducts(value.getProducts().stream().map(op ->
                        ProductDto.builder()
                                .id(op.getProduct().getId())
                                .name(op.getProduct().getName())
                                .description(op.getProduct().getDescription())
                                .image(op.getProduct().getImage())
                                .build()
                ).toList())
                .build()).orElse(null);
    }
}
