package org.experiment.spring_data_jpa_without_springboot.dao;

import java.util.List;

import org.experiment.spring_data_jpa_without_springboot.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByLastName(String lastName);

	List<Customer> findByCountryAndLanguage(String country, String language);

	List<Customer> findByAgeGreaterThan(int age);
}
