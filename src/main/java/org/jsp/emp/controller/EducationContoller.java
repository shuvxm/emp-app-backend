package org.jsp.emp.controller;

import org.jsp.emp.entity.Education;
import org.jsp.emp.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educations")
public class EducationContoller {
	
	@Autowired
	private EducationService educationService;
	
	@PostMapping
	public ResponseEntity<?> saveEducation(@RequestBody Education education){
		return educationService.saveEducation(education);
	}
	
	@GetMapping
	public ResponseEntity<?> findAllEducation(){
		return educationService.findAllEducations();
	}
	
	@GetMapping("/{eid}")
	public ResponseEntity<?> findEducationById(@PathVariable long eid){
		return educationService.findEducationById(eid);
	}
	
	@DeleteMapping("/{eid}")
	public ResponseEntity<?> deleteEducationById(@PathVariable long eid){
		return educationService.deleteEducationById(eid);
	}

}
