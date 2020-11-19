
package com.report.filereportprocessor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.report.filereportprocessor.model.Customer;
import com.report.filereportprocessor.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService implements IService<Customer> {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> findAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	public List<Customer> getAllCustomers() {
		return (List<Customer>) customerRepository.findAll();
	}
	
	@Override
	public boolean save(Customer customer) {
		Optional<Customer> result = findCustomerByCNPJ(customer.getCnpj());
		if (result.isPresent()) {
			customer.setId(result.get().getId());
		}
		
		return customerRepository.save(customer) != null;
	}
	
	public void saveAll(List<Customer> customerList) {
		customerList.forEach(customer -> this.save(customer));
	}
	
	public Optional<Customer> findCustomerByCNPJ(String cnpj) {
		Customer customer = new Customer();
		customer.setCnpj(cnpj);
		Example<Customer> example = Example.of(customer);
		
		return customerRepository.findOne(example);
	}
	
	@Transactional
	public long countCustomers() {
		return customerRepository.count();
	}
}
