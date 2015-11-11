package com.boro.black.entity.crawler;

import javax.persistence.*;
import java.util.List;

/**
 * Created by petroborovets on 6/27/15.
 */
@Entity
@Table(name = "COMPANIES")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "DOMAIN_URL", unique = true, nullable = false, length = 50)
    private String domainUrl;
    @ManyToOne
    @JoinColumn(name = "SPIDER_TASK_FK", nullable = false)
    private SpiderTask spiderTask;
    @OneToMany(mappedBy = "company")
    private List<Email> emails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainUrl() {
        return domainUrl;
    }

    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
        for (Email email : emails) {
            email.setCompany(this);
        }
    }

    public SpiderTask getSpiderTask() {
        return spiderTask;
    }

    public void setSpiderTask(SpiderTask spiderTask) {
        this.spiderTask = spiderTask;
    }
}
