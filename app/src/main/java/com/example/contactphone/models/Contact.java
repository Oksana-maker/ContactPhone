package com.example.contactphone.models;

import java.io.Serializable;

public class Contact implements Serializable {
    private String firstName;
    private String lastName;
    private String phone;
    private String imagePath;


    public Contact(String firstName, String lastName, String phone, String imagePath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.imagePath = imagePath;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
