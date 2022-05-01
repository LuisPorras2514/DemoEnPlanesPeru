package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.PackageTravel;

@Named
public class PackageTravelRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;
	
	public Long insert(PackageTravel packageTravel) throws Exception {
		em.persist(packageTravel);
		return packageTravel.getId();
	}
	
	public Long update(PackageTravel packageTravel) throws Exception {
		em.merge(packageTravel);
		return packageTravel.getId();
	}
	
	public void delete(PackageTravel packageTravel)throws Exception {
		em.remove(em.contains(packageTravel) ? packageTravel : em.merge(packageTravel));
	}
	
	public List<PackageTravel> findAll() throws Exception {
		List<PackageTravel> packageTravels = new ArrayList<>();
		TypedQuery<PackageTravel> query = em.createQuery("From PackageTravel", PackageTravel.class);
		packageTravels = query.getResultList();
		return packageTravels;
	}
	
	//PorDepartamento
	public List<PackageTravel> findAllByDeparment(Long id) throws Exception {
		List<PackageTravel> packageTravels = new ArrayList<>();
		
		TypedQuery<PackageTravel> query = em.createQuery("FROM PackageTravel WHERE department_id = ?1", PackageTravel.class);
		
		query.setParameter(1, id);
		packageTravels = query.getResultList();
		return packageTravels;
	}
	
	//PorProvincia
	public List<PackageTravel> findAllByProvince(Long id) throws Exception {
		List<PackageTravel> packageTravels = new ArrayList<>();
		
		TypedQuery<PackageTravel> query = em.createQuery("FROM PackageTravel WHERE province_id = ?1", PackageTravel.class);
		
		query.setParameter(1, id);
		packageTravels = query.getResultList();
		return packageTravels;
	}
	
	//PorPrecio
	public List<PackageTravel> findAllByPrice(double priceMinimal, double priceMaximum) throws Exception {
		List<PackageTravel> packageTravels = new ArrayList<>();
		
		TypedQuery<PackageTravel> query = em.createQuery("FROM PackageTravel WHERE price BETWEEN ?1 AND ?2", PackageTravel.class); 
		
		query.setParameter(1, priceMinimal);
		query.setParameter(2, priceMaximum);
		packageTravels = query.getResultList();
		return packageTravels;
	}
	
	//PorCantidadDePersonas
	public List<PackageTravel> findAllByPeople(int People) throws Exception {
		List<PackageTravel> packageTravels = new ArrayList<>();
		
		TypedQuery<PackageTravel> query = em.createQuery("FROM PackageTravel WHERE maximum_people <= ?1", PackageTravel.class); 
		
		query.setParameter(1, People);
		packageTravels = query.getResultList();
		return packageTravels;
	}
	
	//PorFecha
	public List<PackageTravel> findAllByDate(Date inicio, Date fin) throws Exception {
		List<PackageTravel> packageTravels = new ArrayList<>();
		
		TypedQuery<PackageTravel> query = em.createQuery("FROM PackageTravel WHERE package_start_date >= 1 AND package_end_date <= ?2", PackageTravel.class); 
		
		query.setParameter(1, inicio);
		query.setParameter(1, fin);
		
		packageTravels = query.getResultList();
		return packageTravels;
	}
	

}
