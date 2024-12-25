package com.project.fooddeliveryapp.model.orders;

import com.project.fooddeliveryapp.model.food.Items;
import com.project.fooddeliveryapp.model.food.ProductItems;
import jakarta.persistence.*;

@Entity
public class OrderItems extends Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "productItem_id", nullable = false)
    private ProductItems productItem;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders order;
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public ProductItems getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItems productItem) {
        this.productItem = productItem;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
