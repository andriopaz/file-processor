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

import com.report.filereportprocessor.model.Sale;
import com.report.filereportprocessor.model.Salesman;

@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SaleRepositoryTest {
	@Autowired
	private SaleRepository saleRepository;

	@Test
	void saveAndFindIt() {
		Sale sale = new Sale(1L, new Salesman("1234556", "Joseph Owe", 37123.00), 32.00);
		saleRepository.save(sale);

		Optional<Sale> sale2 = saleRepository.findById(1L);
		assertEquals(32.00, sale2.get().getPrice());
	}
}
