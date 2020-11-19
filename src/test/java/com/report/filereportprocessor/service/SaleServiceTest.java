package com.report.filereportprocessor.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.report.filereportprocessor.model.Sale;
import com.report.filereportprocessor.model.Salesman;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleServiceTest {
	
	@Autowired
	private SaleService saleService;
	
	@Test
	void save_and_find_the_most_expensive_sale() {
		List<Sale> saleList = new ArrayList<>();
		
		saleList.add(new Sale(1L, new Salesman("12345678", "Name1", 120.0), 32.00));
		saleList.add(new Sale(2L, new Salesman("22345678", "Name2", 170.0), 2578.00));
		saleList.add(new Sale(3L, new Salesman("32345678", "Name3", 80.0), 7.00));
		saleList.add(new Sale(4L, new Salesman("42345678", "Name4", 220.0), 162.00));
		saleService.saveAll(saleList);
		
		Optional<Sale> mostExpensiveSale = saleService.getMostExpensiveSale();
		assertTrue(mostExpensiveSale.get().getPrice() == 2578.00);
	}
}
