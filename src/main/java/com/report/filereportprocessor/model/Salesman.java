package com.report.filereportprocessor.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class Salesman {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true)
	private String cpf;
	private String name;
	private Double salary;
	@OneToMany(mappedBy = "salesman", cascade = CascadeType.ALL)
	private List<Sale> sales = new ArrayList<Sale>();
	
	public Salesman(String cpf, String name, Double salary) {
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}
	
	public long getNumberOfSales() {
		return sales.size();
	}
	
	public Double getTotalPriceOfSales() {
		return sales.stream().mapToDouble(Sale::getPrice).sum();
	}
}
