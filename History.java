package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Button goHome = (Button) findViewById(R.id.goHomeBtn);
        goHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(History.this, HomeScreen.class));
            }
        });
    }
}