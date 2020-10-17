package com.example.myfirstapp;

import android.provider.ContactsContract;
import android.text.Editable;

public class EmergencyContact {
    private String name;
    private Editable phone;

    public EmergencyContact(String name, Editable phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public Editable getPhone() {
        return this.phone;
    }
}
