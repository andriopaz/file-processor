package com.report.filereportprocessor.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.report.filereportprocessor.model.Salesman;
import com.report.filereportprocessor.service.IService;

@Service
@Transactional
@Primary
public class SalesmanService extends BaseService<Salesman> implements IService<Salesman> {
	
	@Autowired
	public SalesmanService(JpaRepository<Salesman, Long> repo) {
		super(repo);
	}
	
	
	@Override
	public boolean save(Salesman salesman) {
		Optional<Salesman> result = findSalesmanByCPF(salesman.getCpf());
		if (result.isPresent()) {
			salesman.setId(result.get().getId());
		}
		
		return repo.save(salesman) != null;
	}

	public Optional<Salesman> findSalesmanByCPF(String cpf) {
		Salesman salesman = new Salesman();
		salesman.setCpf(cpf);
		Example<Salesman> example = Example.of(salesman);
		
		return repo.findOne(example);
	}

	public Optional<Salesman> getWorstSalesman() {
		List<Salesman> salesmanList = repo.findAll();
		
		Collections.sort(salesmanList,Comparator.comparing(Salesman::getNumberOfSales)
				.reversed()
				.thenComparing(Salesman::getTotalPriceOfSales)
				.reversed());

		return Optional.ofNullable(salesmanList.get(0));
	}
}
