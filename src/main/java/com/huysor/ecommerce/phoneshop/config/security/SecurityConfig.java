package com.huysor.ecommerce.phoneshop.config.security;

import com.huysor.ecommerce.phoneshop.config.jwt.JwtLoginFilter;
import com.huysor.ecommerce.phoneshop.config.jwt.JwtVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;


@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilter(new JwtLoginFilter(authenticationManager(authenticationConfiguration)))
                .addFilterAfter(new JwtVerify(), JwtLoginFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/", "index.html").permitAll()
                .anyRequest().authenticated();
        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder().username("user")
                .password(passwordEncoder.encode("123"))
//                .roles("ADMIN") // static roles
                .authorities(RoleEnum.ADMIN.grantedAuthorities()) // collection of granted  authorities
                .build();
        UserDetails userDetails1 = User
                .withUsername("kok")
                .password(passwordEncoder.encode("123"))
                .authorities(RoleEnum.SALE.grantedAuthorities())
                .build();
        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(userDetails, userDetails1);
        return userDetailsService;
    }


}