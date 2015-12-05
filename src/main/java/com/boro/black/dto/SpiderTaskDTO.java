package com.boro.black.dto;

/**
 * Created by petroborovets on 11/9/15.
 */
public class SpiderTaskDTO {
    long id;
    String name;
    String description;
    String spiderUrl;
    String progress;
    String startDate;
    String startTime;
    String finishDate;
    String finishTime;
    int numberOfEmails;
    int numberOfCompanies;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getSpiderUrl() {
        return spiderUrl;
    }

    public void setSpiderUrl(String spiderUrl) {
        this.spiderUrl = spiderUrl;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getNumberOfEmails() {
        return numberOfEmails;
    }

    public void setNumberOfEmails(int numberOfEmails) {
        this.numberOfEmails = numberOfEmails;
    }

    public void setNumberOfCompanies(int numberOfCompanies) {
        this.numberOfCompanies = numberOfCompanies;
    }

    public int getNumberOfCompanies() {
        return numberOfCompanies;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
