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
@Table(name = "plate")
public class Plate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "price_breakfast", nullable = false)
	private double priceBreakfast;
	
	@Column(name = "price_lunch", nullable = false)
	private double priceLunch;
	
	@Column(name = "price_dinner", nullable = false)
	private double priceDinner;
	
	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPriceBreakfast() {
		return priceBreakfast;
	}

	public void setPriceBreakfast(double priceBreakfast) {
		this.priceBreakfast = priceBreakfast;
	}

	public double getPriceLunch() {
		return priceLunch;
	}

	public void setPriceLunch(double priceLunch) {
		this.priceLunch = priceLunch;
	}

	public double getPriceDinner() {
		return priceDinner;
	}

	public void setPriceDinner(double priceDinner) {
		this.priceDinner = priceDinner;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
}
