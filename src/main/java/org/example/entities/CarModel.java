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
@Table(name = "car_models")
public class CarModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name_model", nullable = false)
	private String nameModel;
	
	@Column(name = "seats_quantity", nullable = false)
	private int seatsQuantity;
	
	@ManyToOne
	@JoinColumn(name = "car_brand_id", nullable = false)
	private CarBrand carBrand;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameModel() {
		return nameModel;
	}

	public void setNameModel(String nameModel) {
		this.nameModel = nameModel;
	}

	public int getSeatsQuantity() {
		return seatsQuantity;
	}

	public void setSeatsQuantity(int seatsQuantity) {
		this.seatsQuantity = seatsQuantity;
	}

	public CarBrand getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(CarBrand carBrand) {
		this.carBrand = carBrand;
	}

}
