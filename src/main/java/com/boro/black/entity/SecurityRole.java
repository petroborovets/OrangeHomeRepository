package com.boro.black.entity;

import javax.persistence.*;

/**
 * Created by petroborovets on 4/8/15.
 */

@Entity
@Table(name = "SECURITY_ROLES")
public class SecurityRole {
    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
