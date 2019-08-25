package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;
import com.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Override
	@Transactional
	public List<Employee> saveEmployee(Employee e) {
		 repository.save(e);
		return (List<Employee>) repository.findAll();
	}

	@Override
	@Transactional
	public void updateEmployee(Employee e) {
		repository.save(e);
	}

	@Override
	@Transactional
	public void deleteEmployee(Integer id) {
		// TODO Auto-generated method stub
		 repository.deleteById(id);
	}

	

	@Override
	@Transactional(readOnly=true)
	public List<Employee> getAllEmployees() {
		
		return (List<Employee>) repository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Employee getEmployeeById(Integer id) {
		
		return repository.findById(id).orElse(null);
		
	}

}
