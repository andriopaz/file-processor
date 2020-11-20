package com.report.filereportprocessor.report;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.report.filereportprocessor.reader.FileReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderTest {
	
	private static final String TEST_DIRECTORY = "src/test/resources/";

	@Mock
	private FileReader directoryFileReader;
	
	@Test
	public void test_directory_scan() {
		directoryFileReader.readSourceDirectory(TEST_DIRECTORY);
		 Mockito.verify(directoryFileReader, Mockito.times(1)).readSourceDirectory(TEST_DIRECTORY);
	}
}
