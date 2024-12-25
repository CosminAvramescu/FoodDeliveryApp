package com.project.fooddeliveryapp;

import com.project.fooddeliveryapp.model.users.Customers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FoodDeliveryAppApplicationTests {

    private static EntityManagerFactory entityManagerFactory;

    static {
        PropertyConfigurator.configure(FoodDeliveryAppApplication.class.getResource("/log4j.properties"));
    }

    @BeforeAll
    static void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit.foodDelivery");
    }

    @AfterAll
    static void teardown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    void testEntityManagerFactoryCreation() {
        assertNotNull(entityManagerFactory, "EntityManagerFactory should not be null");
    }

    @Test
    void testDatabaseOperations() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();

            // Create and persist a customer
            Customers customer = new Customers();
            customer.setName("Test Customer");
            customer.setEmail("test@mail.com");
            customer.setRole("Customer");
            customer.setAddress("Test Address");

            em.persist(customer);
            em.getTransaction().commit();

            // Verify if the customer was persisted
            Customers persistedCustomer = em.find(Customers.class, customer.getId());
            assertNotNull(persistedCustomer, "Persisted customer should not be null");
        } finally {
            em.close();
        }
    }
}
