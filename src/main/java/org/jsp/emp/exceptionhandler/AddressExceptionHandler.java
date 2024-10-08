package org.jsp.emp.exceptionhandler;

import org.jsp.emp.exceptionclasses.NoAddressesfoundException;
import org.jsp.emp.responsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AddressExceptionHandler {

	@ExceptionHandler(NoAddressesfoundException.class)
	public ResponseEntity<?> noAddressesfoundExceptionHandler(NoAddressesfoundException e) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(
						ResponseStructure
						.builder()
						.status(HttpStatus.NOT_FOUND.value())
						.message("No Address Found in the Database table")
						.body(e.getMessage())
						.build());
	}

}
