package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.CarModel;

@Named
public class CarModelRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;

	public List<CarModel> findAll() throws Exception {
		List<CarModel> carModels = new ArrayList<>();
		TypedQuery<CarModel> query = em.createQuery("FROM CarBrand", CarModel.class);
		carModels = query.getResultList();
		return carModels;
	}

	public List<CarModel> findAllByBrand(Long id) throws Exception {
		List<CarModel> carModels = new ArrayList<>();
		TypedQuery<CarModel> query = em.createQuery("FROM CarModel WHERE car_brand_id = ?1", CarModel.class);
		query.setParameter(1, id);
		carModels = query.getResultList();
		return carModels;
	}
}
