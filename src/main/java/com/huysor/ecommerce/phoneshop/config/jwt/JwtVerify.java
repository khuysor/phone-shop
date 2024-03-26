package com.huysor.ecommerce.phoneshop.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtVerify extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.contains("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authorization.replace("Bearer ", "");
        String secretKey = "abdfdsjsdfkjjna;jksldfklasdfjklasdkljf";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        String username = body.getSubject();
        List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
        Set<SimpleGrantedAuthority> authority = authorities.stream().map(x -> new SimpleGrantedAuthority(x.get("authority")))
                .collect(Collectors.toSet());

        Authentication authentication= new UsernamePasswordAuthenticationToken(username,null,authority);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }
}
