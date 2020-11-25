package com.report.filereportprocessor.process;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.report.filereportprocessor.converter.IConverter;
import com.report.filereportprocessor.enumeration.FileReportEnum;
import com.report.filereportprocessor.exception.CannotReadException;
import com.report.filereportprocessor.service.IService;

@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class ProcessFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessFactory.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private IConverter converter;
	
	@Autowired
	private IService service;
	
	public void process(String line) {
		try {
			String prefix = line.substring(0, 3);
			
			converter =  applicationContext.getBean(FileReportEnum.getConverterByKey(prefix));
			service = applicationContext.getBean(FileReportEnum.getServiceByKey(prefix));
			
			Optional<?> convertedObject = converter.convert(line);
			service.save(convertedObject.get());
			
		} catch (CannotReadException | RuntimeException | ReflectiveOperationException | IOException e) {
			LOGGER.error("Error when reading line.", e);
		}
	}
}
