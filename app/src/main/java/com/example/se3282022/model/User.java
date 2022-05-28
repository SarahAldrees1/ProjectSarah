package com.example.se3282022.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    public String id;
    String name;
    String sureName;
    String fatherName;
    String nationalID;
    String dob;
    String gender;

    public User() {
    }

    public User(String iD, String name, String sureName, String fatherName, String nationalID, String dob, String gender) {
        this.id = iD;
        this.name = name;
        this.sureName = sureName;
        this.fatherName = fatherName;
        this.nationalID = nationalID;
        this.dob = dob;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "iD='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sureName='" + sureName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", nationalID='" + nationalID + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(sureName, user.sureName) && Objects.equals(fatherName, user.fatherName) && Objects.equals(nationalID, user.nationalID) && Objects.equals(dob, user.dob) && Objects.equals(gender, user.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sureName, fatherName, nationalID, dob, gender);
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("iD", id);
        result.put("name", name);
        result.put("sureName", sureName);
        result.put("fatherName", fatherName);
        result.put("nationalID", nationalID);
        result.put("dob", dob);
        result.put("gender", gender);
        return result;
    }
}
