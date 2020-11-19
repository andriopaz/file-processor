package com.report.filereportprocessor.converter;

import java.util.Optional;

import com.report.filereportprocessor.exception.CannotReadException;

public interface IConverter {
	public Optional<?> convert(String content) throws CannotReadException; 
}
