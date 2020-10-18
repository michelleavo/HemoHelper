package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Contacts extends AppCompatActivity {

    MakeProfile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        TextView ecName = (TextView) findViewById(R.id.eContactName);
        String tester = User.user.getEmergencyContact().getName();
        ecName.setText(tester);

        TextView ecNumber = (TextView) findViewById(R.id.eContactNumber);
        String tester2 = User.user.getEmergencyContact().getPhone();
        ecNumber.setText(tester2);

        TextView pName = (TextView) findViewById(R.id.pName);
        pName.setText(User.user.getProvider().getName());

        TextView pNumber = (TextView) findViewById(R.id.pNumber);
        pNumber.setText(User.user.getProvider().getNumber());

        //return home button
        Button goHome = (Button) findViewById(R.id.goHomeBtn2);
        goHome.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Contacts.this, HomeScreen.class));
            }
        });

        //edit button
        Button edit = (Button) findViewById(R.id.editBtn);
        edit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Contacts.this, MakeProfile.class));
            }
        });
    }

    public void initializeContacts(User user) {
        TextView ecName = (TextView) findViewById(R.id.eContactName);
        ecName.setText(user.getEmergencyContact().getName());

        TextView ecNumber = (TextView) findViewById(R.id.eContactPhone);
        ecNumber.setText(user.getEmergencyContact().getPhone().toString());

        TextView pName = (TextView) findViewById(R.id.pName);
        pName.setText(user.getProvider().getName());

        TextView pNumber = (TextView) findViewById(R.id.pNumber);
        pNumber.setText(user.getProvider().getNumber());
    }

}