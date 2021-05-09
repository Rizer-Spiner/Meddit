package com.endregaswarriors.meddit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieVideo {

    private String key;
    private String name;
    private String site;

    public MovieVideo(String key, String name, String site) {
        this.key = key;
        this.name = name;
        this.site = site;
    }

    public MovieVideo() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "MovieVideo{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", site='" + site + '\'' +
                '}';
    }
}
