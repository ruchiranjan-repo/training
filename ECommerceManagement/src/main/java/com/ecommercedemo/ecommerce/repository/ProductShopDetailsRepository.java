package com.ecommercedemo.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommercedemo.ecommerce.entity.ProductShopDetails;

@Repository
public interface ProductShopDetailsRepository extends JpaRepository<ProductShopDetails, Long> {

	@Query(value = " select * from  PRODUCT_SHOP_DETAILS  where product_id=?1", nativeQuery = true)
	public List<ProductShopDetails> findShopDetails(Long productId);


}
