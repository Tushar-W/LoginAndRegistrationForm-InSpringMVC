package com.springmvc.model;

public class UserInformation {
    private String name;
    private String email;
    private String password;
    private String date;

    public UserInformation(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserInformation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
