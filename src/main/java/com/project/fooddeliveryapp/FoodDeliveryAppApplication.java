package com.project.fooddeliveryapp;

import com.project.fooddeliveryapp.model.orders.Orders;
import com.project.fooddeliveryapp.model.users.Customers;
import com.project.fooddeliveryapp.model.users.DeliveryPartners;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;

@SpringBootApplication
public class FoodDeliveryAppApplication {
//	static {
//		PropertyConfigurator.configure(FoodDeliveryAppApplication.class.getResource("/log4j.properties"));
//	}

	public static void main(String[] args) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit.foodDelivery");
//		init(emf.createEntityManager());
//		sample(emf.createEntityManager());
//
//		emf.close();
		SpringApplication.run(FoodDeliveryAppApplication.class, args);
	}

	private static void init(EntityManager em) {
		em.getTransaction().begin();

		Customers customer1 = new Customers();
		customer1.setAddress("Address 1");
		customer1.setEmail("customer1@mail");
		customer1.setName("Customer 1");
		customer1.setRole("Customer");
		em.persist(customer1);

		Customers customer2 = new Customers();
		customer2.setAddress("Address 2");
		customer2.setEmail("customer2@mail");
		customer2.setName("Customer 2");
		customer2.setRole("Customer");
		em.persist(customer2);

		DeliveryPartners deliveryPartner1 = new DeliveryPartners();
		deliveryPartner1.setEmail("delivery1@mail");
		deliveryPartner1.setName("Delivery 1");
		deliveryPartner1.setRole("Delivery Partner");
		deliveryPartner1.setAvailable(true);
		deliveryPartner1.setOrders(new ArrayList<Orders>());
		deliveryPartner1.setPhone("1234567890");
		deliveryPartner1.setVehicleDetails("Vehicle 1");
		em.persist(deliveryPartner1);

		DeliveryPartners deliveryPartner2 = new DeliveryPartners();
		deliveryPartner2.setEmail("delivery1@mail");
		deliveryPartner2.setName("Delivery 1");
		deliveryPartner2.setRole("Delivery Partner");
		deliveryPartner2.setAvailable(true);
		deliveryPartner2.setOrders(new ArrayList<Orders>());
		deliveryPartner2.setPhone("1234567891");
		deliveryPartner2.setVehicleDetails("Vehicle 1");
		em.persist(deliveryPartner2);

		em.getTransaction().commit();
	}


	private static void sample(EntityManager em) {
		em.getTransaction().begin();

		Customers customer = em.find(Customers.class, 1);
		System.out.println(customer.getName());

		em.getTransaction().rollback();
	}
}
