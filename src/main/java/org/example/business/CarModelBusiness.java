package org.example.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.CarModel;
import org.example.repository.CarModelRepository;

@Named
public class CarModelBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarModelRepository carModelRepository;
	
	@Transactional
	public List<CarModel> getAllCarModel() throws Exception {
		return carModelRepository.findAll();
	}
	
	@Transactional
	public List<CarModel> getAllCarModelByBrand(Long id) throws Exception {
		return carModelRepository.findAllByBrand(id);
	}

}
