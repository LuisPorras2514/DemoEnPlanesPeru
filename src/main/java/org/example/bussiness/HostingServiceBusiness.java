package org.example.bussiness;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.HostingService;
import org.example.repository.HostingServiceRepository;

@Named
public class HostingServiceBusiness implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private HostingServiceRepository hostingServiceRepository;
	
	@Transactional
	public Long insert(HostingService hostingService) throws Exception{
		return hostingServiceRepository.insert(hostingService);
	}
	
	@Transactional
	public Long update(HostingService hostingService) throws Exception{
		return hostingServiceRepository.update(hostingService);
	}
	
	@Transactional
	public void delete(HostingService hostingService) throws Exception{
		hostingServiceRepository.delete(hostingService);
	}

	public List<HostingService> getAll() throws Exception{
		return hostingServiceRepository.findAll();
	}
}
