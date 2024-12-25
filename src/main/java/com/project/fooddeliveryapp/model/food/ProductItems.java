package com.project.fooddeliveryapp.model.food;

import jakarta.persistence.*;

@Entity
public class ProductItems extends Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menus menu;

    public Long getId() {
        return id;
    }

    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }
}
