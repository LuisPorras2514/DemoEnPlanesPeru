package org.example.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.example.entities.Province;
import org.example.repository.ProvinceRepository;

@Named
public class ProvinceBusiness implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;

	@Inject
	private ProvinceRepository provinceRepository;

	@Transactional
	public List<Province> getAllProvince() throws Exception {
		return provinceRepository.findall();
	}
	
	@Transactional
	public List<Province> getAllProvinceByDepartment(Long id) throws Exception {
		return provinceRepository.findAllByDepartment(id);
	}
}
