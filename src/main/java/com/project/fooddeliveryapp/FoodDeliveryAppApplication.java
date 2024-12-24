package com.project.fooddeliveryapp;

import com.project.fooddeliveryapp.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.log4j.PropertyConfigurator;

@SpringBootApplication
public class FoodDeliveryAppApplication {
	static {
		PropertyConfigurator.configure(FoodDeliveryAppApplication.class.getResource("/log4j.properties"));
	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit.foodDelivery");
		init(emf.createEntityManager());
		sample(emf.createEntityManager());

		emf.close();
//		SpringApplication.run(FoodDeliveryAppApplication.class, args);
	}

	private static void init(EntityManager em) {
		em.getTransaction().begin();

		Customer customer = new Customer();
		customer.setAddress("Address 1");
		customer.setEmail("customer@mail");
		customer.setName("Customer 1");
		customer.setRole("Customer");
		em.persist(customer);

		em.getTransaction().commit();
	}


	private static void sample(EntityManager em) {
		em.getTransaction().begin();

		Customer customer = em.find(Customer.class, 1);
		System.out.println(customer.getName());

		em.getTransaction().rollback();
	}
}
