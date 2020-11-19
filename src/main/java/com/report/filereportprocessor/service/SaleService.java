package com.report.filereportprocessor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.report.filereportprocessor.model.Sale;
import com.report.filereportprocessor.model.Salesman;
import com.report.filereportprocessor.repository.SaleRepository;

@Service
@Transactional
public class SaleService implements IService<Sale> {
	
	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private SalesmanService salesmanService;
	
	public List<Sale> findAllSales() {
		return (List<Sale>) saleRepository.findAll();
	}

	@Override
	public boolean save(Sale sale) {
		
		Optional<Salesman> storedSalesMan = salesmanService.findAllSalesmans().stream()
				.filter(salesman -> salesman.getName().equalsIgnoreCase(sale.getSalesman().getName())).findAny();
		
		if (storedSalesMan.isPresent()) {
			sale.setSalesman(storedSalesMan.get());
		}
			
		return saleRepository.save(sale) != null;
	}

	public boolean saveAll(List<Sale> sales) {
		return saleRepository.saveAll(sales) != null;
	}
	
	public long countSale() {
		return saleRepository.count();
	}
	
	public Optional<Sale> getMostExpensiveSale() {
		List<Sale> sale = saleRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
		return Optional.ofNullable(sale.get(0));
	}
}
