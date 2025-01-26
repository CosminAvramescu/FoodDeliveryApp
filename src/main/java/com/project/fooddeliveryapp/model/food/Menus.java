package com.project.fooddeliveryapp.model.food;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Menus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }
}
