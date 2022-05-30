package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.Car;

@Named
public class CarRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;
	
	public Long insert(Car car) throws Exception {
		em.persist(car);
		return car.getId();
	}
	
	public Long update(Car car) throws Exception {
		em.merge(car);
		return car.getId();
	}
	
	public void delete(Car car)throws Exception {
		em.remove(em.contains(car) ? car : em.merge(car));
	}
	
	public List<Car> findAll() throws Exception {
		List<Car> cars = new ArrayList<>();

		TypedQuery<Car> query = em.createQuery("FROM Car", Car.class);
		cars = query.getResultList();
		return cars;
	}
	
	public List<Car> findAllByService(Long id) throws Exception {
		List<Car> cars = new ArrayList<>();

		TypedQuery<Car> query = em.createQuery("FROM Car WHERE service_id = ?1", Car.class);
		query.setParameter(1, id);
		cars = query.getResultList();
		return cars;
	}
}