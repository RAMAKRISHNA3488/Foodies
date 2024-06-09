package com.Foodies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Foodies.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
