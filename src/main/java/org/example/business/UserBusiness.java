package org.example.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.User;
import org.example.repository.UserRepository;

@Named
public class UserBusiness implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserRepository userRepository;
	
	@Transactional
	public Long insert(User user) throws Exception {
		return userRepository.insert(user);
	}
	
	@Transactional
	public Long update(User user) throws Exception {
		return userRepository.update(user);
	}
	
	@Transactional
	public List<User> listarAfiliado() throws Exception {
		return userRepository.findAll();
	}
	
	@Transactional
	public List<User> login(String email, String password) throws Exception {
		return userRepository.login(email, password);
	}
}
