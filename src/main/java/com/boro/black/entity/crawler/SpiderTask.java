package com.boro.black.entity.crawler;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by petroborovets on 7/16/15.
 */
@Entity
@Table(name = "SPIDER_TASKS")
public class SpiderTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    @Column(name = "DESCRIPTION", nullable = true, length = 300)
    private String description;
    @OneToMany(mappedBy = "spiderTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Company> companies = new HashSet<Company>();
    @Column(name = "PROGRESS", nullable = false)
    private Integer progress = 0;
    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;
    @Column(name = "FINISH_DATE", nullable = true)
    private Date finishDate;

    public SpiderTask() {
        createDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Company> getCompany() {
        return companies;
    }

    public void setCompany(Set<Company> companies) {
        this.companies = companies;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
        if (progress == 2) {
            finishDate = new Date();
        }
    }
}
