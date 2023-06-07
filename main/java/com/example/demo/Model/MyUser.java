package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username shouldn't be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String username;

    @NotEmpty(message = "password shouldn't be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @NotEmpty(message = "role can't be empty")
    @Column(columnDefinition = "varchar(25) not null check ( role='CUSTOMER' or role='ADMIN')")
    private String role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "myUser")
    private Set<Order> orderSet;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isEqualTo() {
        return true;
    }
}

