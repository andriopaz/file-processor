package com.report.filereportprocessor.converter.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.report.filereportprocessor.converter.Converter;
import com.report.filereportprocessor.converter.IConverter;
import com.report.filereportprocessor.exception.CannotReadException;
import com.report.filereportprocessor.model.Customer;

@Component
public class CustomerConverter extends Converter implements IConverter {

	@Value("${reader.customer.columns}")
	private int columns;
	
	@Override
	public Optional<?> convert(String lineContent) throws CannotReadException {
		resultList = Arrays.stream(lineContent.split(separator))
				  .map(String::trim)
				  .toArray(String[]::new);
		
		if (resultList == null || resultList.length != columns) {
			throw new CannotReadException("File contains an invalid number of columns.");
		}
		
		Optional<Customer> customer = Optional.of(new Customer(resultList[1], resultList[2], resultList[3]));
		return customer;
	
	}

}
