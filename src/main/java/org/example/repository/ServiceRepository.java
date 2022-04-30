package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.Service;

@Named
public class ServiceRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;

	public Long insert(Service service) throws Exception {
		em.persist(service);
		return service.getId();
	}
	
	public Long update(Service service) throws Exception {
		em.merge(service);
		return service.getId();
	}
	
	public void delete(Service service)throws Exception {
		em.remove(em.contains(service) ? service : em.merge(service));
	}
	
	public List<Service> findAll() throws Exception {
		List<Service> services = new ArrayList<>();
		TypedQuery<Service> query = em.createQuery("From Service", Service.class);
		services = query.getResultList();
		return services;
	}
	
	public List<Service> findAllHostingService() throws Exception {
		List<Service> services = new ArrayList<>();

		TypedQuery<Service> query = em.createQuery("FROM Service WHERE service_type_id = 1", Service.class);
		
		services = query.getResultList();
		return services;
	}
	
	public List<Service> findAllFoodService() throws Exception {
		List<Service> services = new ArrayList<>();

		TypedQuery<Service> query = em.createQuery("FROM Service WHERE service_type_id = 2", Service.class);
		
		services = query.getResultList();
		return services;
	}
	
	public List<Service> findAllTransportService() throws Exception {
		List<Service> services = new ArrayList<>();

		TypedQuery<Service> query = em.createQuery("FROM Service WHERE service_type_id = 3", Service.class);
		
		services = query.getResultList();
		return services;
	}

}
