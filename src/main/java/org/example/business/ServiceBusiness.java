package org.example.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.Service;
import org.example.repository.ServiceRepository;

@Named
public class ServiceBusiness implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ServiceRepository serviceRepository;
	
	@Transactional
	public Long insert(Service service) throws Exception{
		return serviceRepository.insert(service);
	}
	
	@Transactional
	public Long update(Service service) throws Exception{
		return serviceRepository.update(service);
	}
	
	@Transactional
	public void delete(Service service) throws Exception{
		serviceRepository.delete(service);
	}

	@Transactional
	public List<Service> getAll() throws Exception{
		return serviceRepository.findAll();
	}
	
	@Transactional
	public List<Service> getAllHostingServices() throws Exception{
		return serviceRepository.findAllHostingService();
	}
	
	@Transactional
	public List<Service> getAllFoodServices() throws Exception{
		return serviceRepository.findAllFoodService();
	}
	
	@Transactional
	public List<Service> getAllTransportServices() throws Exception{
		return serviceRepository.findAllTransportService();
	}
	
}
