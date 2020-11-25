package com.report.filereportprocessor.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public class BaseService<T> {
	
	protected JpaRepository<T, Long> repo;
	
	public BaseService(JpaRepository<T, Long> repo) {
		this.repo = repo;
	}
	
	public List<T> findAll() {
		return repo.findAll();
	}
	
	public long count() {
		return repo.count();
	}
	
	public Iterable<T> saveAll(List<T> list) {
		return repo.saveAll(list);
	}
}
