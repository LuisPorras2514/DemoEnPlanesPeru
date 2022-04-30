package org.example.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.ServiceType;
import org.example.repository.ServiceTypeRepository;

@Named
public class ServiceTypeBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceTypeRepository serviceTypeRepository;
	
	@Transactional
	public List<ServiceType> getAll() throws Exception {
		return serviceTypeRepository.findall();
	}

}
