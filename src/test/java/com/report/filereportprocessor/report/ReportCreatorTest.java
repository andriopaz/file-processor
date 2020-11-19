package com.report.filereportprocessor.report;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.report.filereportprocessor.reader.FileReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportCreatorTest {
	
	private static final String TEST_DIRECTORY = "src/test/resources/";

	@Autowired
	private FileReader directoryFileReader;
	
	@Test
	public void test_directory_scan() {
		directoryFileReader.readSourceDirectory(TEST_DIRECTORY);
	}
}
