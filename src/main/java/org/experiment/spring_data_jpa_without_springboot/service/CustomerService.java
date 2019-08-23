package org.experiment.spring_data_jpa_without_springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.experiment.spring_data_jpa_without_springboot.model.Customer;
import org.experiment.spring_data_jpa_without_springboot.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public void addCustomer(Customer customer){
		customerRepository.save(customer);
	}

	public List<Customer> findAllCustomers(){
		List<Customer> customerList = new ArrayList<>();
		Iterable<Customer> customerIterator	= customerRepository.findAll();
		for (Customer customer: customerIterator){
			customerList.add(customer);
		}
		return customerList;
	}

	public List<Customer> findCustomersByLastName(String lastName){
		return customerRepository.findByLastName(lastName);
	}

	public void removeAllCustomers(){
		customerRepository.deleteAll();
	}

	public int countTotalCustomers(){
		return (int)customerRepository.count();
	}

	public List<Customer> findCustomersByCountryAndLanguage(String country, String language){
		return customerRepository.findByCountryAndLanguage(country, language);
	}

	public List<Customer> findCustomersByAge(int age){
		return customerRepository.findByAgeGreaterThan(age);
	}

}
