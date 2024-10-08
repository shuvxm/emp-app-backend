package org.jsp.emp.exceptionhandler;

import java.sql.SQLIntegrityConstraintViolationException;

//import org.apache.catalina.loader.ResourceEntry;
import org.jsp.emp.exceptionclasses.InvalidEmployeeIdException;
import org.jsp.emp.exceptionclasses.InvalidFoundEmployeeIdException;
import org.jsp.emp.exceptionclasses.NoActiveEmployeeFoundException;
import org.jsp.emp.exceptionclasses.NoEducationFoundException;
import org.jsp.emp.exceptionclasses.InvalidCredientialException;
import org.jsp.emp.exceptionclasses.NoSuchNameOfEmpException;
import org.jsp.emp.responsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ExceptionHandler 
{
	@org.springframework.web.bind.annotation.ExceptionHandler(NoActiveEmployeeFoundException.class)
   public ResponseEntity<?> noActiveEmployeeFoundException(NoActiveEmployeeFoundException e)
   {
	   return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(ResponseStructure.builder().status(HttpStatus.NOT_FOUND.value()).message("No active emp ...").build());
   }
   
   @org.springframework.web.bind.annotation.ExceptionHandler(InvalidEmployeeIdException.class)
   public ResponseEntity<?> inavldEmployeeidIdException(InvalidEmployeeIdException e)
   {
	   return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(ResponseStructure.builder().status(HttpStatus.NOT_FOUND.value()).message("No active emp ...").build());
   }
   
   @org.springframework.web.bind.annotation.ExceptionHandler(InvalidFoundEmployeeIdException.class)
   public ResponseEntity<?> noFoundEmployeeIdException(InvalidFoundEmployeeIdException e)
   {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(ResponseStructure.builder().status(HttpStatus.NOT_FOUND.value()).message("No emp Found....").build());
   }
   
   @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchNameOfEmpException.class)
	public ResponseEntity<?> noSuchNameOfEmpException(NoSuchNameOfEmpException e)
   {
	   return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(ResponseStructure.builder().status(HttpStatus.NOT_FOUND.value()).message("No such emp  found").build());
//		ResponseStructure<String> structure = new ResponseStructure<>();
//		structure.setStatus(HttpStatus.NOT_FOUND.value());
//		structure.setMessage("No such name of Employees found");
//		structure.setBody(e.getMessage());
//		return structure;
	}
   
   @org.springframework.web.bind.annotation.ExceptionHandler(InvalidCredientialException.class)
   public ResponseEntity<String> handleInvalidCredentialException(InvalidCredientialException ex) {
       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
   }
   @org.springframework.web.bind.annotation.ExceptionHandler(SQLIntegrityConstraintViolationException.class)
   public ResponseEntity<ResponseStructure<String>> sQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
	   ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setMessage("Invalid email or phone");
		structure.setBody(e.getMessage());
		return new ResponseEntity(structure, HttpStatus.BAD_REQUEST);
   }
   @org.springframework.web.bind.annotation.ExceptionHandler(NoEducationFoundException.class)
   public ResponseEntity<?> noEducationFoundException(NoEducationFoundException e)
   {
	   return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(ResponseStructure.builder().status(HttpStatus.NOT_FOUND.value()).message("No Education found").build());
   }
   
   
}
