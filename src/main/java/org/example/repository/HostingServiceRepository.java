package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.HostingService;

@Named
public class HostingServiceRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;

	public Long insert(HostingService hostingservice) throws Exception {
		em.persist(hostingservice);
		return hostingservice.getId();
	}
	
	public Long update(HostingService hostingservice) throws Exception {
		em.merge(hostingservice);
		return hostingservice.getId();
	}
	
	public void delete(HostingService hostingservice)throws Exception {
		em.remove(em.contains(hostingservice) ? hostingservice : em.merge(hostingservice));
	}
	
	public List<HostingService> findAll() throws Exception {
		List<HostingService> hostingServices = new ArrayList<>();
		TypedQuery<HostingService> query = em.createQuery("From HostingService h", HostingService.class);
		hostingServices = query.getResultList();
		return hostingServices;
	}

}
