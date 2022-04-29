package org.example.repository;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.FoodService;

@Named
public class FoodServiceRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;
	
	public Long insert(FoodService foodService) throws Exception {
		em.persist(foodService);
		return foodService.getId();
	}
	
	public Long update(FoodService foodService) throws Exception {
		em.merge(foodService);
		return foodService.getId();
	}
	
	public void delete(FoodService foodService)throws Exception {
		em.remove(em.contains(foodService) ? foodService : em.merge(foodService));
	}
	
	public List<FoodService> findAll() throws Exception {
		List<FoodService> FoodServices = new ArrayList<>();
		TypedQuery<FoodService> query = em.createQuery("From FoodService fs", FoodService.class);
		FoodServices = query.getResultList();
		return FoodServices;
	}

}
