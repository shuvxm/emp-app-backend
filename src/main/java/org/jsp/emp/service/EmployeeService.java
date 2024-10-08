package org.jsp.emp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsp.emp.dao.EmployeeDao;
import org.jsp.emp.entity.Employee;
import org.jsp.emp.exceptionclasses.InvalidCredientialException;
import org.jsp.emp.exceptionclasses.InvalidEmployeeIdException;
import org.jsp.emp.exceptionclasses.InvalidFoundEmployeeIdException;
import org.jsp.emp.exceptionclasses.NoActiveEmployeeFoundException;
import org.jsp.emp.exceptionclasses.NoSuchNameOfEmpException;
import org.jsp.emp.responsestructure.ResponseStructure;
import org.jsp.emp.util.EmployeeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao dao;
	
	// save
//	public ResponseEntity<?> saveEmployee(Employee employee){
//		employee.setStatus(EmployeeStatus.IN_ACTIVE);
//		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseStructure.builder().status(HttpStatus.CREATED.value()).message("Emplyee details Saved Succesfully....") .body(dao.saveEmployee(employee)).build());
//	}
	public ResponseEntity<?> saveEmployee(Employee employee) {
	    // Set the default status for the employee
	    employee.setStatus(EmployeeStatus.IN_ACTIVE);

	    // Save the employee to the database
	    Employee savedEmployee = dao.saveEmployee(employee);

	    // Build the response structure
	    ResponseStructure<Employee> response = ResponseStructure.<Employee>builder()
	            .status(HttpStatus.CREATED.value())
	            .message("Employee details saved successfully.")
	            .body(savedEmployee)
	            .build();

	    // Return the response entity with the created status and response structure
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// update
//	public ResponseEntity<?> updateEmployee(Employee employee){
//		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Employee updated successfully").body(dao.updateEmployee(employee)).build());
//	}
	
	public ResponseEntity<?> updateEmployee(int id, Employee employee) {
	    // Find the existing employee by id
	    Optional<Employee> existingEmployee = dao.findEmpById(id);
	    
	    // If employee with given id doesn't exist, throw an exception or return a 404 response
	    if (existingEmployee.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with the given ID.");
	    }

	    // Set the id from the path to ensure we are updating the correct employee
	    employee.setId(id);

	    // Update the employee in the database
	    Employee updatedEmployee = dao.updateEmployee(employee);

	    // Build a generic response structure
	    ResponseStructure<Employee> response = ResponseStructure.<Employee>builder()
	            .status(HttpStatus.OK.value())
	            .message("Employee updated successfully.")
	            .body(updatedEmployee)
	            .build();

	    // Return the generic ResponseEntity
	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	// fetch employee by id
	public ResponseEntity<?> findEmpById(long id) {
	    Optional<Employee> optional = dao.findEmpById(id);
	    
	    if (optional.isEmpty()) {
	        // If employee is not found, throw an exception or return an error response
	        throw new InvalidEmployeeIdException("Invalid Employee ID: " + id);
	    }

	    // Get the found employee
	    Employee employee = optional.get();

	    // Build a response structure with the found employee details
	    ResponseStructure<Employee> response = ResponseStructure.<Employee>builder()
	            .status(HttpStatus.OK.value())
	            .message("Employee found successfully.")
	            .body(employee)  // Include the found employee in the response
	            .build();

	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	
	// fetch all employee
	public ResponseEntity<?> findAllEmp() {
	    // Fetch all active employees
	    List<Employee> employees = dao.findAllActiveEmp();
	    
	    // If no active employees are found, throw an exception
	    if (employees.isEmpty()) {
	        throw new NoActiveEmployeeFoundException("No Active Employee in the Table...");
	    }

	    // Build the response structure with active employees list
	    ResponseStructure<List<Employee>> response = ResponseStructure.<List<Employee>>builder()
	            .status(HttpStatus.OK.value())
	            .message("All employees found successfully.")
	            .body(employees)
	            .build();

	    // Return response entity with status and body
	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// to delete employee by id
//	public ResponseEntity<?> deleteEmpById(long id){
//		
//		Optional<Employee> optional = dao.findEmpById(id);
//		ResponseStructure<String> structure = new ResponseStructure<>();
//		if(optional.isEmpty()) 
//			throw new InvalidFoundEmployeeIdException("Invalid Employee ID..Unable to delete..");
//		dao.deleteEmpById(id);
//		
//		return ResponseEntity.status(HttpStatus.OK.value()).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Employee deleted successfully").build());
//	}
	public ResponseEntity<ResponseStructure<String>> deleteEmpById(long id) {
	    Optional<Employee> optional = dao.findEmpById(id);
	    
	    if (optional.isEmpty()) {
	        // If no employee is found with the given ID, throw an exception
	        throw new InvalidFoundEmployeeIdException("Invalid Employee ID. Unable to delete.");
	    }

	    // Proceed to delete the employee
	    dao.deleteEmpById(id);

	    // Build a response structure indicating successful deletion
	    ResponseStructure<String> response = ResponseStructure.<String>builder()
	            .status(HttpStatus.OK.value())
	            .message("Employee deleted successfully.")
//	            .body("deleted") // Optionally set to null since body is not needed here
	            .build();

	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	
	// find employee by email and password
	public ResponseEntity<?> findEmpByEmailAndPassword(String email, String password) {
	    Optional<Employee> optional = dao.findEmpByEmailAndPassword(email, password);
	    
	    if (optional.isEmpty()) {
	        // If no employee found, throw an exception
	        throw new InvalidCredientialException("Invalid Email or Password.");
	    }

	    // Get the authenticated employee
	    Employee employee = optional.get();

	    // Build a response structure including the authenticated employee details
	    ResponseStructure<Employee> response = ResponseStructure.<Employee>builder()
	            .status(HttpStatus.OK.value())
	            .message("Verification successful.")
	            .body(employee) // Include the authenticated employee in the response
	            .build();

	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	
	// fetch by name
	public ResponseEntity<?> findEmpByName(String name) {
	    List<Employee> employees = dao.findEmpByName(name);
	    
	    if (employees.isEmpty()) {
	        // If no employees found, throw an exception
	        throw new NoSuchNameOfEmpException("No employees found with the name: " + name);
	    }

	    // Build a response structure including the list of employees found
	    ResponseStructure<List<Employee>> response = ResponseStructure.<List<Employee>>builder()
	            .status(HttpStatus.OK.value())
	            .message("Employees found successfully.")
	            .body(employees) // Include the list of found employees
	            .build();

	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}


	public ResponseEntity<?> setEmployeeStatusToActive(int id) {
	    // Fetch employee by ID
	    Optional<Employee> op = dao.findEmpById(id);

	    // Check if employee exists
	    if (op.isEmpty()) {
	        throw new InvalidEmployeeIdException("Invalid Employee ID. Unable to update status.");
	    }

	    // Get employee and update status to ACTIVE
	    Employee employee = op.get();
	    employee.setStatus(EmployeeStatus.ACTIVE);  // Explicitly setting the status to ACTIVE

	    // Save the updated employee
	    Employee updatedEmployee = dao.updateEmployee(employee);

	    // Build response structure with updated employee
	    ResponseStructure<Employee> response = ResponseStructure.<Employee>builder()
	            .status(HttpStatus.OK.value())
	            .message("Employee status has changed to ACTIVE.")
	            .body(updatedEmployee)  // Include the updated employee in the response
	            .build();

	    // Return response entity with status and updated employee
	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}


	public ResponseEntity<?> setEmployeeStatusToInActive(int id) {
		Optional<Employee> op=dao.findEmpById(id);
		if(op.isEmpty())
			throw new InvalidEmployeeIdException("Invalid Employee ID..Unable to delete..");
		Employee e=op.get();
		e.setStatus(EmployeeStatus.IN_ACTIVE);
		e=dao.updateEmployee(e);
		return ResponseEntity.status(HttpStatus.OK.value()).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Employee Status has Changed to InActive....").build());
	}
	
	
	
	

}
