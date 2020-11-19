package com.report.filereportprocessor.businesscase;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.report.filereportprocessor.model.Sale;
import com.report.filereportprocessor.model.Salesman;
import com.report.filereportprocessor.reader.FileReader;
import com.report.filereportprocessor.service.CustomerService;
import com.report.filereportprocessor.service.SaleService;
import com.report.filereportprocessor.service.SalesmanService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BusinessCaseTest {

	@Autowired
	private FileReader directoryFileReader;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SalesmanService salesmanService;
	
	@Autowired
	private SaleService saleService;;
	
	private final String FILE_PATH = "src/test/resources/testfile.dat";
	
	@Test
	public void test_example_scenario() throws IOException {
		FileWriter writer = new FileWriter(FILE_PATH);
		
		writer.write("001ç1234567891234çPedroç50000\n"
				   + "001ç3245678865434çPauloç40000.99\n"
				   + "002ç2345675434544345çJose da SilvaçRural\n"
				   + "002ç2345675433444345çEduardo PereiraçRural\n"
				   + "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro\n"
				   + "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo\n");
		writer.close();
		
		directoryFileReader.readFile(Paths.get(FILE_PATH));
		
		Long countCustomer = customerService.countCustomers();
		Long salesmanCustomer = salesmanService.countSalesman();
		Long saleCustomer = saleService.countSale();
		
		Optional<Sale> mostExpensiveSale = saleService.getMostExpensiveSale(); 
		Optional<Salesman> worstSalesman = salesmanService.getWorstSalesman();
		
		assertAll(
				() -> assertEquals(2L, countCustomer),
				() -> assertEquals(2L, salesmanCustomer),
				() -> assertEquals(2L, saleCustomer),
				() -> assertEquals(8L, mostExpensiveSale.get().getId()),
				() -> assertEquals("Paulo", worstSalesman.get().getName())
		);	
	}
}