package org.jsp.emp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.emp.entity.Employee;
import org.jsp.emp.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDao {
	
	@Autowired
	private EmployeeRepo repository;
	
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}
	
	public Employee updateEmployee(Employee employee) {
		return repository.save(employee);
	}
	public Optional<Employee> findEmpById(long id) {
		return repository.findById(id);
	}
	
	public List<Employee> findAllEmp(){
		return repository.findAll();
	}
	
	public List<Employee> findAllActiveEmp(){
		return repository.findAllActiveEmp();
	}
	
	
	
	public void deleteEmpById(long id) {
		repository.deleteById(id);
	}
	
	public Optional<Employee> findEmpByEmailAndPassword(String email, String password){
		return repository.findByEmailAndPassword(email, password);
	}
	
	public List<Employee> findEmpByName(String name){
		return repository.findByName(name);
	}


}
