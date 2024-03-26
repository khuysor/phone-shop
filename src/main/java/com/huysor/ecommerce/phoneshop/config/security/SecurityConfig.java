package com.huysor.ecommerce.phoneshop.config.security;

import com.huysor.ecommerce.phoneshop.config.jwt.JwtLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;


@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                 http
                .csrf().disable()
                         .addFilter(new JwtLoginFilter(authentication -> authentication))
                .authorizeHttpRequests()
                .requestMatchers("/", "index.html").permitAll()
//                .requestMatchers("/brand").hasRole("SALE") //this satic roles
//                .requestMatchers(HttpMethod.GET,"/brand").hasAuthority("brand:read")// untype safe
//                .requestMatchers(HttpMethod.GET, "/brand").hasAuthority(PermissionEnum.BRAND_READ.getDescription())
//                .requestMatchers("/brand").hasRole(RoleEnum.SALE.name()) // allow to access all http method
//                .requestMatchers(HttpMethod.POST, "/brand").hasAuthority(PermissionEnum.BRAND_WRITE.getDescription())// type safe
                .anyRequest()
                .authenticated();
//                .and() this basic auth
//                .httpBasic();
        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userDetails = User
                .withUsername("user")
                .password(passwordEncoder.encode("123"))
//                .roles("ADMIN") // static roles
                .authorities(RoleEnum.ADMIN.grantedAuthorities()) // collection of granted  authorities
                .build();
        UserDetails userDetails1 = User
                .withUsername("kok")
                .password(passwordEncoder.encode("123"))
                .authorities(RoleEnum.SALE.grantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(userDetails, userDetails1);
    }


}