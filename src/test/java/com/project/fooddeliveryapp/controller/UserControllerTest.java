package com.project.fooddeliveryapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.fooddeliveryapp.dto.CustomersDto;
import com.project.fooddeliveryapp.dto.DeliveryPartnersDto;
import com.project.fooddeliveryapp.model.users.Customers;
import com.project.fooddeliveryapp.model.users.DeliveryPartners;
import com.project.fooddeliveryapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private List<Customers> customersList;
    private List<DeliveryPartners> deliveryPartnersList;
    private List<CustomersDto> customersDtoList;
    private List<DeliveryPartnersDto> deliveryPartnersDtoList;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        Customers customer1 = new Customers();
        customer1.setId(1L);
        customer1.setName("Customer 1");

        Customers customer2 = new Customers();
        customer2.setId(2L);
        customer2.setName("Customer 2");

        customersList = Arrays.asList(customer1, customer2);

        DeliveryPartners deliveryPartner1 = new DeliveryPartners();
        deliveryPartner1.setId(1L);
        deliveryPartner1.setName("Delivery Partner 1");

        DeliveryPartners deliveryPartner2 = new DeliveryPartners();
        deliveryPartner2.setId(2L);
        deliveryPartner2.setName("Delivery Partner 2");

        deliveryPartnersList = Arrays.asList(deliveryPartner1, deliveryPartner2);

        customersDtoList = Arrays.asList(
                new CustomersDto(1L, "Customer 1", "0512311320", "Address 1"),
                new CustomersDto(2L, "Customer 2", "0512311321", "Address 2"));
        deliveryPartnersDtoList = Arrays.asList(
                new DeliveryPartnersDto(1L, "Delivery Partner 1", "0512311320", "Vehicle 1"),
                new DeliveryPartnersDto(2L, "Delivery Partner 2", "0512311321", "Vehicle 2"));

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testAddCustomers() throws Exception {
        String jsonContent = Files.readString(Paths.get("src/test/resources/mocks/customers.json"),
                StandardCharsets.UTF_8);

        when(userService.addCustomers(anyList())).thenReturn(customersList);

        mockMvc.perform(post("/user/add/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(userService, times(1)).addCustomers(anyList());
    }

    @Test
    public void testAddDeliveryPartner() throws Exception {
        String jsonContent = Files.readString(Paths.get("src/test/resources/mocks/deliveryPartners.json"),
                StandardCharsets.UTF_8);

        when(userService.addDeliveryPartner(anyList())).thenReturn(deliveryPartnersList);

        mockMvc.perform(post("/user/add/deliveryPartner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(userService, times(1)).addDeliveryPartner(anyList());
    }

    @Test
    public void testGetCustomers() throws Exception {
        when(userService.getCustomers()).thenReturn(customersDtoList);

        mockMvc.perform(get("/user/getCustomers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(userService, times(1)).getCustomers();
    }

    @Test
    public void testGetDeliveryPartners() throws Exception {
        when(userService.getDeliveryPartners()).thenReturn(deliveryPartnersDtoList);

        mockMvc.perform(get("/user/getDeliveryPartners")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));

        verify(userService, times(1)).getDeliveryPartners();
    }
}
