package org.example.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.Car;
import org.example.repository.CarRepository;

@Named
public class CarBusiness implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarRepository carRepository;
	
	@Transactional
	public Long insert(Car car) throws Exception{
		return carRepository.insert(car);
	}
	
	@Transactional
	public Long update(Car car) throws Exception{
		return carRepository.update(car);
	}
	
	@Transactional
	public void delete(Car car) throws Exception{
		carRepository.delete(car);
	}

	@Transactional
	public List<Car> getAll() throws Exception{
		return carRepository.findAll();
	}
	
	@Transactional
	public List<Car> getAllByService(Long id) throws Exception{
		return carRepository.findAllByService(id);
	}
}
