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
@Table(name="ShopFeedback")
public class ShopFeedback implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shop_feedback_id") 
	private Long shopFeedbackId;
	
	@Column(name="shop_feedback_rating")
	private Double shopFeedbackRating;
	
	@Column(name="shop_feedback_comment")
	private String shopFeedbackComment;
	
	@OneToOne
	@JoinColumn(name="shop_id")
	Shop shop;
	
	@OneToOne
	@JoinColumn(name="user_id")
	User user;

	public Long getShopFeedbackId() {
		return shopFeedbackId;
	}

	public void setShopFeedbackId(Long shopFeedbackId) {
		this.shopFeedbackId = shopFeedbackId;
	}

	public Double getShopFeedbackRating() {
		return shopFeedbackRating;
	}

	public void setShopFeedbackRating(Double shopFeedbackRating) {
		this.shopFeedbackRating = shopFeedbackRating;
	}

	public String getShopFeedbackComment() {
		return shopFeedbackComment;
	}

	public void setShopFeedbackComment(String shopFeedbackComment) {
		this.shopFeedbackComment = shopFeedbackComment;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
