package com.ecommercedemo.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Shop")
public class Shop implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shop_id") 
	private Long shopId;
	
	@Column(name="shop_name") 
	private  String shopName;
	
	@Column(name="shop_location")
	private String shopLocation;
	
	@Column(name="shop_description")
	private String shopDescription;
	
	@Column(name="shop_average_rating")
	private Double  shopAverageRating;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopLocation() {
		return shopLocation;
	}

	public void setShopLocation(String shopLocation) {
		this.shopLocation = shopLocation;
	}

	public String getShopDescription() {
		return shopDescription;
	}

	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}

	public Double getShopAverageRating() {
		return shopAverageRating;
	}

	public void setShopAverageRating(Double shopAverageRating) {
		this.shopAverageRating = shopAverageRating;
	}
	
	

}
