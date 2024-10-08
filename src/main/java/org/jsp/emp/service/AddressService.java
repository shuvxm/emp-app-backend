package org.jsp.emp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.emp.dao.AddressDao;
import org.jsp.emp.dao.EmployeeDao;
import org.jsp.emp.entity.Address;
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
public class AddressService {
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	// add address
	public ResponseEntity<?> saveAddress(Address address){
		
		// Fetch the full Employee object from the database using the ID
	    Employee employee = employeeDao.findEmpById(address.getEmployee().getId()).orElse(null);
	    
	    // Set the fetched employee in the education entity
	    address.setEmployee(employee);
	    
		Address saveaddress= addressDao.saveAddress(address);
		
		ResponseStructure<Address> response = ResponseStructure.<Address>builder()
				.status(HttpStatus.CREATED.value())
				.message("Address added successfully")
				.body(saveaddress)
				.build();
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	// fetch address
	public ResponseEntity<?> findAllAddress()
	{
		List<Address> addresses = addressDao.findAllAddress();
		if(addresses.isEmpty()) {
			throw new NoEducationFoundException("no address found");
		}
		ResponseStructure<List<Address>> response = ResponseStructure.<List<Address>>builder()
				.status(HttpStatus.OK.value())
				.message(" All address found successfully")
				.body(addresses)
				.build();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// fetch address by aid
	public ResponseEntity<?> findAddressById(long aid) {
		Optional<Address> optional = addressDao.findAddressById(aid);

		Address address = optional.get();

		if (optional.isEmpty()) {
			throw new InvalidEmployeeIdException("Invalid Address ID: " + aid);
		}

		ResponseStructure<Address> response = ResponseStructure.<Address>builder()
				.status(HttpStatus.OK.value())
				.message("Address found succcessfully")
				.body(address).build();

		// Return the response entity with the created status and response structure
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// delete address by eid
	public ResponseEntity<?> deleteAddressById(long aid) {
		Optional<Address> optional = addressDao.findAddressById(aid);

		if (optional.isEmpty()) {
			throw new InvalidEmployeeIdException("Invalid Address ID: " + aid);
		}
		addressDao.deleteAddressById(aid);

		ResponseStructure<String> response = ResponseStructure.<String>builder()
				.status(HttpStatus.OK.value())
				.message("Address deleted successfully.")
//		        .body("deleted") // Optionally set to null since body is not needed here
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
