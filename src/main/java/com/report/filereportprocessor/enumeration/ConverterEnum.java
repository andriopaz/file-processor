package com.report.filereportprocessor.enumeration;

import java.io.IOException;
import java.util.Arrays;

import com.report.filereportprocessor.converter.IConverter;
import com.report.filereportprocessor.converter.impl.CustomerConverter;
import com.report.filereportprocessor.converter.impl.SaleConverter;
import com.report.filereportprocessor.converter.impl.SalesmanConverter;

public enum ConverterEnum {

	SALESMAN("001", SalesmanConverter.class), 
	CUSTOMER("002", CustomerConverter.class),
	SALE("003", SaleConverter.class);

	private String providerKey;
	private Class<? extends IConverter> converter;

	private <T> ConverterEnum(String providerKey,
			Class<? extends IConverter> converter) {

		this.providerKey = providerKey;
		this.converter = converter;
	}

	public String getProviderKey() {
		return providerKey;
	}

	public Class<? extends IConverter> getConverter() {
		return converter;
	}

	public static Class<? extends IConverter> getConverterByKey(
			String providerKey) throws IOException, InstantiationException, IllegalAccessException {
		return Arrays.stream(ConverterEnum.values())
				.filter(value -> value.getProviderKey().equals(providerKey)).findFirst()
				.orElseThrow(() -> new IOException("No provider found for the given key")).getConverter();
	}
}