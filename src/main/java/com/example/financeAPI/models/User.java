package com.example.financeAPI.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean active;
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    public User(){

    }

    public User(String name,
                String email,
                String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = true;
    }

    public void activate(){
        if (active){
            throw new IllegalArgumentException("The user is already active.");
        }
        this.active = true;
    }

    public void deactivate(){
        if (!active){
            throw new IllegalArgumentException("The user is already deactivated.");
        }
        this.active = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
