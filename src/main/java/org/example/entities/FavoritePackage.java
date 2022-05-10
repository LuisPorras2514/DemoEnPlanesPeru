package org.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favorite_packages")
public class FavoritePackage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "package_travel_id", nullable = false)
	private PackageTravel packageTravel;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PackageTravel getPackageTravel() {
		return packageTravel;
	}

	public void setPackageTravel(PackageTravel packageTravel) {
		this.packageTravel = packageTravel;
	}

	public User getUser_id() {
		return user;
	}

	public void setUser_id(User user) {
		this.user = user;
	}
}
