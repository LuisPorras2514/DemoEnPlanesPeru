package org.example.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.example.business.AffiliateBusiness;
import org.example.business.FavoritePackageBusiness;
import org.example.business.UserBusiness;
import org.example.entities.Affiliate;
import org.example.entities.FavoritePackage;
import org.example.entities.User;
import org.example.util.Message;
import org.primefaces.event.SelectEvent;

@Named
@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserBusiness userBusiness;
	@Inject
	private AffiliateBusiness affiliateBusiness;
	@Inject
	private FavoritePackageBusiness favoritePackageBusiness;

	// Variable
	private User user;
	private User userSelected;
	
	private Affiliate afiliate;
	private Affiliate afiliateSelected;
	
	private List<User> users;
	private List<Affiliate> afiliates;
	
	private FavoritePackage favoritePackage;
	private FavoritePackage favoritePackageSelected;
	private  List<FavoritePackage> favoritePackages;

	private String email;
	private String password;
	private String typeUser;
	
	private String passwordConfirm;

	@PostConstruct
	public void init() {
		user = new User();
		userSelected = new User();
		afiliate = new Affiliate();
		afiliateSelected = new Affiliate();
		users = new ArrayList<>();
		afiliates = new ArrayList<>();

	}
	
	public void getAllFavoritePackage() {
		try {
			favoritePackages = favoritePackageBusiness.getAll();
		} catch (Exception e) {
			
		}
	}

	public String loginUser() {
		String view = "";
		try {
			users = userBusiness.login(email, password);

			if (users.isEmpty()) {
				Message.messageInfo("No se encuentra registrado este usuario");
				view = "/user/login";
			} else {
				user = users.get(0);
				getAllFavoritePackage();
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
	
	public String saveUser() {
		String view = "";
		try {
			if (user.getId() != null) {
				userBusiness.update(user);
				Message.messageInfo("Usuario actualizado exitosamente");
				this.resetForm();
				view = "/user/profile-user";
			} else {
				userBusiness.insert(user);
				Message.messageInfo("Usuario registrado exitosamente");
				this.resetForm();
				view = "/user/login-user";
			}

		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}
	
	public void seleccionarUsuario(SelectEvent e) {
		this.userSelected = (User) e.getObject();
	}
	
	public String saveAfiliate() {
		String view = "";
		try {
			if (afiliate.getId() != null) {
				affiliateBusiness.update(afiliate);
				Message.messageInfo("Empresa actualizado exitosamente");
				this.resetForm();
				view = "/user/profile-afiliate";
			} else {
				affiliateBusiness.insert(afiliate);
				Message.messageInfo("Empresa registrado exitosamente");
				this.resetForm();
				view = "/user/login-afiliate";
			}

		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}
	
	public String deleteFavorite() {
		String view = "";
		try {
			if (this.favoritePackageSelected != null) {
				this.favoritePackage = favoritePackageSelected;
				this.favoritePackageBusiness.delete(favoritePackage);
				getAllFavoritePackage();
				view = "/package/list";
			} else {
				Message.messageInfo("Debe seleccionar un servicio");
			}
		} catch (Exception e) {
			Message.messageError("Error ProductController:" + e.getMessage());
		}
		return view;
	}
	
	public void resetForm() {
		this.user = new User();
		this.afiliate = new Affiliate();
	}
	
	public String editUser() {
		user = users.get(0);
		return  "/user/edit-user";
	}
	
	public String editAfiliate() {
		afiliate = afiliates.get(0);
		return  "/user/edit-afiliate";
	}
	
	public String newRegisterUser() {
		resetForm();
		return "/user/register-user";
	}
	
	public String newRegisterAfiliate() {
		resetForm();
		return "/user/register-afiliate";
	}
	
	public String newLoginUser() {
		resetForm();
		return "/user/login-user";
	}
	
	public String newLoginAfiliate() {
		resetForm();
		return "/user/login-afiliate";
	}
	
	public String favoritePackage() {
		user = users.get(0);
		return "/user/favorite-package";
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

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public FavoritePackage getFavoritePackage() {
		return favoritePackage;
	}

	public void setFavoritePackage(FavoritePackage favoritePackage) {
		this.favoritePackage = favoritePackage;
	}

	public FavoritePackage getFavoritePackageSelected() {
		return favoritePackageSelected;
	}

	public void setFavoritePackageSelected(FavoritePackage favoritePackageSelected) {
		this.favoritePackageSelected = favoritePackageSelected;
	}

	public List<FavoritePackage> getFavoritePackages() {
		return favoritePackages;
	}

	public void setFavoritePackages(List<FavoritePackage> favoritePackages) {
		this.favoritePackages = favoritePackages;
	}

}
