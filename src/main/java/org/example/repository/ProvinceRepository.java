package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import org.example.entities.Province;

@Named
public class ProvinceRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;

	public List<Province> findall() throws Exception {
		List<Province> provinces = new ArrayList<>();

		TypedQuery<Province> query = em.createQuery("FROM Province p", Province.class);
		provinces = query.getResultList();
		return provinces;
	}
	
	public List<Province> findAllByDepartment(Long id) throws Exception {
		List<Province> provinces = new ArrayList<>();

		TypedQuery<Province> query = em.createQuery("FROM Province WHERE department_id = ?1", Province.class);
		
		query.setParameter(1, id);
		provinces = query.getResultList();
		return provinces;
	}

}
