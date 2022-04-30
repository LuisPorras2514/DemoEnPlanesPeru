package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.ServiceType;

@Named
public class ServiceTypeRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;
	
	public List<ServiceType> findall() throws Exception {
		List<ServiceType> servicetypes = new ArrayList<>();

		TypedQuery<ServiceType> query = em.createQuery("FROM ServiceType", ServiceType.class);
		servicetypes = query.getResultList();
		return servicetypes;
	}
}
