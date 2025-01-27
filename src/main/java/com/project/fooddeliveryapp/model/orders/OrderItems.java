package com.project.fooddeliveryapp.model.orders;

import com.project.fooddeliveryapp.model.food.ProductItems;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "productItem_id", nullable = false)
    private ProductItems productItem;

    private Integer quantity;
}
