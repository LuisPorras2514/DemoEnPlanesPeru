package org.example.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.example.bussiness.FoodServiceBusiness;
import org.example.entities.FoodService;
import org.example.util.Message;
import org.primefaces.event.SelectEvent;

@Named
@SessionScoped
public class FoodServiceController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FoodServiceBusiness foodServiceBusiness;

	private FoodService foodService;
	private FoodService foodServiceSelected;
	private List<FoodService> foodServices;

	@PostConstruct
	public void init() {
		foodService = new FoodService();
		foodServiceSelected = new FoodService();
		foodServices = new ArrayList<>();

		getAllFoodService();
	}

	public void getAllFoodService() {
		try {
			foodServices = foodServiceBusiness.getAll();
		} catch (Exception e) {

		}
	}

	public String newFoodService() {
		try {
			this.resetForm();
		} catch (Exception e) {

		}
		return "insert.xhtml";
	}

	public String saveFoodService() {
		String view = "";
		try {
			if (foodService.getId() != null) {
				foodServiceBusiness.update(foodService);
				Message.messageInfo("Servicio actualizado exitosamente");
			} else {
				this.foodService.setStar(1);
				foodServiceBusiness.insert(foodService);
				Message.messageInfo("Servicio agregado exitosamente");
			}
			this.getAllFoodService();
			this.resetForm();
			view = "list";

		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public String editFoodService() {
		String view = "";
		try {
			if (this.foodServiceSelected != null) {
				this.foodService = foodServiceSelected;
				view = "/Services/food/update";
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
			if (this.foodServiceSelected != null) {
				foodServiceBusiness.delete(foodServiceSelected);
				getAllFoodService();
				view = "/Services/food/list";
			} else {
				Message.messageInfo("Debe seleccionar un servicio");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}

	public void resetForm() {
		foodService = new FoodService();
	}

	public String listFoodService() {
		return "list.xhtml";
	}

	public void selectFoodService(SelectEvent e) {
		this.foodServiceSelected = (FoodService) e.getObject();
	}

	public FoodService getFoodService() {
		return foodService;
	}

	public void setFoodService(FoodService foodService) {
		this.foodService = foodService;
	}

	public FoodService getFoodServiceSelected() {
		return foodServiceSelected;
	}

	public void setFoodServiceSelected(FoodService foodServiceSelected) {
		this.foodServiceSelected = foodServiceSelected;
	}

	public List<FoodService> getFoodServices() {
		return foodServices;
	}

	public void setFoodServices(List<FoodService> foodServices) {
		this.foodServices = foodServices;
	}

}
