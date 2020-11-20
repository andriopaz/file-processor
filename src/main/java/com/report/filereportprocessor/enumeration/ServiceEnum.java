package com.report.filereportprocessor.enumeration;

import java.io.IOException;
import java.util.Arrays;

import com.report.filereportprocessor.service.CustomerService;
import com.report.filereportprocessor.service.IService;
import com.report.filereportprocessor.service.SaleService;
import com.report.filereportprocessor.service.SalesmanService;

@SuppressWarnings("rawtypes")
public enum ServiceEnum {

	SALESMAN("001", SalesmanService.class), 
	CUSTOMER("002", CustomerService.class),
	SALE("003", SaleService.class);

	private String providerKey;

	private Class<? extends IService> service;

	private <T> ServiceEnum(String providerKey,
			Class<? extends IService> service) {

		this.providerKey = providerKey;
		this.service = service;
	}

	public String getProviderKey() {
		return providerKey;
	}

	public Class<? extends IService> getService() {
		return service;
	}

	public static Class<? extends IService> getServiceByKey(
			String providerKey) throws IOException, InstantiationException, IllegalAccessException {
		return Arrays.stream(ServiceEnum.values())
				.filter(value -> value.getProviderKey().equals(providerKey)).findFirst()
				.orElseThrow(() -> new IOException("No service found for the given key")).getService();
	}
}