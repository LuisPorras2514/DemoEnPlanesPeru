package org.example.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.example.bussiness.HostingServiceBusiness;
import org.example.entities.HostingService;
import org.example.util.Message;
import org.primefaces.event.SelectEvent;

@Named
@SessionScoped
public class HostingServiceController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private HostingServiceBusiness hostingServiceBusiness;

	private HostingService hostingService;
	private HostingService hostingServiceSelected;
	private List<HostingService> hostingServices;

	@PostConstruct
	public void init() {
		hostingService = new HostingService();
		hostingServiceSelected = new HostingService();
		hostingServices = new ArrayList<>();

		getAllHostingService();
	}

	public void getAllHostingService() {
		try {
			hostingServices = hostingServiceBusiness.getAll();
		} catch (Exception e) {

		}
	}

	public String newHostingService() {
		try {
			this.resetForm();
		} catch (Exception e) {

		}
		return "insert.xhtml";
	}

	//GUARDAR
	public String saveHostingService() {
		String view = "";
		try {
			if (hostingService.getId() != null) {
				hostingServiceBusiness.update(hostingService);
				Message.messageInfo("Servicio actualizado exitosamente");
			} else {
				this.hostingService.setStar(1);
				hostingServiceBusiness.insert(hostingService);
				Message.messageInfo("Servicio agregado exitosamente");
			}
			this.getAllHostingService();
			this.resetForm();
			view = "list";
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public String editHostingService() {
		String view = "";
		try {
			if (this.hostingServiceSelected != null) {
				this.hostingService = hostingServiceSelected;
				view = "/Services/hosting/update";
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
			if (this.hostingServiceSelected != null) {				
				hostingServiceBusiness.delete(hostingServiceSelected);
				getAllHostingService();
				view = "/Services/hosting/list";
			} else {
				Message.messageInfo("Debe seleccionar un servicio");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public void resetForm() {
		hostingService = new HostingService();
	}

	public String listHostingService() {
		return "list.xhtml";
	}

	public void selectHostingService(SelectEvent e) {
		this.hostingServiceSelected = (HostingService) e.getObject();
	}

	public HostingService getHostingService() {
		return hostingService;
	}

	public void setHostingService(HostingService hostingService) {
		this.hostingService = hostingService;
	}

	public HostingService getHostingServiceSelected() {
		return hostingServiceSelected;
	}

	public void setHostingServiceSelected(HostingService hostingServiceSelected) {
		this.hostingServiceSelected = hostingServiceSelected;
	}

	public List<HostingService> getHostingServices() {
		return hostingServices;
	}

	public void setHostingServices(List<HostingService> hostingServices) {
		this.hostingServices = hostingServices;
	}

}
