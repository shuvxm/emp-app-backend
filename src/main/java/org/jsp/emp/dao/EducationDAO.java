package org.jsp.emp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.emp.entity.Education;
import org.jsp.emp.repo.EducationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EducationDAO {
	@Autowired
	private EducationRepo educationRepo;
	
	// save education
	public Education saveEducation(Education education) {
		return educationRepo.save(education);
	}
	// update education
	public Education updateEducation(Education education) {
		return educationRepo.save(education);
	}
	
	// fetch education
	public List<Education> findAllEducations(){
		return educationRepo.findAll();
	}
	
	// fetch education by ID
	public Optional<Education> findEducationById(long eid) {
		return educationRepo.findById(eid);
	}
	
	// delete education by id
	public void deleteEducationById(long eid) {
		educationRepo.deleteById(eid);
	}
	
	
	
	

}
