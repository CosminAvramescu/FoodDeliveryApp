package com.project.fooddeliveryapp.model.food;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Menus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "menu")
    private List<ProductItems> productItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public List<ProductItems> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItems> productItems) {
        this.productItems = productItems;
    }
}
