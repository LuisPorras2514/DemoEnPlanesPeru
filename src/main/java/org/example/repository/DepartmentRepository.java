package org.example.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.example.entities.Department;

@Named
public class DepartmentRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "demoWeb")
	private EntityManager em;
	
	public List<Department> findall() throws Exception {
		List<Department> departments = new ArrayList<>();

		TypedQuery<Department> query = em.createQuery("FROM Department p", Department.class);
		departments = query.getResultList();
		return departments;
	}

}
