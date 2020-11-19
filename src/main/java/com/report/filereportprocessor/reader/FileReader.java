package com.report.filereportprocessor.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.report.filereportprocessor.process.ProcessFactory;

@Component
public class FileReader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);
	
	@Autowired
	private ProcessFactory processFactory;

	@Value("${input.file.format}")
	private String inputFormat;

	public void readSourceDirectory(String sourcePath) {
		try (Stream<Path> paths = Files.walk(Paths.get(sourcePath))) {
			paths
			.filter(Files::isRegularFile)
			.filter(s -> s.toString().endsWith(inputFormat))
					.forEach(f -> readFile(f));
		} catch (IOException e) {
			LOGGER.error("Error when reading source directory.", e);
		}
	}

	@SuppressWarnings("resource")
	public void readFile(Path path) {
		LOGGER.info("Starting to process the file: " + path);
		try {
			Stream<String> lines = Files.lines(path);
			lines.filter(l -> l != null && !l.isBlank()).forEach(l -> processFactory.process(l));
		} catch (IOException e) {
			LOGGER.error("Error when reading source file.", e);
		}
		LOGGER.info("Finished to process file: " + path);
	}
}
