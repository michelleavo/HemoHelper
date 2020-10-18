package com.example.myfirstapp;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class HomeScreen extends AppCompatActivity {

    User user;
    MakeProfile profile;
    Contacts contacts;

    //    private final String DEVICE_NAME="MyBTBee";
    private final String DEVICE_ADDRESS="00:14:03:06:00:C4";
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");//Serial Port Service ID
    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    Button startButton;
    TextView textView;
    EditText editText;
    boolean deviceConnected=false;
    Thread thread;
    byte buffer[];
    int bufferPosition;
    boolean stopThread;

    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

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

        /*
        // call
        Button callButt = (Button) findViewById(R.id.button2);
        callButt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                makeCall(User.user.getEmergencyContact().getPhone());
            }
        });

        // text
        Button textButt = (Button) findViewById(R.id.button3);
        textButt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sendText(User.user.getEmergencyContact().getName(), User.user.getEmergencyContact().getPhone());
            }
        });
         */

        startButton = (Button) findViewById(R.id.buttonStart);
        textView = (TextView) findViewById(R.id.textView2);
        setUiEnabled(false);
    }

    public void setUiEnabled(boolean bool)
    {
        startButton.setEnabled(!bool);
        textView.setEnabled(bool);

    }

    public boolean BTinit()
    {
        boolean found=false;
        BluetoothAdapter bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(),"Device doesnt Support Bluetooth",Toast.LENGTH_SHORT).show();
        }
        if(!bluetoothAdapter.isEnabled())
        {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter, 0);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if(bondedDevices.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please Pair the Device first",Toast.LENGTH_SHORT).show();
        }
        else
        {
            for (BluetoothDevice iterator : bondedDevices)
            {
                if(iterator.getAddress().equals(DEVICE_ADDRESS))
                {
                    device=iterator;
                    found=true;
                    break;
                }
            }
        }
        return found;
    }

    public boolean BTconnect() {
        boolean connected = true;
        try {
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID);
            socket.connect();
        } catch (IOException e) {
            e.printStackTrace();
            connected = false;
        }
        if (connected) {
            try {
                outputStream = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return connected;
    }

    public void onClickStart(View view) {
        if(BTinit())
        {
            if(BTconnect())
            {
                setUiEnabled(true);
                deviceConnected=true;
                beginListenForData();
            }
        }
    }

    void beginListenForData()
    {
        final Handler handler = new Handler();

        stopThread = false;
        buffer = new byte[1024];
        Thread thread  = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopThread)
                {
                    try {
                        if (inputStream.available() > 0) {
                            int num = inputStream.read();

                            if (num <= 85 && flag == 0) {
                                makeCall(User.user.getEmergencyContact().getPhone());
                                sendText(User.user.getEmergencyContact().getName(), User.user.getEmergencyContact().getPhone());
                                flag++;
                            } else {
                                final String pr = "" + num;
                                if (num > 0) {
                                    handler.post(new Runnable() {
                                        public void run()
                                        {
                                            textView.setText(pr);
                                        }
                                    });

                                }
                            }
                        }
                    } catch (IOException e) {
                        stopThread = true;
                    }
                }
            }
        });

        thread.start();
    }

    public void makeCall(String target) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:+1" + target));

        if (ActivityCompat.checkSelfPermission(HomeScreen.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        startActivity(callIntent);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setSpeakerphoneOn(true);

    }

    public void sendText(String name, String number) {
        String destinationAddress = "smsto:+1" + number;

        String smsMessage = "Hello " + name + ".\nThis is HemoHelper on behalf of " + User.user.getName()
                + ". We have recorded an incident at (" + User.user.getLatitude() + ", " +
                User.user.getLongitude() + ") and contacted " + User.user.getName() + "'s primary healthcare " +
                "provider.";

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(destinationAddress, null, smsMessage, null, null);
    }
}