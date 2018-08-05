package com.example.wificonnect;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddDoor extends AppCompatActivity {

    User user;
    private EditText ssid_edittext;
    private EditText wifi_password_edittext;
    private EditText user_password_edittext;

    private String ssid;
    private String wifi_password;
    private String user_password;

    Door door;
    DoorData doorData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_door);

        ssid_edittext = (EditText) findViewById(R.id.ssid);
        wifi_password_edittext = (EditText) findViewById(R.id.wifi_password);
        user_password_edittext = (EditText) findViewById(R.id.door_user_password);



        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("UserAccount");

    }

    public void clicked_add_door_submit(View view){

        try {
            ssid = ssid_edittext.getText().toString();
            wifi_password = wifi_password_edittext.getText().toString();
            user_password = user_password_edittext.getText().toString();

            if(wifi_password.length() < 8) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddDoor.this);
                builder.setMessage("Wifi Password should be longer than 8 characters")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }else if(user_password.length() < 6){
                AlertDialog.Builder builder = new AlertDialog.Builder(AddDoor.this);
                builder.setMessage("User Password should be longer than 6 characters")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();

            }else if(ssid.length() == 0){
                AlertDialog.Builder builder = new AlertDialog.Builder(AddDoor.this);
                builder.setMessage("Wifi SSID cannot be empty")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();

            }else {
                door = new Door();
                door.setUsername(user.getUsername());
                door.setSsid(ssid);
                door.setWifi_password(wifi_password);
                door.setDoor_password(user_password);

                doorData = new DoorData(this);
                doorData.addDoor(door);


                Intent home = new Intent(this, Home.class);
                home.putExtra("UserAccount", user);
                startActivity(home);
                finish();
            }
        }catch(Exception e){
            System.out.println("ERROR -----------------------------------!");
            System.out.println(e.getMessage());
        }
    }


    public void clicked_cancel_ad(View view){



            Intent home = new Intent(this, Home.class);
            home.putExtra("UserAccount", user);
            startActivity(home);
            finish();

    }
}
