package com.example.myfirstapp;

import android.provider.ContactsContract;
import android.text.Editable;

public class User {
    private String name;
    private Editable phone;
    private EmergencyContact contact;
    private Provider provider;

    public User(String name, Editable phone, EmergencyContact contact, Provider provider) {
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

    public Editable getPhone() {
        return this.phone;
    }
    public void setPhone(Editable phone) {
        this.phone = phone;
    }
}
