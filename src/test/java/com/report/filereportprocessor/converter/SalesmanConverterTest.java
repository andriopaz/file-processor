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

import com.report.filereportprocessor.converter.impl.SalesmanConverter;
import com.report.filereportprocessor.exception.CannotReadException;
import com.report.filereportprocessor.model.Salesman;
import com.report.filereportprocessor.reader.FileReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalesmanConverterTest {

	public static final String TEST_VALID_ROW_CONTENT = "001çCPFçNameç999.00";
	public static final String TEST_INVALID_ROW_CONTENT = "çINVAL001çCPFçNameç999.00";


	@Autowired
	private SalesmanConverter salesmanConverter;

	@MockBean
	private FileReader directoryFileReader;

	@Test
	public void read_valid_row_test() throws CannotReadException {
		Optional<?> optional = salesmanConverter.convert(TEST_VALID_ROW_CONTENT);

		Salesman salesman = (Salesman) optional.get();
		assertTrue(optional.isPresent());
		assertTrue("CPF".equalsIgnoreCase(salesman.getCpf()));		
		assertTrue(999.00 == salesman.getSalary());
		assertTrue("Name".equalsIgnoreCase(salesman.getName()));
	}
	
	@Test
	public void read_invalid_row_throws_an_exception() {
		Exception exception = assertThrows(CannotReadException.class, () -> {
			salesmanConverter.convert(TEST_INVALID_ROW_CONTENT);
	    });	
	}
}
