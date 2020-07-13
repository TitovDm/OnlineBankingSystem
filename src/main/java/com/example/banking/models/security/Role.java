package com.example.banking.models.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Role {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

    public Role(String name, Set<UserRole> userRoles) {
        this.name = name;
        this.userRoles = userRoles;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
