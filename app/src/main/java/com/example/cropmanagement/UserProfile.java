package com.example.cropmanagement;

public class UserProfile {
    String name,password,email,district;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public UserProfile() {
    }

    public UserProfile(String name, String password, String email, String district) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.district = district;
    }
}
