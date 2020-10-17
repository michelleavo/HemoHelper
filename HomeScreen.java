package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //route to history page
        Button history = (Button) findViewById(R.id.historyBtn);
        history.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeScreen.this, History.class));
            }
        });

        //route to contacts
        Button contacts = (Button) findViewById(R.id.contactsBtn);
        contacts.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeScreen.this, Contacts.class));
            }
        });

        //route to profile
        Button profile = (Button) findViewById(R.id.profileBtn);
        profile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeScreen.this, Profile.class));
            }
        });
    }
}