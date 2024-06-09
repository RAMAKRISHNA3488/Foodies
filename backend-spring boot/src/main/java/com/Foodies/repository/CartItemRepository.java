package com.Foodies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Foodies.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


//    CartItem findByFoodIsContaining

}
