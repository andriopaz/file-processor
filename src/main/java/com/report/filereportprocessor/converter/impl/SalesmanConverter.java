package com.report.filereportprocessor.converter.impl;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.report.filereportprocessor.converter.Converter;
import com.report.filereportprocessor.converter.IConverter;
import com.report.filereportprocessor.exception.CannotReadException;
import com.report.filereportprocessor.model.Salesman;

@Component
@Primary
public class SalesmanConverter extends Converter implements IConverter {
	
	@Override
	public Optional<?> convert(String lineContent) throws CannotReadException {
		super.convert(lineContent);
		
		return Optional.of(new Salesman(resultList[1], resultList[2], Double.valueOf(resultList[3])));
	}
}
