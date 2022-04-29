package org.example.bussiness;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.FoodService;
import org.example.repository.FoodServiceRepository;

@Named
public class FoodServiceBusiness implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FoodServiceRepository foodServiceRepository;
	
	@Transactional
	public Long insert(FoodService foodService) throws Exception{
		return foodServiceRepository.insert(foodService);
	}
	
	@Transactional
	public Long update(FoodService foodService) throws Exception{
		return foodServiceRepository.update(foodService);
	}
	
	@Transactional
	public void delete(FoodService foodService) throws Exception{
		foodServiceRepository.delete(foodService);
	}

	public List<FoodService> getAll() throws Exception{
		return foodServiceRepository.findAll();
	}
	
}
