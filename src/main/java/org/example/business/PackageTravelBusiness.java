package org.example.business;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.example.entities.PackageTravel;
import org.example.repository.PackageTravelRepository;

@Named
public class PackageTravelBusiness implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;

	@Inject
	private PackageTravelRepository packageTravelRepository;

	@Transactional
	public Long insert(PackageTravel packageTravel) throws Exception {
		return packageTravelRepository.insert(packageTravel);
	}

	@Transactional
	public Long update(PackageTravel packageTravel) throws Exception {
		return packageTravelRepository.update(packageTravel);
	}

	@Transactional
	public void delete(PackageTravel packageTravel) throws Exception {
		packageTravelRepository.delete(packageTravel);
	}

	@Transactional
	public List<PackageTravel> getAll() throws Exception {
		return packageTravelRepository.findAll();
	}

	@Transactional
	public List<PackageTravel> getAllByDeparment(Long id) throws Exception {
		return packageTravelRepository.findAllByDeparment(id);
	}

	@Transactional
	public List<PackageTravel> getAllByProvince(Long id) throws Exception {
		return packageTravelRepository.findAllByProvince(id);
	}

	@Transactional
	public List<PackageTravel> getAllByPrice(double priceMinimal, double priceMaximum) throws Exception {
		return packageTravelRepository.findAllByPrice(priceMinimal, priceMaximum);
	}

	@Transactional
	public List<PackageTravel> getAllByPeople(int People) throws Exception {
		return packageTravelRepository.findAllByPeople(People);
	}

	@Transactional
	public List<PackageTravel> getAllByDate(Date inicio, Date fin) throws Exception {
		return packageTravelRepository.findAllByDate(inicio, fin);
	}

}
