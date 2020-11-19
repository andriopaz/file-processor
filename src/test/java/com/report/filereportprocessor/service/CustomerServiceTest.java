package com.report.filereportprocessor.service;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.report.filereportprocessor.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	void save_and_find_it() {
		Customer customer = new Customer(1L, "12345678/0001", "John", "IT");
		customerService.save(customer);
		
		assertTrue(customerService.findAllCustomers().stream().filter(s -> "John".equalsIgnoreCase(s.getName()))
				.findAny().isPresent());
	}
}
