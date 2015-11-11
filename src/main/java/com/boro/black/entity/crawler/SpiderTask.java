package com.boro.black.entity.crawler;

import javax.persistence.*;
import java.util.*;

/**
 * Created by petroborovets on 7/16/15.
 */
@Entity
@Table(name = "SPIDER_TASKS")
public class SpiderTask {
    public static int IS_RUNNING = 1;
    public static int IS_FINISHED = 2;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME", nullable = false, length = 200)
    private String name;
    @Column(name = "DESCRIPTION", nullable = true, length = 300)
    private String description;
    @Column(name = "PROGRESS", nullable = false)
    private Short progress = 1;
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

    public Short getProgress() {
        return progress;
    }

    public void setProgress(Short progress) {
        this.progress = progress;
        if (progress == IS_RUNNING) {
            createDate = new Date();
        }
        if (progress == IS_FINISHED) {
            finishDate = new Date();
        }
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate() {
        this.finishDate = new Date();
    }
}
