package com.report.filereportprocessor.converter.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.report.filereportprocessor.converter.Converter;
import com.report.filereportprocessor.converter.IConverter;
import com.report.filereportprocessor.exception.CannotReadException;
import com.report.filereportprocessor.model.Salesman;

@Component
@Primary
public class SalesmanConverter extends Converter implements IConverter {
	
	@Value("${reader.salesman.columns}")
	private int columns;
	
	@Override
	public Optional<?> convert(String lineContent) throws CannotReadException {
		resultList = Arrays.stream(lineContent.split(separator))
				  .map(String::trim)
				  .toArray(String[]::new);
		
		if (resultList == null || resultList.length != columns) {
			throw new CannotReadException("File contains an invalid number of columns.");
		}

		
		Optional<Salesman> salesman = Optional.of(new Salesman(resultList[1], resultList[2], Double.valueOf(resultList[3])));
		return salesman;
	}
}
