package com.project.fooddeliveryapp.model.orders;

import com.project.fooddeliveryapp.model.food.Items;
import com.project.fooddeliveryapp.model.food.ProductItems;
import jakarta.persistence.*;

@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "productItem_id", nullable = false)
    private ProductItems productItem;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
