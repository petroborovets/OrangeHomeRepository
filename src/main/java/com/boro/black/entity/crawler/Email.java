package com.boro.black.entity.crawler;


import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by petroborovets on 4/27/15.
 */
@Entity
@Table(name = "EMAILS")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMAIL", unique = true, nullable = false, length = 50)
    private String email;
    @Column(name = "URL", length = 500)
    private String url;
//    @OneToOne(mappedBy = "email", cascade = CascadeType.ALL, orphanRemoval = true)
//    private EmailContext emailContext;
    @Column(name = "LEFT_CONTEXT", length = 200)
    private String leftContext;
    @Column(name = "RIGHT_CONTEXT", length = 100)
    private String rightContext;
    @OneToOne
    @JoinColumn(name = "EMAIL_TYPE_FK", nullable = true)
    private EmailType emailType;
    @ManyToOne
    @JoinColumn(name = "COMPANY_FK", nullable = false)
    private Company company;
    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;

    public Email() {
        createDate = new Date();
    }

    public Company getCompany() {
        return company;
    }
    @Required
    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    @Required
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public EmailType getEmailType() {
        return emailType;
    }

    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }

//    public EmailContext getEmailContext() {
//        return emailContext;
//    }
//
//    public void setEmailContext(EmailContext emailContext) {
//        this.emailContext = emailContext;
//    }

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

    public Date getCreateDate() {
        return createDate;
    }
}
