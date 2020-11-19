package com.report.filereportprocessor.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.report.filereportprocessor.model.Salesman;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SalesmanRepositoryTest {

	@Autowired
	private SalesmanRepository salesmanRepository;

	@Test
	void save_and_find_it() {
		Salesman salesman = new Salesman("12345678901", "Andrio Fonseca", 999.0);
		salesmanRepository.save(salesman);

		Optional<Salesman> salesman2 = salesmanRepository.findById(1L);
		assertEquals("Andrio Fonseca", salesman2.get().getName());
	}

}
