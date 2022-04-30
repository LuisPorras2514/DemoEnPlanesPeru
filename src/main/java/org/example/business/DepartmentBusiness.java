package org.example.business;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.example.entities.Department;
import org.example.repository.DepartmentRepository;

@Named
public class DepartmentBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DepartmentRepository departmentRepository;
	
	@Transactional
	public List<Department> getAllDepartment() throws Exception {
		return departmentRepository.findall();
	}
}
