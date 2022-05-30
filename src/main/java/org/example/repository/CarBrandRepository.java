package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.CarBrand;

@Named
public class CarBrandRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;

	public List<CarBrand> findAll() throws Exception {
		List<CarBrand> carBrands = new ArrayList<>();
		TypedQuery<CarBrand> query = em.createQuery("FROM CarBrand", CarBrand.class);
		carBrands = query.getResultList();
		return carBrands;
	}
}
