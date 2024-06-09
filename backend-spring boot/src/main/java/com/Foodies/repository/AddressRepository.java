package com.Foodies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Foodies.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
