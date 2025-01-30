package com.example.customerservice;

import com.example.customerservice.config.CustomerConfigParams;
import com.example.customerservice.entities.Customer;
import com.example.customerservice.repositories.CustomerRepository;
import com.example.customerservice.services.CustomerService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
		return args -> {
			customerRepository.save(new Customer(1L,"omar el", "omar@gmail.com"));
			customerRepository.save(new Customer(2L, "yassine el", "yassine@gmail.com"));
			customerRepository.save(new Customer(3L, "rachid el", "rachid@gmail.com"));
			customerRepository.save(new Customer(4L, "mourad el", "mourad@gmail.com"));
		};
	}

}
