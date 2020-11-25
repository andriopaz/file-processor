package com.report.filereportprocessor.converter.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.report.filereportprocessor.converter.Converter;
import com.report.filereportprocessor.converter.IConverter;
import com.report.filereportprocessor.exception.CannotReadException;
import com.report.filereportprocessor.model.Sale;
import com.report.filereportprocessor.model.Salesman;

import lombok.Getter;
import lombok.Setter;

@Component
public class SaleConverter extends Converter implements IConverter {

	@Getter @Setter
	private Double salePrice = 0.0;

	@Override
	public Optional<?> convert(String lineContent) throws CannotReadException {
		super.convert(lineContent);
		sumSalePrices();
		
		return Optional.of(new Sale(Long.valueOf(resultList[1]), new Salesman(null, resultList[3], null), getSalePrice()));
	}

	private void sumSalePrices() {
		String resultItemList[] = Arrays.stream(resultList[2].replace("[", "").replace("]", "").split(","))
				  .map(String::trim)
				  .toArray(String[]::new);
		
		Arrays.stream(resultItemList).forEach(i -> {
			String item[] = i.split("-");
			setSalePrice(getSalePrice() + Double.valueOf(item[2])); 
		});
	}	
}
