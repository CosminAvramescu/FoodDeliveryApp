package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.dto.OrderItemsDto;
import com.project.fooddeliveryapp.dto.OrdersDto;
import com.project.fooddeliveryapp.exceptions.AvailableDeliveryPartnerNotFoundException;
import com.project.fooddeliveryapp.exceptions.OrderNotFoundException;
import com.project.fooddeliveryapp.exceptions.UserNotFoundException;
import com.project.fooddeliveryapp.model.food.ProductItems;
import com.project.fooddeliveryapp.model.orders.OrderItems;
import com.project.fooddeliveryapp.model.orders.Orders;
import com.project.fooddeliveryapp.model.users.Customers;
import com.project.fooddeliveryapp.model.users.DeliveryPartners;
import com.project.fooddeliveryapp.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private DeliveryPartnerRepository deliveryPartnerRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private ProductItemRepository productItemRepository;

    @InjectMocks
    private OrderService orderService;

    private Orders order;
    private OrdersDto ordersDto;
    private Customers customer;
    private DeliveryPartners deliveryPartner;
    private OrderItems orderItem;
    private List<OrderItems> orderItemsList;

    @BeforeEach
    public void setUp() {
        customer = new Customers();
        customer.setId(1L);
        customer.setOrders(new ArrayList<>());

        deliveryPartner = new DeliveryPartners();
        deliveryPartner.setId(1L);
        deliveryPartner.setAvailable(true);

        orderItem = new OrderItems();
        orderItem.setId(1L);
        orderItem.setQuantity(2);

        ProductItems productItems = new ProductItems();
        productItems.setId(100L);
        productItems.setName("Pizza");
        productItems.setPrice(9.99);
        orderItem.setProductItem(productItems);

        orderItemsList = new ArrayList<>();
        orderItemsList.add(orderItem);

        order = new Orders();
        order.setId(1L);
        order.setOrderItems(orderItemsList);
        order.setCustomer(customer);
        order.setTotalAmount(19.98);
        order.setOrderTime("2025-02-05T14:00:00");

        ordersDto = new OrdersDto(customer.getId(), order.getOrderTime(), deliveryPartner.getName(), order.getTotalAmount(), 1);
    }

    @Test
    public void testAddOrdersSuccessfully() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(deliveryPartnerRepository.findAll()).thenReturn(Collections.singletonList(deliveryPartner));
        when(orderItemRepository.saveAll(anyList())).thenReturn(orderItemsList);
        when(productItemRepository.findById(anyLong())).thenReturn(Optional.of(orderItem.getProductItem()));
        when(orderRepository.save(any(Orders.class))).thenReturn(order);

        OrdersDto result = orderService.addOrders(order, customer.getId());

        verify(customerRepository, times(1)).findById(anyLong());
        verify(deliveryPartnerRepository, times(1)).findAll();
        verify(orderItemRepository, times(1)).saveAll(anyList());
        verify(orderRepository, times(1)).save(any(Orders.class));

        assert result != null;
        assert result.customerId().equals(customer.getId());
        assert result.orderTime().equals(order.getOrderTime());
    }

    @Test
    public void testAddOrdersThrowsUserNotFoundException() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            orderService.addOrders(order, customer.getId());
        } catch (UserNotFoundException e) {
            assert e.getMessage().contains("Customer with id " + customer.getId() + " not found.");
        }
    }

    @Test
    public void testAddOrdersThrowsAvailableDeliveryPartnerNotFoundException() {
        when(deliveryPartnerRepository.findAll()).thenReturn(Collections.emptyList());
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        try {
            orderService.addOrders(order, customer.getId());
        } catch (AvailableDeliveryPartnerNotFoundException e) {
            assert e.getMessage().contains("No Delivery Partner available.");
        }
    }

    @Test
    public void testGetOrdersByCustomerId() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        when(deliveryPartnerRepository.findAll()).thenReturn(Collections.singletonList(deliveryPartner));
        when(productItemRepository.findById(anyLong())).thenReturn(Optional.of(orderItem.getProductItem()));

        orderService.addOrders(order, customer.getId());

        List<OrdersDto> result = orderService.getOrdersByCustomerId(customer.getId());

        assert result.size() == 1;
        assert result.get(0).customerId().equals(customer.getId());
        assert result.get(0).orderTime().equals(order.getOrderTime());
    }

    @Test
    public void testGetOrdersByCustomerIdThrowsUserNotFoundException() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            orderService.getOrdersByCustomerId(customer.getId());
        } catch (UserNotFoundException e) {
            assert e.getMessage().contains("Customer with id " + customer.getId() + " not found.");
        }
    }

    @Test
    public void testGetOrderItemsByOrderId() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));

        List<OrderItemsDto> result = orderService.getOrderItemsByOrderId(order.getId());

        assert result.size() == 1;
        assert result.get(0).name().equals(orderItem.getProductItem().getName());
        assert result.get(0).price() == orderItem.getProductItem().getPrice();
    }

    @Test
    public void testGetOrderItemsByOrderIdThrowsOrderNotFoundException() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        try {
            orderService.getOrderItemsByOrderId(order.getId());
        } catch (OrderNotFoundException e) {
            assert e.getMessage().contains("Order with id " + order.getId() + " not found.");
        }
    }
}
