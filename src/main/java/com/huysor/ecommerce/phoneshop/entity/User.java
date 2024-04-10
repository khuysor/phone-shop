package com.huysor.ecommerce.phoneshop.entity;

import com.huysor.ecommerce.phoneshop.config.security.RoleEnum;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean accEnabled;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> roles;

}
