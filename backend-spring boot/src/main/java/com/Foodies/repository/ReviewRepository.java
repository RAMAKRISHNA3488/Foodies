package com.Foodies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Foodies.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
