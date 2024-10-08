package org.jsp.emp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.emp.dao.EducationDAO;
import org.jsp.emp.dao.EmployeeDao;
import org.jsp.emp.entity.Education;
import org.jsp.emp.entity.Employee;
import org.jsp.emp.exceptionclasses.InvalidEmployeeIdException;
import org.jsp.emp.exceptionclasses.NoEducationFoundException;
import org.jsp.emp.responsestructure.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EducationService {

	@Autowired
	private EducationDAO dao;

	@Autowired
	private EmployeeDao employeeDao;

	// add education
	public ResponseEntity<?> saveEducation(Education education) {
		// Fetch the full Employee object from the database using the ID
		Employee employee = employeeDao.findEmpById(education.getEmployee().getId()).orElse(null);

		// Set the fetched employee in the education entity
		education.setEmployee(employee);

		// Save the education to the database
		Education savedEducation = dao.saveEducation(education);

		// Build the response structure
		ResponseStructure<Education> response = ResponseStructure.<Education>builder()
				.status(HttpStatus.CREATED.value()).message("Education details saved successfully.")
				.body(savedEducation).build();

		// Return the response entity with the created status and response structure
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// fetch all education details
	public ResponseEntity<?> findAllEducations() {
		List<Education> educations = dao.findAllEducations();

		// If no education are found, throw an exception
		if (educations.isEmpty()) {
			throw new NoEducationFoundException("No Education found...");
		}

		// Build the response structure
		ResponseStructure<List<Education>> response = ResponseStructure.<List<Education>>builder()
				.status(HttpStatus.OK.value()).message("All education details found successfully").body(educations)
				.build();

		// Return the response entity with the created status and response structure
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// fetch education by eid
	public ResponseEntity<?> findEducationById(long eid) {
		Optional<Education> optional = dao.findEducationById(eid);

		Education education = optional.get();

		if (optional.isEmpty()) {
			throw new InvalidEmployeeIdException("Invalid Education ID: " + eid);
		}

		ResponseStructure<Education> response = ResponseStructure.<Education>builder()
				.status(HttpStatus.OK.value())
				.message("Education found succcessfully")
				.body(education).build();

		// Return the response entity with the created status and response structure
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// delete education by eid
	public ResponseEntity<?> deleteEducationById(long eid) {
		Optional<Education> optional = dao.findEducationById(eid);

		if (optional.isEmpty()) {
			throw new InvalidEmployeeIdException("Invalid Education ID: " + eid);
		}
		dao.deleteEducationById(eid);

		ResponseStructure<String> response = ResponseStructure.<String>builder()
				.status(HttpStatus.OK.value())
				.message("Education deleted successfully.")
//		        .body("deleted") // Optionally set to null since body is not needed here
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
