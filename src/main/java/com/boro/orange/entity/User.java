package com.boro.orange.entity;

import com.boro.orange.entity.crawler.EmailType;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by petroborovets on 4/4/15.
 */
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;
    @Column(name = "PASSWORD", nullable = false, length = 50)
    private String password;

    @JoinTable(name = "USER2SECURITY_ROLES", joinColumns = {@JoinColumn(name = "USER_FK")}, inverseJoinColumns = {@JoinColumn(name = "SECURITY_ROLE_FK")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<SecurityRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<SecurityRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SecurityRole> roles) {
        this.roles = roles;
    }
}