package com.huysor.ecommerce.phoneshop.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
    ADMIN(Set.of(PermissionEnum.BRAND_WRITE, PermissionEnum.BRAND_READ, PermissionEnum.MODEL_WRITE, PermissionEnum.MODEL_READ)),
    SALE(Set.of(PermissionEnum.BRAND_READ, PermissionEnum.MODEL_READ));
    private Set<PermissionEnum> permissionEnum;

    public Set<SimpleGrantedAuthority> grantedAuthorities() {
        Set<SimpleGrantedAuthority> set = this.permissionEnum.stream()
                .map(permissionEnum1 -> new SimpleGrantedAuthority(permissionEnum1.getDescription()))
                .collect(Collectors.toSet());
        SimpleGrantedAuthority role= new SimpleGrantedAuthority("ROLE_"+this.name());
        set.add(role);
        return set;
    }

}
