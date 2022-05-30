package org.example.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.CarBrand;
import org.example.repository.CarBrandRepository;

@Named
public class CarBrandBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarBrandRepository carBrandRepository;
	
	@Transactional
	public List<CarBrand> getAllCarBrand() throws Exception {
		return carBrandRepository.findAll();
	}

}
