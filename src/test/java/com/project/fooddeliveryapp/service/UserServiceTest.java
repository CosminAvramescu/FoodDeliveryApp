package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.dto.CustomersDto;
import com.project.fooddeliveryapp.dto.DeliveryPartnersDto;
import com.project.fooddeliveryapp.model.users.Customers;
import com.project.fooddeliveryapp.model.users.DeliveryPartners;
import com.project.fooddeliveryapp.repository.CustomerRepository;
import com.project.fooddeliveryapp.repository.DeliveryPartnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private DeliveryPartnerRepository deliveryPartnerRepository;

    @InjectMocks
    private UserService userService;

    private List<Customers> customersList;
    private List<DeliveryPartners> deliveryPartnersList;

    @BeforeEach
    public void setUp() {
        Customers customer1 = new Customers();
        customer1.setId(1L);
        customer1.setName("Customer 1");
        customer1.setPhone("1234567890");
        customer1.setAddress("Address 1");

        Customers customer2 = new Customers();
        customer2.setId(2L);
        customer2.setName("Customer 2");
        customer2.setPhone("0987654321");
        customer2.setAddress("Address 2");

        customersList = Arrays.asList(customer1, customer2);

        DeliveryPartners deliveryPartner1 = new DeliveryPartners();
        deliveryPartner1.setId(1L);
        deliveryPartner1.setName("Delivery Partner 1");
        deliveryPartner1.setPhone("1122334455");
        deliveryPartner1.setVehicleDetails("Bike");

        DeliveryPartners deliveryPartner2 = new DeliveryPartners();
        deliveryPartner2.setId(2L);
        deliveryPartner2.setName("Delivery Partner 2");
        deliveryPartner2.setPhone("2233445566");
        deliveryPartner2.setVehicleDetails("Car");

        deliveryPartnersList = Arrays.asList(deliveryPartner1, deliveryPartner2);
    }

    @Test
    public void testAddCustomers() {
        when(customerRepository.saveAll(anyList())).thenReturn(customersList);

        List<Customers> result = userService.addCustomers(customersList);

        verify(customerRepository, times(1)).saveAll(anyList());
        assert result != null;
        assert result.size() == 2;
        assert result.get(0).getId() == 1L;
        assert result.get(1).getId() == 2L;
    }

    @Test
    public void testAddDeliveryPartner() {
        when(deliveryPartnerRepository.saveAll(anyList())).thenReturn(deliveryPartnersList);

        List<DeliveryPartners> result = userService.addDeliveryPartner(deliveryPartnersList);

        verify(deliveryPartnerRepository, times(1)).saveAll(anyList());
        assert result != null;
        assert result.size() == 2;
        assert result.get(0).getId() == 1L;
        assert result.get(1).getId() == 2L;
    }

    @Test
    public void testGetCustomers() {
        when(customerRepository.findAll()).thenReturn(customersList);

        List<CustomersDto> result = userService.getCustomers();

        verify(customerRepository, times(1)).findAll();
        assert result != null;
        assert result.size() == 2;
        assert result.get(0).id() == 1L;
        assert result.get(1).id() == 2L;
    }

    @Test
    public void testGetDeliveryPartners() {
        when(deliveryPartnerRepository.findAll()).thenReturn(deliveryPartnersList);

        List<DeliveryPartnersDto> result = userService.getDeliveryPartners();

        verify(deliveryPartnerRepository, times(1)).findAll();
        assert result != null;
        assert result.size() == 2;
        assert result.get(0).id() == 1L;
        assert result.get(1).id() == 2L;
    }
}
