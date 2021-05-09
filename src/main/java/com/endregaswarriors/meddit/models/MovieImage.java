package com.endregaswarriors.meddit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieImage {

    @SerializedName(value = "file_path")
    private String path;
    private int height;
    private int width;
    @SerializedName(value = "iso_639_1")
    private String language;

    public MovieImage()
    {

    }

    public MovieImage(String path, int height, int width, String language) {
        this.path = path;
        this.height = height;
        this.width = width;
        this.language = language;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "MovieImage{" +
                "path='" + path + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", language='" + language + '\'' +
                '}';
    }
}
