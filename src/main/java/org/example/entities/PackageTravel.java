package org.example.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "package")
public class PackageTravel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name_package", nullable = false)
	private String namePackage;
	
	@Column(name = "package_start_date", nullable = false) 
	private Date dateStart;
	
	@Column(name = "package_end_date", nullable = false)
	private Date dateEnd;
	
	@Column(name = "maximum_people", nullable = false)
	private int maximumPeople ;
	
	@Column(name = "price", nullable = false)
	private int price ;
	
	@Column(name = "offer", nullable = false)
	private int offer;
	
	@Column(name = "days", nullable = false)
	private int days;
	
	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	@ManyToOne
	@JoinColumn(name = "province_id", nullable = false)
	private Province province;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamePackage() {
		return namePackage;
	}

	public void setNamePackage(String namePackage) {
		this.namePackage = namePackage;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getMaximumPeople() {
		return maximumPeople;
	}

	public void setMaximumPeople(int maximumPeople) {
		this.maximumPeople = maximumPeople;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}
	
}