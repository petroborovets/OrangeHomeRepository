package com.boro.black.entity.crawler;

import javax.persistence.*;

/**
 * Created by petroborovets on 4/27/15.
 */
@Entity
@Table(name = "EMAIL_TYPE")
public class EmailType {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "JOB_APPLY")
    private boolean jobApply;
    @Column(name = "DOCTOR")
    private boolean doctor;
    @Column(name = "MANAGEMENT")
    private boolean management;
    @Column(name = "PROGRAMMING")
    private boolean programming;
    @Column(name = "IT")
    private boolean it;
    @Column(name = "HR")
    private boolean hr;
    @Column(name = "SALES")
    private boolean sales;
    @Column(name = "INFO")
    private boolean info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isJobApply() {
        return jobApply;
    }

    public void setJobApply(boolean jobApply) {
        this.jobApply = jobApply;
    }

    public boolean isDoctor() {
        return doctor;
    }

    public void setDoctor(boolean doctor) {
        this.doctor = doctor;
    }

    public boolean isManagement() {
        return management;
    }

    public void setManagement(boolean management) {
        this.management = management;
    }

    public boolean isProgramming() {
        return programming;
    }

    public void setProgramming(boolean programming) {
        this.programming = programming;
    }

    public boolean isIt() {
        return it;
    }

    public void setIt(boolean it) {
        this.it = it;
    }

    public boolean isHr() {
        return hr;
    }

    public void setHr(boolean hr) {
        this.hr = hr;
    }

    public boolean isSales() {
        return sales;
    }

    public void setSales(boolean sales) {
        this.sales = sales;
    }

    public boolean isInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }
}
