package org.example.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.example.business.AffiliateBusiness;
import org.example.business.UserBusiness;
import org.example.entities.Affiliate;
import org.example.entities.User;
import org.example.util.Message;

@Named
@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserBusiness userBusiness;
	@Inject
	private AffiliateBusiness affiliateBusiness;

	// Variable
	private User user;
	private User userSelected;
	private Affiliate afiliate;
	private Affiliate afiliateSelected;
	private List<User> users;
	private List<Affiliate> afiliates;

	private String email;
	private String password;
	private String typeUser;

	@PostConstruct
	public void init() {
		user = new User();
		userSelected = new User();
		afiliate = new Affiliate();
		afiliateSelected = new Affiliate();
		users = new ArrayList<>();
		afiliates = new ArrayList<>();

	}

	public String loginUser() {
		String view = "";
		try {
			users = userBusiness.login(email, password);

			if (users.isEmpty()) {
				Message.messageInfo("No se encuentra registrado este usuario");
				view = "/user/login";
			} else {
				view = "/user/profile-user";
			}

		} catch (Exception e) {
		}
		return view;
	}
	
	public String loginAfiliate() {
		String view = "";
		try {
			afiliates = affiliateBusiness.login(email, password);
			if (afiliates.isEmpty()) {
				Message.messageInfo("No se encuentra registrada esta empresa");
				view = "/user/login";
			} else {
				view = "/user/profile-afiliate";
			}

		} catch (Exception e) {
		}
		return view;
	}
	
	public String newLoginUser() {
		return "/user/login-user";
	}
	
	public String newLoginAfiliate() {
		return "/user/login-afiliate";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(User userSelected) {
		this.userSelected = userSelected;
	}

	public Affiliate getAfiliate() {
		return afiliate;
	}

	public void setAfiliate(Affiliate afiliate) {
		this.afiliate = afiliate;
	}

	public Affiliate getAfiliateSelected() {
		return afiliateSelected;
	}

	public void setAfiliateSelected(Affiliate afiliateSelected) {
		this.afiliateSelected = afiliateSelected;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Affiliate> getAfiliates() {
		return afiliates;
	}

	public void setAfiliates(List<Affiliate> afiliates) {
		this.afiliates = afiliates;
	}

	public String getTyperUser() {
		return typeUser;
	}

	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
