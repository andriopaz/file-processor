package com.report.filereportprocessor.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.report.filereportprocessor.model.Salesman;
import com.report.filereportprocessor.repository.SalesmanRepository;

@Service
@Transactional
@Primary
public class SalesmanService implements IService<Salesman> {

	@Autowired
	private SalesmanRepository salesmanRepository;

	public List<Salesman> findAllSalesmans() {
		return (List<Salesman>) salesmanRepository.findAll();
	}

	@Override
	public boolean save(Salesman salesman) {
		Optional<Salesman> result = findSalesmanByCPF(salesman.getCpf());
		if (result.isPresent()) {
			salesman.setId(result.get().getId());
		}
		
		return salesmanRepository.save(salesman) != null;
	}

	public Optional<Salesman> findSalesmanByCPF(String cpf) {
		Salesman salesman = new Salesman();
		salesman.setCpf(cpf);
		Example<Salesman> example = Example.of(salesman);
		
		return salesmanRepository.findOne(example);
	}

	public void saveAll(List<Salesman> salesmanList) {
		salesmanList.forEach(salesman -> this.save(salesman));
	}

	public long countSalesman() {
		return salesmanRepository.count();
	}

	public Optional<Salesman> getWorstSalesman() {
		List<Salesman> salesmanList = salesmanRepository.findAll();
		
		Collections.sort(salesmanList,Comparator.comparing(Salesman::getNumberOfSales)
				.reversed()
				.thenComparing(Salesman::getTotalPriceOfSales)
				.reversed());

		return Optional.ofNullable(salesmanList.get(0));
	}
}
