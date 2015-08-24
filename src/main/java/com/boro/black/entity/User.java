package com.boro.black.entity;

import javax.persistence.*;
import java.util.Date;
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
    @Column(name = "EMAIL", nullable = false, length = 50, unique = true)
    private String email;
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;
    @Column(name = "PASSWORD", nullable = false, length = 60)
    private String password;
    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;

    public User() {
        createDate = new Date();
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}