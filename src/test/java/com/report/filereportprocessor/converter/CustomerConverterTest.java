package com.report.filereportprocessor.converter;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.report.filereportprocessor.converter.impl.CustomerConverter;
import com.report.filereportprocessor.exception.CannotReadException;
import com.report.filereportprocessor.model.Customer;
import com.report.filereportprocessor.reader.FileReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerConverterTest {

	public static final String TEST_VALID_ROW_CONTENT = "002çCNPJçNameçBusiness Area";
	public static final String TEST_INVALID_ROW_CONTENT = "002çCNPJçNameçINVALIDçBusiness Area";

	@Autowired
	private CustomerConverter customerConverter;
	
	@MockBean
	private FileReader directoryFileReader;

	@Test
	public void read_valid_row_test() throws CannotReadException {
		Optional<?> optional = customerConverter.convert(TEST_VALID_ROW_CONTENT);

		Customer customer = (Customer) optional.get();
		assertTrue(optional.isPresent());

		assertTrue("Name".equalsIgnoreCase(customer.getName()));
		assertTrue("CNPJ".equalsIgnoreCase(customer.getCnpj()));
		assertTrue("Business Area".equalsIgnoreCase(customer.getBusinessArea()));
	}
	
	@Test
	public void read_invalid_row_throws_an_exception() {
		Exception exception = assertThrows(CannotReadException.class, () -> {
			customerConverter.convert(TEST_INVALID_ROW_CONTENT);
	    });
	}
}
