package com.example.ControllerProtection.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table
public class Salary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Double amount;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Salary() {
	}
	
	public Salary(Long id, Double amount, User user) {
		this.id = id;
		this.amount = amount;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
