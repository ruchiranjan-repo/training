package com.ecommercedemo.ecommerce.dto;

import java.util.List;

import com.ecommercedemo.ecommerce.entity.Shop;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * ShopResponse is used to send the Shop details
 * @author YaseenShaik
 *
 */
public class ShopResponse {

	@JsonProperty("shops")
	private List<Shop> shops;
	@JsonProperty("shopListSize")
	private Integer shopsListSize;

	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

	public Integer getShopsListSize() {
		return shopsListSize;
	}

	public void setShopsListSize(Integer shopsListSize) {
		this.shopsListSize = shopsListSize;
	}

	public ShopResponse(List<Shop> shops, Integer shopsListSize) {
		super();
		this.shops = shops;
		this.shopsListSize = shopsListSize;
	}

}
