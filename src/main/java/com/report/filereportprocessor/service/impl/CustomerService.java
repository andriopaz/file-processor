
package com.report.filereportprocessor.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.report.filereportprocessor.model.Customer;
import com.report.filereportprocessor.service.IService;

@Service
@Transactional
public class CustomerService extends BaseService<Customer> implements IService<Customer> {
	
	@Autowired
	public CustomerService(JpaRepository<Customer, Long> repo) {
		super(repo);
	}
	
	
	@Override
	public boolean save(Customer customer) {
		Optional<Customer> result = findCustomerByCNPJ(customer.getCnpj());
		if (result.isPresent()) {
			customer.setId(result.get().getId());
		}
		
		return repo.save(customer) != null;
	}
	
	public Optional<Customer> findCustomerByCNPJ(String cnpj) {
		Customer customer = new Customer();
		customer.setCnpj(cnpj);
		Example<Customer> example = Example.of(customer);
		
		return repo.findOne(example);
	}

}
