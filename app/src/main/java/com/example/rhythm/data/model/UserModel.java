package com.example.rhythm.data.model;

public class UserModel {

    String name , email , password , id , dateOfBirth , gender , phoneNumber ;

    public UserModel(String name, String email, String id, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public UserModel(String name, String email, String password, String id, String dateOfBirth, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
