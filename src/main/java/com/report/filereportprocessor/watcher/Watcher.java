package com.report.filereportprocessor.watcher;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.report.filereportprocessor.reader.FileReader;
import com.report.filereportprocessor.report.ReportCreator;

@Service
public class Watcher {

	private static final Logger LOGGER = LoggerFactory.getLogger(Watcher.class);
	
	private static final String INPUT_DIRECTORY = System.getProperty("user.home") + "\\data\\in\\";
	
	private static final String OUTPUT_DIRECTORY = System.getProperty("user.home") + "\\data\\out\\";

	private WatchService watchService;
	
	@Autowired
	private FileReader directoryFileReader;
	
	@Autowired
	private ReportCreator reportCreator;

	public void init() {
		createDirectories();
		this.start();
		this.execute();
		this.stop();
	}

	private void createDirectories() {
		try {
			Files.createDirectories(Paths.get(INPUT_DIRECTORY));
			Files.createDirectories(Paths.get(OUTPUT_DIRECTORY));
		} catch (IOException e) {
			LOGGER.error("Failed to create input and output directories.", e);
		}
	}

	public void start() {
		try {
			this.watchService = FileSystems.getDefault().newWatchService();
			Paths.get(INPUT_DIRECTORY).register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY);
			LOGGER.info("Watcher has been started.");
		} catch (IOException e) {
			LOGGER.error("Failed to start Directory Watcher.", e);
		}
	}
	
	public void execute() {
        WatchKey key;
        
        try {
			while ((key = watchService.take()) != null) {
				String filename = key.pollEvents().stream().findFirst().get().context().toString();
				directoryFileReader.readSourceDirectory(INPUT_DIRECTORY + filename);
				reportCreator.createReport(OUTPUT_DIRECTORY + filename);
			    key.reset();    
			}
		} catch (InterruptedException e) {
			LOGGER.error("Exception cought when executing watcher.", e);
		}
	}

	public void stop() {
		try {
			watchService.close();
		} catch (IOException e) {
			LOGGER.error("Failed to stop Directory Watcher.", e);
		}
	}
}
