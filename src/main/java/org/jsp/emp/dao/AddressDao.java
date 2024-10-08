package org.jsp.emp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.emp.entity.Address;
import org.jsp.emp.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressDao {
	
	@Autowired
	private AddressRepo addressRepo;
	
	// save Address
	public Address saveAddress(Address address) {
		return addressRepo.save(address);
	}
	
	// update Address
	public Address updateAddress(Address address) {
		return addressRepo.save(address);
	}
	
	// fetch address
	public List<Address> findAllAddress(){
		return addressRepo.findAll();
	}
	
	// fetch address by id
	public Optional<Address> findAddressById(long id) {
		return addressRepo.findById(id);
	}
	
	// delete education by id
	public void deleteAddressById(long id) {
		 addressRepo.deleteById(id);
	}

}
