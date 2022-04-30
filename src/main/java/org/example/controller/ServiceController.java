package org.example.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.example.business.DepartmentBusiness;
import org.example.business.ServiceBusiness;
import org.example.business.ProvinceBusiness;
import org.example.entities.Department;
import org.example.entities.Service;
import org.example.entities.Province;
import org.example.util.Message;
import org.primefaces.event.SelectEvent;

@Named
@SessionScoped
public class ServiceController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServiceBusiness ServiceBusiness;
	@Inject
	private DepartmentBusiness departmentBusiness;
	@Inject
	private ProvinceBusiness provinceBusiness;

	private Service service;
	private Service serviceSelected;
	private List<Service> services;
	private List<Service> hostingServices;
	private List<Service> foodServices;
	private List<Service> transportServices;

	private Department department;
	private List<Department> departments;

	private Province province;
	private List<Province> provinces;

	@PostConstruct
	public void init() {
		service = new Service();
		serviceSelected = new Service();
		services = new ArrayList<>();
		hostingServices = new ArrayList<>();
		foodServices = new ArrayList<>();
		transportServices = new ArrayList<>();

		department = new Department();
		departments = new ArrayList<>();

		province = new Province();
		provinces = new ArrayList<>();

		getAllService();
		getAllHostingService();
		getAllFoodService();
		getAllTransportService();
		
	}

	public void getAllService() {
		try {
			services = ServiceBusiness.getAll();
		} catch (Exception e) {

		}
	}
	
	public void getAllHostingService() {
		try {
			hostingServices = ServiceBusiness.getAllHostingServices();
		} catch (Exception e) {

		}
	}
	
	public void getAllFoodService() {
		try {
			foodServices = ServiceBusiness.getAllFoodServices();
		} catch (Exception e) {

		}
	}
	
	public void getAllTransportService() {
		try {
			transportServices = ServiceBusiness.getAllTransportServices();
		} catch (Exception e) {

		}
	}

	public String newService() {
		try {
			this.departments = departmentBusiness.getAllDepartment();
			this.resetForm();
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return "insert.xhtml";
	}

	// GUARDAR
	public String saveService() {
		String view = "";
		try {
			if (service.getId() != null) {
				this.service.setDepartment(department);
				this.service.setProvince(province);
				this.ServiceBusiness.update(service);
				Message.messageInfo("Servicio actualizado exitosamente");
			} else {
				this.service.setStar(1);
				this.service.setDepartment(department);
				this.service.setProvince(province);
				this.ServiceBusiness.insert(service);
				Message.messageInfo("Servicio agregado exitosamente");
			}
			this.getAllService();
			this.resetForm();
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public String editService() {
		String view = "";
		try {
			if (this.serviceSelected != null) {
				this.service = serviceSelected;
				this.departments = departmentBusiness.getAllDepartment();
				this.provinces = provinceBusiness.getAllProvince();
				view = "/services/insert";
			} else {
				Message.messageInfo("Debe seleccionar un servicio");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public String deleteService() {
		String view = "";
		try {
			if (this.serviceSelected != null) {
				this.service = serviceSelected;
				ServiceBusiness.delete(service);
				getAllService();
				view = "/services/list";
			} else {
				Message.messageInfo("Debe seleccionar un servicio");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public void resetForm() {
		service = new Service();
	}

	public String listService() {
		this.resetForm();
		return "list.xhtml";
	}

	public void selectService(SelectEvent e) {
		this.serviceSelected = (Service) e.getObject();
	}

	public void provinceChange() {
		try {
			Message.messageInfo("Se actualizo");
			this.provinces = provinceBusiness.getAllProvinceByDepartment(department.getId());
		} catch (Exception e) {
			Message.messageInfo("Error en revoger datos");
		}
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Service getServiceSelected() {
		return serviceSelected;
	}

	public void setServiceSelected(Service serviceSelected) {
		this.serviceSelected = serviceSelected;
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

}
