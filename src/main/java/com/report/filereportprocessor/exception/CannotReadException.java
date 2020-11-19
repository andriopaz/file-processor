package com.report.filereportprocessor.exception;

public class CannotReadException extends Exception {

	private static final long serialVersionUID = 1L;

	public CannotReadException(String errorMessage) {
		super(errorMessage);
	}
}
