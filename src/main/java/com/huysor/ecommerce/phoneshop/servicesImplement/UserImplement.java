package com.huysor.ecommerce.phoneshop.servicesImplement;

import com.huysor.ecommerce.phoneshop.config.security.AuthUser;
import com.huysor.ecommerce.phoneshop.entity.User;
import com.huysor.ecommerce.phoneshop.exception.ApiException;
import com.huysor.ecommerce.phoneshop.repository.UserRepository;
import com.huysor.ecommerce.phoneshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
@Primary
@Service
@RequiredArgsConstructor
public class UserImplement implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<AuthUser> findByUserName(String username) {
        System.out.println(username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new ApiException(HttpStatus.NOT_FOUND,"User not found"));
        AuthUser authUser= AuthUser.builder()
                .userName(user.getUsername())
                .passWord(user.getPassword())
                .authorities(user.getRole().grantedAuthorities())
                .accountNonExpired(user.isAccountNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .accEnabled(user.isAccEnabled())
                .credentialsNonExpired(user.isCredentialsNonExpired())
                .build();
        return Optional.of(authUser);
    }
}
