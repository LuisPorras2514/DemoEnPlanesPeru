package org.example.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.Car;
import org.example.entities.Plate;
import org.example.entities.Room;
import org.example.entities.Service;
import org.example.repository.CarRepository;
import org.example.repository.PlateRepository;
import org.example.repository.RoomRepository;
import org.example.repository.ServiceRepository;

@Named
public class ServiceBusiness implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceRepository serviceRepository;
	@Inject
	private PlateRepository plateRepository;
	@Inject
	private CarRepository carRepository;
	@Inject
	private RoomRepository roomRepository;

	@Transactional
	public Long insert(Service service) throws Exception {
		return serviceRepository.insert(service);
	}

	@Transactional
	public Long update(Service service) throws Exception {
		return serviceRepository.update(service);
	}

	@Transactional
	public void delete(Service service) throws Exception{
		//implementaciónCascada
		{
			if(service.getServiceType().getId() == 1) {
				List<Room> rooms = roomRepository.findAll();
				for(Room r : rooms) {
					roomRepository.delete(r);
				}
			} else if(service.getServiceType().getId() == 2) {
				List<Plate> plates = plateRepository.findAll();
				for(Plate p : plates) {
					plateRepository.delete(p);
				}
			} else if(service.getServiceType().getId() == 3) {
				List<Car> cars = carRepository.findAll();
				for(Car c : cars) {
					carRepository.delete(c);
				}
			}
		}
		
		serviceRepository.delete(service);
	}

	@Transactional
	public List<Service> getAll() throws Exception {
		return serviceRepository.findAll();
	}

	@Transactional
	public List<Service> getAllHostingServices() throws Exception {
		return serviceRepository.findAllHostingService();
	}

	@Transactional
	public List<Service> getAllFoodServices() throws Exception {
		return serviceRepository.findAllFoodService();
	}

	@Transactional
	public List<Service> getAllTransportServices() throws Exception {
		return serviceRepository.findAllTransportService();
	}

}
