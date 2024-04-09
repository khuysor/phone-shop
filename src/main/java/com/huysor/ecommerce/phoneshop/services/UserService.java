package com.huysor.ecommerce.phoneshop.services;

import com.huysor.ecommerce.phoneshop.config.security.AuthUser;

import java.util.Optional;

public interface UserService {
    Optional<AuthUser>findByUserName(String username);
}
