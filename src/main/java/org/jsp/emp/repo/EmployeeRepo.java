package org.jsp.emp.repo;

import java.util.List;
import java.util.Optional;

import org.jsp.emp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{

	Optional<Employee> findByEmailAndPassword(String email, String password);

	List<Employee> findByName(String name);
     
//	@Query("select e from Employee e where e.status='ACTIVE'")
	@Query("select e from Employee e")
	List<Employee> findAllActiveEmp();

	
	

}
