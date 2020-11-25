package com.report.filereportprocessor.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.report.filereportprocessor.model.Sale;
import com.report.filereportprocessor.model.Salesman;
import com.report.filereportprocessor.service.IService;

@Service
@Transactional
public class SaleService extends BaseService<Sale> implements IService<Sale> {
	
	public SaleService(JpaRepository<Sale, Long> repo) {
		super(repo);
	}
	
	@Autowired
	private SalesmanService salesmanService;
	
	@Override
	public boolean save(Sale sale) {
		
		Optional<Salesman> storedSalesMan = salesmanService.findAll().stream()
				.filter(salesman -> salesman.getName().equalsIgnoreCase(sale.getSalesman().getName())).findAny();
		
		if (storedSalesMan.isPresent()) {
			sale.setSalesman(storedSalesMan.get());
		}
			
		return repo.save(sale) != null;
	}
	
	public Optional<Sale> getMostExpensiveSale() {
		List<Sale> sale = repo.findAll(Sort.by(Sort.Direction.DESC, "price"));
		return Optional.ofNullable(sale.get(0));
	}
}
