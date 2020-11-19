package com.report.filereportprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.report.filereportprocessor.watcher.Watcher;

@SpringBootApplication
public class FileReportProcessorApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FileReportProcessorApplication.class, args);
		Watcher watcher = context.getBean(Watcher.class);
		watcher.init();
	}

}
