package com.boro.black.entity.crawler;

import javax.persistence.*;

/**
 * Created by petroborovets on 4/27/15.
 */
@Entity
@Table(name = "EMAIL_CONTEXT")
public class EmailContext {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "LEFT_CONTEXT", length = 300)
    private String leftContext;
    @Column(name = "RIGHT_CONTEXT", length = 300)
    private String rightContext;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeftContext() {
        return leftContext;
    }

    public void setLeftContext(String leftContext) {
        this.leftContext = leftContext;
    }

    public String getRightContext() {
        return rightContext;
    }

    public void setRightContext(String rightContext) {
        this.rightContext = rightContext;
    }

}
