package com.ecommercedemo.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ProductShopDetails")
public class ProductShopDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="detail_id") 
	private Long DetailId;
	
	@OneToOne
	@JoinColumn(name="shop_id")
	Shop shop;
	
	@OneToOne
	@JoinColumn(name="product_id")
	Product product;
	
	@Column(name="product_quantity")
	private Long quantity;

	public Long getDetailId() {
		return DetailId;
	}

	public void setDetailId(Long detailId) {
		DetailId = detailId;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	

}
