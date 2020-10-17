package com.example.myfirstapp;

import android.provider.ContactsContract;
import android.text.Editable;

public class Provider {
    private String name;
    private Editable phone;

    public Provider(String name, Editable number) {
        this.name = name;
        this.phone = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Editable getNumber() {
        return this.phone;
    }

    public void setNumber(Editable number) {
        this.phone = number;
    }
}
