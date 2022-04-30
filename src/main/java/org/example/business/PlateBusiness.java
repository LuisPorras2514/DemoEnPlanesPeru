package org.example.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.Plate;
import org.example.repository.PlateRepository;

@Named
public class PlateBusiness implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PlateRepository plateRepository;

	@Transactional
	public Long insert(Plate plate) throws Exception{
		return plateRepository.insert(plate);
	}
	
	@Transactional
	public Long update(Plate plate) throws Exception{
		return plateRepository.update(plate);
	}
	
	@Transactional
	public void delete(Plate plate) throws Exception{
		plateRepository.delete(plate);
	}

	@Transactional
	public List<Plate> getAll() throws Exception{
		return plateRepository.findAll();
	}
	
	@Transactional
	public List<Plate> getAllByService(Long id) throws Exception{
		return plateRepository.findAllByService(id);
	}
}
