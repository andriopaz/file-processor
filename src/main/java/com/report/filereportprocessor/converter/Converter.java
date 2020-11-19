package com.report.filereportprocessor.converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public abstract class Converter {
	
	protected String[] resultList;

	@Value("ç")
	protected String separator;
	
}
