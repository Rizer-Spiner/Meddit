package com.endregaswarriors.meddit.models;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class MedditInfo {

    private Integer members;
    private LocalDate creationDate;

    public MedditInfo(Integer members, LocalDate creationDate) {
        this.members = members;
        this.creationDate = creationDate;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "MedditInfo{" +
                "members=" + members +
                ", creationDate=" + creationDate +
                '}';
    }
}
