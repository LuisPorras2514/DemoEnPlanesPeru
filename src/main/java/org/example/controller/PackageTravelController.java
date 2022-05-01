package org.example.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.example.business.DepartmentBusiness;
import org.example.business.PackageTravelBusiness;
import org.example.business.ProvinceBusiness;
import org.example.business.ServiceTypeBusiness;
import org.example.entities.Department;
import org.example.entities.PackageTravel;
import org.example.entities.Province;
import org.example.entities.ServiceType;
import org.example.util.Message;

@Named
@SessionScoped
public class PackageTravelController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PackageTravelBusiness packageTravelBusiness;
	@Inject
	private DepartmentBusiness departmentBusiness;
	@Inject
	private ProvinceBusiness provinceBusiness;
	@Inject
	private ServiceTypeBusiness serviceTypeBusiness;

	// Variable
	private PackageTravel packageTravel;
	private PackageTravel packageTravelSelected;
	private List<PackageTravel> packageTravels;

	private Department department;
	private List<Department> departments;

	private Province province;
	private List<Province> provinces;

	private double priceMaximum;

	private List<ServiceType> servicesTypeSelected;
	private List<ServiceType> servicesType;
	private ServiceType serviceType;

	@PostConstruct
	public void init() {
		packageTravel = new PackageTravel();
		packageTravels = new ArrayList<>();
		packageTravelSelected = new PackageTravel();

		department = new Department();
		departments = new ArrayList<>();

		province = new Province();
		provinces = new ArrayList<>();

		serviceType = new ServiceType();
		servicesTypeSelected = new ArrayList<>();
		servicesType = new ArrayList<>();

		getAllPackageTravel();
		getAllDeparments();
		getAllServiceType();
	}

	public void getAllPackageTravel() {
		try {
			packageTravels = packageTravelBusiness.getAll();
		} catch (Exception e) {

		}
	}

	public void getAllDeparments() {
		try {
			departments = departmentBusiness.getAllDepartment();
		} catch (Exception e) {

		}
	}

	public void getAllServiceType() {
		try {
			servicesType = serviceTypeBusiness.getAll();
		} catch (Exception e) {

		}
	}

	public void searchProductByDepartment() {
		try {
			packageTravels = packageTravelBusiness.getAllByDeparment(department.getId());
		} catch (Exception e) {
			Message.messageInfo("No se encontro Paquetes con el departamento ingresado");
		}
	}

	public void searchProductByPriceMaximum() {
		try {
			packageTravels = packageTravelBusiness.getAllByPrice(priceMaximum);
		} catch (Exception e) {
			Message.messageInfo("Error ProductController:" + e.getMessage());
		}
	}

	public String newPackageTravel() {
		try {
			this.departments = departmentBusiness.getAllDepartment();
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return "/package/insert";
	}

	public String savePackageTravel() {
		String view = "";

		try {
			if (packageTravel.getId() != null) {
				this.packageTravel.setDepartment(department);
				this.packageTravel.setProvince(province);
				this.packageTravelBusiness.update(packageTravel);

				Message.messageInfo("Paquete actualizado exitosamente");
			} else {
				this.packageTravel.setDepartment(department);
				this.packageTravel.setProvince(province);
				this.packageTravelBusiness.insert(packageTravel);

				Message.messageInfo("Paquete agregado exitosamente");
			}

			this.resetForm();
			this.getAllPackageTravel();
			view = "/package/list";
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public String editPackageTravel() {
		String view = "";
		try {
			if (this.packageTravelSelected != null) {
				this.packageTravel = packageTravelSelected;
				this.departments = departmentBusiness.getAllDepartment();
				this.provinces = provinceBusiness.getAllProvince();
				view = "/package/insert";
			} else {
				Message.messageInfo("Debe seleccionar un servicio");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public String deletePackageTravel() {
		String view = "";
		try {
			if (this.packageTravelSelected != null) {
				this.packageTravel = packageTravelSelected;
				this.packageTravelBusiness.delete(packageTravel);
				getAllPackageTravel();
				view = "/package/list";
			} else {
				Message.messageInfo("Debe seleccionar un servicio");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public String listPackageTravel() {
		this.resetForm();
		return "/package/list";
	}

	public void resetForm() {
		packageTravel = new PackageTravel();
		packageTravelSelected = new PackageTravel();
		packageTravels = new ArrayList<>();

		department = new Department();
	}

	public void provinceChange() {
		try {
			this.provinces = provinceBusiness.getAllProvinceByDepartment(department.getId());
		} catch (Exception e) {
			Message.messageInfo("Error en revoger datos");
		}
	}

	public PackageTravel getPackageTravel() {
		return packageTravel;
	}

	public void setPackageTravel(PackageTravel packageTravel) {
		this.packageTravel = packageTravel;
	}

	public PackageTravel getPackageTravelSelected() {
		return packageTravelSelected;
	}

	public void setPackageTravelSelected(PackageTravel packageTravelSelected) {
		this.packageTravelSelected = packageTravelSelected;
	}

	public List<PackageTravel> getPackageTravels() {
		return packageTravels;
	}

	public void setPackageTravels(List<PackageTravel> packageTravels) {
		this.packageTravels = packageTravels;
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

	public double getPriceMaximum() {
		return priceMaximum;
	}

	public void setPriceMaximum(double priceMaximum) {
		this.priceMaximum = priceMaximum;
	}

	public List<ServiceType> getServicesTypeSelected() {
		return servicesTypeSelected;
	}

	public void setServicesTypeSelected(List<ServiceType> servicesTypeSelected) {
		this.servicesTypeSelected = servicesTypeSelected;
	}

	public List<ServiceType> getServicesType() {
		return servicesType;
	}

	public void setServicesType(List<ServiceType> servicesType) {
		this.servicesType = servicesType;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

}
