package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.User;

@Named
public class UserRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;
	
	public Long insert(User user) throws Exception {
		em.persist(user);
		return user.getId();
	}

	public Long update(User user) throws Exception {
		em.merge(user);
		return user.getId();
	}

	public List<User> findAll() throws Exception {
		List<User> users = new ArrayList<>();
		TypedQuery<User> query = em.createQuery("FROM User", User.class);
		users = query.getResultList();
		return users;
	}
	
	public List<User> login(String email,String password)  throws Exception{
		List<User> users=new ArrayList<>();
		TypedQuery<User> query=em.createQuery("FROM User WHERE mail=?1 AND password=?2", User.class);
		query.setParameter(1, email);
		query.setParameter(2, password);
		users=query.getResultList();
		return users;
	}

}
