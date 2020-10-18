package com.example.myfirstapp;

import android.provider.ContactsContract;
import android.text.Editable;

public class EmergencyContact {
    private String name;
    private String phone;

    public EmergencyContact() {
        this.name = "name";
        this.phone = "test";
    }

    public EmergencyContact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
