package com.report.filereportprocessor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.report.filereportprocessor.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
