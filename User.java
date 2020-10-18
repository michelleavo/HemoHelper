package com.example.myfirstapp;

import android.provider.ContactsContract;
import android.text.Editable;

public class User {
    private String name;
    private String phone;
    private EmergencyContact contact;
    private Provider provider;
    public static User user = new User();
    private double latitude;
    private double longitude;

    public User() {
        this.name = "name";
        this.phone = "phone";
        this.contact = new EmergencyContact();
        this.provider = new Provider();
    }


    public User(String name, String phone, EmergencyContact contact, Provider provider) {
        this.name = name;
        this.phone = phone;
        this.contact = contact;
        this.provider = provider;
    }

    public EmergencyContact getEmergencyContact() {
        return this.contact;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String Name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLatitude() {
        return this.latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
