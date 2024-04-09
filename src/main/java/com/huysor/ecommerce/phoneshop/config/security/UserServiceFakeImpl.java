package com.huysor.ecommerce.phoneshop.config.security;

import com.huysor.ecommerce.phoneshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceFakeImpl implements UserService {
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<AuthUser> findByUserName(String username) {
        List<AuthUser> authUser = List.of(
                new AuthUser("sale", passwordEncoder.encode("123"), RoleEnum.SALE.grantedAuthorities(), true, true, true, true),
                new AuthUser("admin", passwordEncoder.encode("123"), RoleEnum.ADMIN.grantedAuthorities(), true, true, true, true)
        );

        return authUser.stream().filter(user->user.getUsername().equals(username)).findFirst();
    }
}
