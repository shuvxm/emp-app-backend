package org.jsp.emp.controller;

import org.jsp.emp.entity.Employee;
import org.jsp.emp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
@CrossOrigin("*")
public class EmployeeController {
	@Autowired
	private EmployeeService service;

	@PostMapping
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		return service.saveEmployee(employee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		return service.updateEmployee(id, employee); // Pass the id and employee to the service layer
	}

//	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping
	public ResponseEntity<?> findAllEmployees() {
		return service.findAllEmp();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findEmpById(@PathVariable long id) {
		return service.findEmpById(id);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<?> findEmpByName(@PathVariable String name) {
		return service.findEmpByName(name);
	}

	@GetMapping("/{email}/{password}")
	public ResponseEntity<?> findEmpByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
		return service.findEmpByEmailAndPassword(email, password);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmpById(@PathVariable long id) {
		return service.deleteEmpById(id);
	}

	@PatchMapping("/active/{id}")
	public ResponseEntity<?> setEmployeeStatusToActive(@PathVariable int id) {
		return service.setEmployeeStatusToActive(id);
	}

	@PatchMapping("/inactive/{id}")
	public ResponseEntity<?> setEmployeeStatusToInActive(@PathVariable int id) {
		return service.setEmployeeStatusToInActive(id);

	}

}
