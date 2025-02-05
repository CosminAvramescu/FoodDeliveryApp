package com.project.fooddeliveryapp.service;

import com.project.fooddeliveryapp.model.food.Menus;
import com.project.fooddeliveryapp.model.food.ProductItems;
import com.project.fooddeliveryapp.model.orders.OrderItems;
import com.project.fooddeliveryapp.repository.MenuRepository;
import com.project.fooddeliveryapp.repository.OrderItemRepository;
import com.project.fooddeliveryapp.repository.ProductItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private ProductItemRepository productItemRepository;

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private ItemService itemService;

    private ProductItems productItem;
    private OrderItems orderItem;
    private List<ProductItems> productItemsList;

    @BeforeEach
    public void setUp() {
        productItem = new ProductItems();
        productItem.setId(1L);
        productItem.setName("Pizza");
        productItem.setPrice(9.99);
        Menus menu = new Menus();
        menu.setId(1L);
        productItem.setMenu(menu);

        orderItem = new OrderItems();
        orderItem.setId(1L);
        orderItem.setProductItem(productItem);
        orderItem.setQuantity(2);

        productItemsList = Arrays.asList(productItem);
    }

    @Test
    public void testAddProductItems() {
        when(menuRepository.findById(anyLong())).thenReturn(Optional.of(productItem.getMenu()));
        when(productItemRepository.saveAll(anyList())).thenReturn(productItemsList);

        List<ProductItems> result = itemService.addProductItems(productItemsList);

        verify(menuRepository, times(1)).findById(anyLong());
        verify(productItemRepository, times(1)).saveAll(anyList());

        assert result != null;
        assert result.size() == 1;
        assert result.get(0).getName().equals("Pizza");
    }

    @Test
    public void testAddOrderItem() {
        when(orderItemRepository.save(any(OrderItems.class))).thenReturn(orderItem);

        OrderItems result = itemService.addOrderItem(orderItem);

        verify(orderItemRepository, times(1)).save(any(OrderItems.class));

        assert result != null;
        assert result.getProductItem().getName().equals("Pizza");
        assert result.getQuantity() == 2;
    }

    @Test
    public void testGetProductItems() throws Exception {
        when(productItemRepository.findAll()).thenReturn(productItemsList);

        List<ProductItems> result = itemService.getProductItems().get();

        verify(productItemRepository, times(1)).findAll();

        assert result != null;
        assert result.size() == 1;
        assert result.get(0).getName().equals("Pizza");
    }
}
