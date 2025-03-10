package com.plc.restaurant.service;

import com.plc.restaurant.controller.dto.OrderCreationDto;
import com.plc.restaurant.controller.dto.OrderDto;
import com.plc.restaurant.controller.dto.OrderProductDto;
import com.plc.restaurant.model.*;
import com.plc.restaurant.model.repository.*;
import com.plc.restaurant.service.exeption.ClientExceptionNotFound;
import com.plc.restaurant.service.exeption.OrderNotFoundException;
import com.plc.restaurant.service.exeption.RestaurantNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository, UserRepository userRepository, ProductRepository productRepository,
                        OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }

    public OrderCreationDto save(OrderDto dto) throws RestaurantNotFoundException, ClientExceptionNotFound {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(dto.getRestaurantId());
        if (optionalRestaurant.isEmpty()) {
            throw new RestaurantNotFoundException(dto.getRestaurantId());
        }

        Optional<User> optionalClient = userRepository.findById(dto.getUserId());
        if (optionalClient.isEmpty()) {
            throw new ClientExceptionNotFound(dto.getUserId());
        }

        dto.setRestaurantId(optionalRestaurant.get().getId());
        dto.setUserId(optionalClient.get().getId());


        Order order = dto.toOrder();
        order.setRestaurant(optionalRestaurant.get());
        order.setClient(optionalClient.get());
        order.setRestaurant(optionalRestaurant.get());
        order.setDate(LocalDate.now());
        order.setStatus(OrderStatus.ORDERED.name());

        orderRepository.save(order);

        List<Integer> notAvailableProduct = new ArrayList<>();
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        List<OrderProduct> products = dto.getProducts().stream().map(orderProductDto -> {
            Optional<Product> optionalProduct = productRepository.findById(orderProductDto.getProductId());
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                OrderProductId id = new OrderProductId();
                id.setProductId(product.getId());
                id.setOrderId(order.getOrderId());

                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setId(id);
                orderProduct.setOrder(order);
                orderProduct.setProduct(product);
                orderProduct.setQuantity(orderProductDto.getQuantity());
                orderProductRepository.save(orderProduct);
                total.set(total.get() + (product.getCost() * orderProductDto.getQuantity()));
                return orderProduct;
            } else {
                notAvailableProduct.add(orderProductDto.getProductId());
            }
            return null;
        }).filter(Objects::nonNull).toList();
        order.setTotal(total.get());
        orderRepository.save(order);

        dto.setProducts(products.stream().map(po -> OrderProductDto
                .builder()
                .quantity(po.getQuantity())
                .productId(po.getProduct().getId())
                .build()
        ).toList());
        return OrderCreationDto.builder()
                .order(dto)
                .productsNotAvailable(notAvailableProduct)
                .build();
    }

    public OrderDto getOrder(Integer orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }
        return OrderDto.fromEntity(order.get());
    }
}
