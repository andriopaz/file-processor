package com.report.filereportprocessor.converter;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.report.filereportprocessor.exception.CannotReadException;

@Component
public abstract class Converter {
	
	protected String[] resultList;
	
	@Value("${reader.columns}")
	private int columns;

	@Value("ç")
	protected String separator;
	
	public Optional<?> convert(String lineContent) throws CannotReadException {
		resultList = Arrays.stream(lineContent.split(separator))
				  .map(String::trim)
				  .toArray(String[]::new);
		
		if (resultList == null || resultList.length != columns) {
			throw new CannotReadException("File contains an invalid number of columns.");
		}
		
		return Optional.empty();
	}
	
}
