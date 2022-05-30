package org.example.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.example.business.CarBrandBusiness;
import org.example.business.CarBusiness;
import org.example.business.CarModelBusiness;
import org.example.business.DepartmentBusiness;
import org.example.business.PlateBusiness;
import org.example.business.ServiceBusiness;
import org.example.business.ServiceTypeBusiness;
import org.example.business.ProvinceBusiness;
import org.example.business.RoomBusiness;
import org.example.business.RoomTypeBusiness;
import org.example.entities.Car;
import org.example.entities.CarBrand;
import org.example.entities.CarModel;
import org.example.entities.Department;
import org.example.entities.Plate;
import org.example.entities.Service;
import org.example.entities.ServiceType;
import org.example.entities.Province;
import org.example.entities.Room;
import org.example.entities.RoomType;
import org.example.util.Message;

@Named
@SessionScoped
public class ServiceController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceBusiness serviceBusiness;
	@Inject
	private DepartmentBusiness departmentBusiness;
	@Inject
	private ProvinceBusiness provinceBusiness;
	@Inject
	private ServiceTypeBusiness serviceTypeBusiness;
	@Inject
	private RoomBusiness roomBusiness;
	@Inject
	private CarBusiness carBusiness;
	@Inject
	private PlateBusiness plateBusiness;
	@Inject
	private RoomTypeBusiness roomTypeBusiness;
	@Inject
	private CarBrandBusiness carBrandBusiness;
	@Inject
	private CarModelBusiness carModelBusiness;

	// VARIABLES
	private Service service;
	private Service serviceHostingSelected;
	private Service serviceFoodSelected;
	private Service serviceTransportSelected;
	private List<Service> services;
	private List<Service> hostingServices;
	private List<Service> foodServices;
	private List<Service> transportServices;

	private Department department;
	private List<Department> departments;

	private Province province;
	private List<Province> provinces;

	private ServiceType serviceType;
	private List<ServiceType> serviceTypes;

	private Room room;
	private List<Room> rooms;
	private Room roomSelected;

	private RoomType roomType;
	private List<RoomType> roomTypes;

	private Car car;
	private List<Car> cars;
	private Car carSelected;

	private CarBrand carBrand;
	private List<CarBrand> carBrands;

	private CarModel carModel;
	private List<CarModel> carModels;

	private Plate plate;
	private List<Plate> plates;

	private int contador;

	// METODOS
	@PostConstruct
	public void init() {
		service = new Service();
		serviceHostingSelected = new Service();
		serviceFoodSelected = new Service();
		serviceTransportSelected = new Service();

		services = new ArrayList<>();
		hostingServices = new ArrayList<>();
		foodServices = new ArrayList<>();
		transportServices = new ArrayList<>();

		department = new Department();
		departments = new ArrayList<>();

		province = new Province();
		provinces = new ArrayList<>();

		serviceType = new ServiceType();
		serviceTypes = new ArrayList<>();

		room = new Room();
		rooms = new ArrayList<>();
		roomSelected = new Room();

		roomType = new RoomType();
		roomTypes = new ArrayList<>();

		carBrand = new CarBrand();
		carBrands = new ArrayList<>();

		carModel = new CarModel();
		carModels = new ArrayList<>();

		plate = new Plate();
		plates = new ArrayList<>();

		car = new Car();
		cars = new ArrayList<>();
		carSelected = new Car();

		contador = 0;

		getAllService();
	}

	public String newService() {
		try {
			this.departments = departmentBusiness.getAllDepartment();
			this.serviceTypes = serviceTypeBusiness.getAll();
			this.service = new Service();
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return "/services/insert";
	}

	// ROOM
	public void saveRoom() {
		getAllRoomsService();
		try {
			if (rooms.size() < 5) {
				if (room.getId() != null) {
					this.room.setRoomType(roomType);
					this.roomBusiness.update(room);
					Message.messageInfo("Habitación actualizada correctamente");
				} else {
					this.room.setRoomType(roomType);
					this.room.setService(service);
					this.roomBusiness.insert(room);
					Message.messageInfo("Habitación guardado correctamente");
				}
			} else {
				Message.messageInfo("Solo se puede agregar 5 habitaciones por servicio");
			}
			// RestablecerDatos
			this.roomTypes = roomTypeBusiness.getAll();
			this.room = new Room();
			// RecogerDatos
			getAllRoomsService();
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
	}

	public void deleteRoom() {
		try {
			if (this.room != null) {
				this.roomBusiness.delete(room);
				Message.messageInfo("Habitación eliminada");
			} else {
				Message.messageInfo("Debe seleccionar una habitación");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		getAllCarService();
	}

	// PLATE
	public String savePlate() {
		try {
			if (this.plate.getId() != null) {
				this.plate.setService(service);
				this.plateBusiness.update(plate);
				Message.messageInfo("Precio de platos actualizada correctamente");
			} else {
				this.plate.setService(service);
				this.plateBusiness.insert(plate);
				Message.messageInfo("Precio de platos guardado correctamente");
			}
			// RestablecerDatos
			this.plates = new ArrayList<>();
			this.plate = new Plate();
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return listService();
	}

	// CAR
	public void saveCar() {
		getAllCarService();
		try {
			if (cars.size() < 5) {
				if (car.getId() != null) {
					this.car.setCarBrand(carBrand);
					this.car.setCarModel(carModel);
					this.carBusiness.update(car);
					Message.messageInfo("Carro actualizada correctamente");
				} else {
					this.car.setCarBrand(carBrand);
					this.car.setCarModel(carModel);
					this.car.setService(service);
					this.carBusiness.insert(car);
					Message.messageInfo("Carro guardado correctamente");
				}
			} else {
				Message.messageInfo("Solo se puede agregar 5 habitaciones por servicio");
			}
			// RestablecerDatos
			this.carBrands = carBrandBusiness.getAllCarBrand();
			this.carModel = new CarModel();
			this.carModels = new ArrayList<>();
			this.car = new Car();
			// RecogerDatos
			getAllCarService();
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
	}

	public void deleteCar() {
		try {
			if (this.car != null) {
				this.carBusiness.delete(car);
				Message.messageInfo("Carro eliminado");
			} else {
				Message.messageInfo("Debe seleccionar un carro");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		this.carModel = new CarModel();
		this.carModels = new ArrayList<>();
		this.car = new Car();
		getAllCarService();
	}

	// SERVICE
	public String saveService() {
		String view = "";
		try {
			if (service.getId() != null) {
				this.service.setDepartment(department);
				this.service.setProvince(province);
				this.service.setServiceType(serviceType);
				this.serviceBusiness.update(service);
				getAllRoomsService();
				Message.messageInfo("Servicio actualizado exitosamente");
			} else {
				this.service.setStar(1);
				this.service.setDepartment(department);
				this.service.setProvince(province);
				this.service.setServiceType(serviceType);
				this.serviceBusiness.insert(service);
				Message.messageInfo("Servicio agregado exitosamente");
			}

			if (serviceType.getId() == 1) {
				this.roomTypes = roomTypeBusiness.getAll();
				view = "/services/insert-room";
			} else if (serviceType.getId() == 2) {
				this.plates = plateBusiness.getAllByService(service.getId());
				if (plates.size() > 0) {
					plate = plates.get(0);
				} else {
					plate = new Plate();
				}
				view = "/services/insert-plate";
			} else if (serviceType.getId() == 3) {
				this.carBrands = carBrandBusiness.getAllCarBrand();
				view = "/services/insert-car";
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public String editHostingService() {
		String view = "";
		try {
			if (this.serviceHostingSelected != null) {
				this.service = serviceHostingSelected;
				this.departments = departmentBusiness.getAllDepartment();
				this.provinces = provinceBusiness.getAllProvince();
				this.serviceTypes = serviceTypeBusiness.getAll();
				view = "/services/insert";
			} else {
				Message.messageInfo("Debe seleccionar un servicio");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public String editFoodService() {
		String view = "";
		try {
			if (this.serviceFoodSelected != null) {
				this.service = serviceFoodSelected;
				this.departments = departmentBusiness.getAllDepartment();
				this.provinces = provinceBusiness.getAllProvince();
				this.serviceTypes = serviceTypeBusiness.getAll();
				view = "/services/insert";
			} else {
				Message.messageInfo("Debe seleccionar un servicio");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public String editTransportService() {
		String view = "";
		try {
			if (this.serviceTransportSelected != null) {
				this.service = serviceTransportSelected;
				this.departments = departmentBusiness.getAllDepartment();
				this.provinces = provinceBusiness.getAllProvince();
				this.serviceTypes = serviceTypeBusiness.getAll();
				view = "/services/insert";
			} else {
				Message.messageInfo("Debe seleccionar un servicio");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public String deleteHostingService() {
		String view = "";
		try {
			if (this.serviceHostingSelected != null) {
				this.service = serviceHostingSelected;
				serviceBusiness.delete(service);
				getAllService();
				resetForm();
				view = "/services/list";
			} else {
				Message.messageInfo("Debe seleccionar un servicio");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public String deleteFoodService() {
		String view = "";
		try {
			if (this.serviceFoodSelected != null) {
				this.service = serviceFoodSelected;
				serviceBusiness.delete(service);
				getAllService();
				resetForm();
				view = "/services/list";
			} else {
				Message.messageInfo("Debe seleccionar un servicio");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public String deleteTransportService() {
		String view = "";
		try {
			if (this.serviceTransportSelected != null) {
				this.service = serviceTransportSelected;
				serviceBusiness.delete(service);
				getAllService();
				resetForm();
				view = "/services/list";
			} else {
				Message.messageInfo("Debe seleccionar un servicio");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	// GENERALS
	public void resetForm() {
		service = new Service();
		serviceHostingSelected = new Service();
		serviceFoodSelected = new Service();
		serviceTransportSelected = new Service();

		departments = new ArrayList<>();
		department = new Department();

		provinces = new ArrayList<>();
		province = new Province();

		plates = new ArrayList<>();
		plate = new Plate();

		rooms = new ArrayList<>();
		room = new Room();

		cars = new ArrayList<>();
		car = new Car();

		contador = 0;
	}

	public String listService() {
		this.resetForm();
		getAllService();
		return "/services/list";
	}

	public void getAllService() {
		try {
			services = serviceBusiness.getAll();
			hostingServices = serviceBusiness.getAllHostingServices();
			foodServices = serviceBusiness.getAllFoodServices();
			transportServices = serviceBusiness.getAllTransportServices();
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
	}

	public void getAllRoomsService() {
		try {
			this.rooms = roomBusiness.getAllByService(service.getId());
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
	}

	public void getAllCarService() {
		try {
			this.cars = carBusiness.getAllByService(service.getId());
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
	}

	public void provinceChange() {
		try {
			this.provinces = provinceBusiness.getAllProvinceByDepartment(department.getId());
		} catch (Exception e) {
			Message.messageInfo("Error en recoger datos");
		}
	}

	public void carBrandChange() {
		try {
			this.carModels = carModelBusiness.getAllCarModelByBrand(carBrand.getId());
		} catch (Exception e) {
			Message.messageInfo("Error en recoger datos");
		}
	}

	// GETTERS AND SETTERS
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<Province> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}

	public List<Service> getHostingServices() {
		return hostingServices;
	}

	public void setHostingServices(List<Service> hostingServices) {
		this.hostingServices = hostingServices;
	}

	public List<Service> getFoodServices() {
		return foodServices;
	}

	public void setFoodServices(List<Service> foodServices) {
		this.foodServices = foodServices;
	}

	public List<Service> getTransportServices() {
		return transportServices;
	}

	public void setTransportServices(List<Service> transportServices) {
		this.transportServices = transportServices;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public List<ServiceType> getServiceTypes() {
		return serviceTypes;
	}

	public void setServiceTypes(List<ServiceType> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}

	public Service getServiceHostingSelected() {
		return serviceHostingSelected;
	}

	public void setServiceHostingSelected(Service serviceHostingSelected) {
		this.serviceHostingSelected = serviceHostingSelected;
	}

	public Service getServiceFoodSelected() {
		return serviceFoodSelected;
	}

	public void setServiceFoodSelected(Service serviceFoodSelected) {
		this.serviceFoodSelected = serviceFoodSelected;
	}

	public Service getServiceTransportSelected() {
		return serviceTransportSelected;
	}

	public void setServiceTransportSelected(Service serviceTransportSelected) {
		this.serviceTransportSelected = serviceTransportSelected;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Room getRoomSelected() {
		return roomSelected;
	}

	public void setRoomSelected(Room roomSelected) {
		this.roomSelected = roomSelected;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public List<RoomType> getRoomTypes() {
		return roomTypes;
	}

	public void setRoomTypes(List<RoomType> roomTypes) {
		this.roomTypes = roomTypes;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Car getCarSelected() {
		return carSelected;
	}

	public void setCarSelected(Car carSelected) {
		this.carSelected = carSelected;
	}

	public CarBrand getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(CarBrand carBrand) {
		this.carBrand = carBrand;
	}

	public List<CarBrand> getCarBrands() {
		return carBrands;
	}

	public void setCarBrands(List<CarBrand> carBrands) {
		this.carBrands = carBrands;
	}

	public CarModel getCarModel() {
		return carModel;
	}

	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
	}

	public List<CarModel> getCarModels() {
		return carModels;
	}

	public void setCarModels(List<CarModel> carModels) {
		this.carModels = carModels;
	}

	public List<Plate> getPlates() {
		return plates;
	}

	public void setPlates(List<Plate> plates) {
		this.plates = plates;
	}

	public Plate getPlate() {
		return plate;
	}

	public void setPlate(Plate plate) {
		this.plate = plate;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

}
