package com.project.fooddeliveryapp.model.food;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Restaurants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menus menu = new Menus();

    private String name;

    private String address;

    private String phone;

    private String image;
}
