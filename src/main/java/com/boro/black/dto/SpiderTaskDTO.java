package com.boro.black.dto;

/**
 * Created by petroborovets on 11/9/15.
 */
public class SpiderTaskDTO {
    long id;
    String name;
    short progress;
    String startDate;
    int numberOfEmails;

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

    public short getProgress() {
        return progress;
    }

    public void setProgress(short progress) {
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
}
