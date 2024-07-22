package com.beshara.fintech.product.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "P_ORDER")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
    private String customerId;
    private Date orderDate;
    @OneToMany( cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "order_id") 
    private List<OrderLine> orderDetails=new ArrayList<OrderLine>();
	public List<OrderLine> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderLine> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
}
