package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button goHome = (Button) findViewById(R.id.goHomeBtn3);
        goHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Profile.this, HomeScreen.class));
            }
        });

        //edit button
        Button edit = (Button) findViewById(R.id.editBtn2);
        edit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Profile.this, MakeProfile.class));
            }
        });
    }
}