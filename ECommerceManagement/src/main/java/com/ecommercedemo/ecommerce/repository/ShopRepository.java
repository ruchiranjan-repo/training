package com.ecommercedemo.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommercedemo.ecommerce.entity.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

}
