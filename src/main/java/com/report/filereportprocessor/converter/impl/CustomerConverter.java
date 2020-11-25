package com.report.filereportprocessor.converter.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.report.filereportprocessor.converter.Converter;
import com.report.filereportprocessor.converter.IConverter;
import com.report.filereportprocessor.exception.CannotReadException;
import com.report.filereportprocessor.model.Customer;

@Component
public class CustomerConverter extends Converter implements IConverter {
	
	@Override
	public Optional<?> convert(String lineContent) throws CannotReadException {
		super.convert(lineContent);
		
		return Optional.of(new Customer(resultList[1], resultList[2], resultList[3]));
	}
}
