package com.example.myfirstapp;

import android.provider.ContactsContract;
import android.text.Editable;

public class Provider {
    private String name;
    private String phone;

    public Provider() {
        this.name = "name";
        this.phone = "phone";
    }

    public Provider(String name, String number) {
        this.name = name;
        this.phone = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return this.phone;
    }

    public void setNumber(String number) {
        this.phone = number;
    }
}
