package com.report.filereportprocessor.enumeration;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import com.report.filereportprocessor.converter.IConverter;
import com.report.filereportprocessor.converter.impl.CustomerConverter;
import com.report.filereportprocessor.converter.impl.SaleConverter;
import com.report.filereportprocessor.converter.impl.SalesmanConverter;
import com.report.filereportprocessor.service.IService;
import com.report.filereportprocessor.service.impl.CustomerService;
import com.report.filereportprocessor.service.impl.SaleService;
import com.report.filereportprocessor.service.impl.SalesmanService;

@SuppressWarnings("rawtypes")
public enum FileReportEnum {

	SALESMAN("001", SalesmanConverter.class, SalesmanService.class),
	CUSTOMER("002", CustomerConverter.class, CustomerService.class),
	SALE("003", SaleConverter.class, SaleService.class);

	private String providerKey;
	private Class<? extends IConverter> converter;
	private Class<? extends IService> service;

	private static final String NO_CONVERTER_FOUND = "No Converter found for the given key";
	private static final String NO_SERVICE_FOUND = "No Service found for the given key";

	private <T> FileReportEnum(String providerKey, Class<? extends IConverter> converter,
			Class<? extends IService> service) {

		this.providerKey = providerKey;
		this.converter = converter;
		this.service = service;
	}

	public String getProviderKey() {
		return providerKey;
	}

	public Class<? extends IConverter> getConverter() {
		return converter;
	}

	public Class<? extends IService> getService() {
		return service;
	}

	private static Stream<FileReportEnum> getStream(String providerKey) {
		return Arrays.stream(FileReportEnum.values()).filter(value -> value.getProviderKey().equals(providerKey));
	}

	public static Class<? extends IConverter> getConverterByKey(String providerKey)
			throws IOException, InstantiationException, IllegalAccessException {
		return getStream(providerKey).findFirst().orElseThrow(() -> new IOException(NO_CONVERTER_FOUND)).getConverter();
	}

	public static Class<? extends IService> getServiceByKey(String providerKey)
			throws IOException, InstantiationException, IllegalAccessException {
		return getStream(providerKey).findFirst().orElseThrow(() -> new IOException(NO_SERVICE_FOUND)).getService();
	}
	
}