package com.report.filereportprocessor.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class Sale {

	@Id
	protected Long id;
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "salesman_id", referencedColumnName = "id")
	private Salesman salesman;
	private Double price;
}
