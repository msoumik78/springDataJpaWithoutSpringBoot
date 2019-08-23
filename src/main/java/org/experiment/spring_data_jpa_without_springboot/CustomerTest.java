package org.experiment.spring_data_jpa_without_springboot;

import org.experiment.spring_data_jpa_without_springboot.model.Customer;
import org.experiment.spring_data_jpa_without_springboot.service.CustomerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomerTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
		appContext.scan("org.experiment.spring_data_jpa_without_springboot");
		appContext.refresh();

		CustomerService customerService = (CustomerService) appContext.getBean("customerService");

        System.out.println("********Removing all customers before test...clean up operation********");

        customerService.removeAllCustomers();

		Customer customer = new Customer();
		customer.setFirstName("Soumik");
		customer.setLastName("Mukherjee");
		customer.setCountry("India");
		customer.setLanguage("Bengali");
		customer.setAge(42);
		customerService.addCustomer(customer);

		System.out.println("********Now added one customer with country=India and language=Bengali ********");

		System.out.println("********Now count of customers******** :"+customerService.countTotalCustomers());

		// List all customers
		Iterable<Customer> iterator = customerService.findAllCustomers();
		iterator.forEach(customer1 -> {
				System.out.println("********Listing all customers********");
				System.out.println(customer1);
		});

        Iterable<Customer> iteratorByCountryAndLang = customerService.findCustomersByCountryAndLanguage("India", "Bengali");
        iteratorByCountryAndLang.forEach(customer2 -> {
            System.out.println("********Listing all customers by country=India and language=Bengali********");
            System.out.println(customer2);
        });

        iteratorByCountryAndLang = customerService.findCustomersByCountryAndLanguage("India", "English");
        iteratorByCountryAndLang.forEach(customer3 -> {
            System.out.println("********Listing all customers by country=India and language=English********");
            System.out.println(customer3);
        });

        Iterable<Customer> iteratorByAge = customerService.findCustomersByAge(30);
        iteratorByAge.forEach(customer4 -> {
            System.out.println("********Listing all customers by age > 30********");
            System.out.println(customer4);
        });

		System.out.println("********Now removing all customers again********");

		customerService.removeAllCustomers();

		System.out.println("********Now Count of customers******** :"+customerService.countTotalCustomers());

		appContext.close();
	}

}
