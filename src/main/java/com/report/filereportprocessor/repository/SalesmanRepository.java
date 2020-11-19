package com.report.filereportprocessor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.report.filereportprocessor.model.Salesman;

@Repository
public interface SalesmanRepository extends JpaRepository<Salesman, Long> {
}
