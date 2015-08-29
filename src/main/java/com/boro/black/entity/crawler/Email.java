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
    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmailContext> emailContexts = new HashSet<EmailContext>();
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

    public Set<EmailContext> getEmailContexts() {
        return emailContexts;
    }

    public void setEmailContexts(Set<EmailContext> emailContexts) {
        this.emailContexts = emailContexts;
    }
}
