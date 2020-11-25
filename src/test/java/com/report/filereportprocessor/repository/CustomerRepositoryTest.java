package com.report.filereportprocessor.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.report.filereportprocessor.model.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerRepositoryTest {
	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void saveAndFindIt() {
		Customer customer = new Customer(1L, "12345678/0001", "Andrio Fonseca", "IT");
		customerRepository.save(customer);

		Optional<Customer> customer2 = customerRepository.findById(1L);
		assertEquals("Andrio Fonseca", customer2.get().getName());
	}
}
