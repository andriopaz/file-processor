package com.report.filereportprocessor.converter.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${reader.sale.columns}")
	private int columns;

	@Getter @Setter
	private Double salePrice = 0.0;

	@Override
	public Optional<?> convert(String lineContent) throws CannotReadException {
		resultList = Arrays.stream(lineContent.split(separator))
				  .map(String::trim)
				  .toArray(String[]::new);
		
		if (resultList == null || resultList.length != columns) {
			throw new CannotReadException("File contains an invalid number of columns.");
		}
		
		String resultItemList[] = Arrays.stream(resultList[2].replace("[", "").replace("]", "").split(","))
				  .map(String::trim)
				  .toArray(String[]::new);
		
		
		Arrays.stream(resultItemList).forEach(i -> {
			String item[] = i.split("-");
			setSalePrice(getSalePrice() + Double.valueOf(item[2])); 
		});
		
		Optional<Sale> sale = Optional.of(new Sale(Long.valueOf(resultList[1]), new Salesman(null, resultList[3], null), getSalePrice()));
		return sale;
		
	}
	
}
