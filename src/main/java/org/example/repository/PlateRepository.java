package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.Plate;

public class PlateRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;

	public Long insert(Plate plate) throws Exception {
		em.persist(plate);
		return plate.getId();
	}
	
	public Long update(Plate plate) throws Exception {
		em.merge(plate);
		return plate.getId();
	}
	
	public void delete(Plate plate)throws Exception {
		em.remove(em.contains(plate) ? plate : em.merge(plate));
	}

	public List<Plate> findAll() throws Exception {
		List<Plate> cars = new ArrayList<>();

		TypedQuery<Plate> query = em.createQuery("FROM Plate", Plate.class);
		cars = query.getResultList();
		return cars;
	}

	public List<Plate> findAllByService(Long id) throws Exception {
		List<Plate> cars = new ArrayList<>();

		TypedQuery<Plate> query = em.createQuery("FROM Plate WHERE service_id = ?1", Plate.class);
		query.setParameter(1, id);
		cars = query.getResultList();
		return cars;
	}

}
