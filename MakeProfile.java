package com.example.myfirstapp;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MakeProfile extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_profile);

        /*
        Button selectContact = (Button) findViewById(R.id.selectContact);
        selectContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);

                startActivityForResult(intent, 1);

            }
        });
         */

        Button continueBtn = (Button) findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get user name
                EditText userName = (EditText) findViewById(R.id.nameInput);
                String username = userName.getText().toString();
                //get phone number
                EditText phoneNumber = (EditText) findViewById(R.id.userPhone);
                Editable phone = phoneNumber.getText();

                //get emergency contact name
                EditText contactName = (EditText) findViewById(R.id.contactName);
                String eContactName = contactName.getText().toString();
                //get emergency contact number
                EditText eNumber = (EditText) findViewById(R.id.eContactPhone);
                Editable ePhone = eNumber.getText();
                EmergencyContact emergencyContact = new EmergencyContact(eContactName, ePhone);

                //get provider name
                EditText providerName = (EditText) findViewById(R.id.providerName);
                String pName = providerName.getText().toString();
                //get provider number
                EditText pNumber = (EditText) findViewById(R.id.providerNumber);
                Editable pPhone = pNumber.getText();
                Provider provider = new Provider(pName, pPhone);

                user = new User(username, phone, emergencyContact, provider);
                startActivity(new Intent(MakeProfile.this, HomeScreen.class));
            }
        });

    }

}