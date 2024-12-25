package com.project.fooddeliveryapp.model.food;

import jakarta.persistence.*;

@Entity
public class ProductItems extends Items {
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menus menu;

    public Menus getMenu() {
        return menu;
    }

    public void setMenu(Menus menu) {
        this.menu = menu;
    }
}
