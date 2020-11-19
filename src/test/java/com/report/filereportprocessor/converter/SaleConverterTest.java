package com.report.filereportprocessor.converter;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.report.filereportprocessor.converter.impl.SaleConverter;
import com.report.filereportprocessor.exception.CannotReadException;
import com.report.filereportprocessor.model.Sale;
import com.report.filereportprocessor.reader.FileReader;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SaleConverterTest {

	public static final String TEST_VALID_ROW_CONTENT = "003ç32ç[12-45-1.00]çSalesman name";
	public static final String TEST_INVALID_ROW_CONTENT = "003ç23ç[12-45-1.00]çINVALIDçSalesman name";
	public static final String MORE_THAN_ONE_ITEM = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";

	@Autowired
	private SaleConverter saleConverter;
	
	@MockBean
	private FileReader directoryFileReader;

	@Test
	public void read_valid_row_test() throws CannotReadException {
		Optional<?> optional = saleConverter.convert(TEST_VALID_ROW_CONTENT);

		Sale sale = (Sale) optional.get();
		assertTrue(optional.isPresent());
		assertTrue(32 == sale.getId());		
		assertTrue(1.00 == sale.getPrice());
		assertTrue("Salesman name".equalsIgnoreCase(sale.getSalesman().getName()));
	}
	
	@Test
	public void read_invalid_row_throws_an_exception() {
		Exception exception = assertThrows(CannotReadException.class, () -> {
			saleConverter.convert(TEST_INVALID_ROW_CONTENT);
	    });	
	}
	
	@Test
	public void check_total_price_of_a_sale() throws CannotReadException {
		Optional<?> optional = saleConverter.convert(MORE_THAN_ONE_ITEM);

		Sale sale = (Sale) optional.get();
		assertTrue(optional.isPresent());
		assertTrue(105.6 == sale.getPrice());
		
	}
}
