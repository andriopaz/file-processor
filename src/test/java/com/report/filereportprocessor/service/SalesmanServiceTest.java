package com.report.filereportprocessor.service;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.report.filereportprocessor.model.Salesman;
import com.report.filereportprocessor.service.impl.SalesmanService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SalesmanServiceTest {
	
	@Autowired
	private SalesmanService salesmanService;
	
	@Test
	void save_and_find_it() {
		Salesman salesman = new Salesman("63123", "Joseph", 949.0);
		salesmanService.save(salesman);
		
		assertTrue(salesmanService.findAll().stream().filter(s -> "Joseph".equalsIgnoreCase(s.getName()))
				.findAny().isPresent());
	}
}
