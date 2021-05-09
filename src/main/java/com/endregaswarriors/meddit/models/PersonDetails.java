package com.endregaswarriors.meddit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDetails {

    private long id;
    @SerializedName(value = "known_for_department")
    private String department;
    @SerializedName(value = "original_name")
    private String name;
    private int cast_id;
    private String character;
    @SerializedName(value = "profile_path")
    private String profilePicture;

    public PersonDetails(long id, String department, String name, int cast_id, String character, String profilePicture) {
        this.id = id;
        this.department = department;
        this.name = name;
        this.cast_id = cast_id;
        this.character = character;
        this.profilePicture = profilePicture;
    }

    public PersonDetails() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getCast_id() {
        return cast_id;
    }

    public void setCast_id(int cast_id) {
        this.cast_id = cast_id;
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
                "id='" + id + '\'' +
                ", department='" + department + '\'' +
                ", name='" + name + '\'' +
                ", character='" + character + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }


}
