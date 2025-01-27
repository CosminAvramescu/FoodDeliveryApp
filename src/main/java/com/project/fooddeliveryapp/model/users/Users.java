package com.project.fooddeliveryapp.model.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class Users {
    private String name;

    private String email;

    private String phone;

    private String role;
}

