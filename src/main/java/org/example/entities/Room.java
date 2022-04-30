package org.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "price", nullable = false)
	private double price;
	@Column(name = "amount_people", nullable = false)
	private int AmountPeople;
	
	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmountPeople() {
		return AmountPeople;
	}

	public void setAmountPeople(int amountPeople) {
		AmountPeople = amountPeople;
	}
}
