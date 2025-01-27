package com.project.fooddeliveryapp.model.food;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Menus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
