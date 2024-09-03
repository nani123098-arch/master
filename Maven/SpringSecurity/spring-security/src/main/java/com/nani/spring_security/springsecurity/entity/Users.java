package com.nani.spring_security.springsecurity.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
public class Users {

    @Id
    private String username;

    private String password;

    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Set<Authority> authorities;
}
