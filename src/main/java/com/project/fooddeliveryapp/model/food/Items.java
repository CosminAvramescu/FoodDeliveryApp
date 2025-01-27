package com.project.fooddeliveryapp.model.food;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class Items {
    private String name;

    private Double price;

    private String description;

    private String image;
}

