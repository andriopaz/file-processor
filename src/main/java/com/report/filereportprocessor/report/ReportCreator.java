package com.report.filereportprocessor.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.report.filereportprocessor.service.CustomerService;
import com.report.filereportprocessor.service.SaleService;
import com.report.filereportprocessor.service.SalesmanService;

import lombok.Getter;
import lombok.Setter;

@Component
public class ReportCreator {
	
	private static final String LABEL_NAME_OF_WORST_SALESMAN = "Name of worst salesman: ";

	private static final String LABEL_ID_OF_MOST_EXPENSIVE_SALE = "ID of most expensive sale: ";

	private static final String LABEL_TOTAL_SALEMEN = "Total salemen: ";

	private static final String LABEL_TOTAL_CUSTOMERS = "Total customers: ";

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportCreator.class);
	
	@Getter @Setter
	private long totalCustomers;
	@Getter @Setter
	private long totalSalesman;
	@Getter @Setter
	private Long idMostExpensiveSale;
	@Getter @Setter
	private String worstSalesman;
	
	@Value("${input.file.format}")
	private String inputFileFormat;
	
	@Value("${output.file.format}")
	private String outputFileFormat;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SalesmanService salesmanService;
	
	@Autowired
	private SaleService saleService;
	
	public void createReport(String path) {
		fetchInformation();
		createFile(path);
	}
	
	private void fetchInformation() {
		this.setTotalCustomers(customerService.countCustomers());
		this.setTotalSalesman(salesmanService.countSalesman());
		this.setIdMostExpensiveSale(saleService.getMostExpensiveSale().get().getId());
		this.setWorstSalesman(salesmanService.getWorstSalesman().get().getName());
	}
	
	private void createFile(String path) {
		
		LOGGER.info("Creating output File...");
		path = path.replace(inputFileFormat, "");
		
		try{
			StringJoiner joiner = new StringJoiner("\n");
			
			joiner
				.add(LABEL_TOTAL_CUSTOMERS + Long.toString(getTotalCustomers()))
				.add(LABEL_TOTAL_SALEMEN + Long.toString(getTotalSalesman()))
				.add(LABEL_ID_OF_MOST_EXPENSIVE_SALE + Long.toString(getIdMostExpensiveSale()))
				.add(LABEL_NAME_OF_WORST_SALESMAN + getWorstSalesman());
			
	        File file = new File(path + outputFileFormat);

	        FileWriter fw = new FileWriter(file.getAbsoluteFile());
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(joiner.toString());
	        bw.close();

	    }catch(IOException e){
	        LOGGER.error("Failed to create the outputFile: " + path, e);
	    }
		LOGGER.info(path + " was successfully created.");
	}
}
