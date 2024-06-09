package com.Foodies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Foodies.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
	PasswordResetToken findByToken(String token);
}
