package org.jsp.emp.repo;

import java.util.List;

import org.jsp.emp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepo extends JpaRepository<Address, Long>{

	@Query("select a from Address a where a.employee.id=?1")
	List<Address> findAllAddressesByEmployeeId(int eid);

}
