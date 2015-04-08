package com.boro.orange.entity;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by petroborovets on 4/4/15.
 */
@Entity
@Table(name = "USERS")
public class User {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean isBlocked = false;
    private Set<SecurityRole> roles;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    @Required
    public void setFirstName(String username) {
        this.firstName = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Required
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JoinTable(name = "USER2SECURITY_ROLE", joinColumns = {@JoinColumn(name = "USER_FK")}, inverseJoinColumns = {@JoinColumn(name = "SECURITY_ROLE_FK")})
    @ManyToMany(fetch = FetchType.EAGER)
    public Set<SecurityRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SecurityRole> roles) {
        this.roles = roles;
    }

}