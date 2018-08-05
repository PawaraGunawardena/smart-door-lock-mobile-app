package com.example.wificonnect;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OpenDoor extends AppCompatActivity {

    User user;
    ArrayList<Door> user_doors;
    ArrayList<String> door_names;
    DoorData doorData;
    String selected_door_name;
    Door selected_door;
    Spinner spinner;

    String url_door_lock;

    ManageWifi manageWifi;
EditText editText_door_password ;
String door_password_entered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_door);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("UserAccount");
        doorData = new DoorData(this);
        System.out.println("USERNAME = " + user.getUsername());
        user_doors = doorData.getUserDoor(user.getUsername());
        door_names = get_door_names(user_doors);

        if(door_names.size()>0){
            spinner = (Spinner) findViewById(R.id.spinner_open_door);
            ArrayAdapter<String> adapter
                    = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, door_names);

            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selected_door_name = adapterView.getItemAtPosition(i).toString();
                    System.out.println(selected_door_name + " 1111111111111");
                    clicked__connect_door(view);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }else{

            CharSequence colors[] = new CharSequence[]{"Add Now", "Add Later"};

            AlertDialog.Builder builder = new AlertDialog.Builder(OpenDoor.this);
            builder.setTitle("You don't have any door to open. Add Door:");
            builder.setItems(colors, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.e("value is", "" + which);
                    switch (which) {
                        case 0:
                            Intent addDoor = new Intent(OpenDoor.this, AddDoor.class);
                            addDoor.putExtra("UserAccount", user);
                            startActivity(addDoor);
                            finish();
                            break;
                        case 1:
                            Intent home = new Intent(OpenDoor.this, Home.class);
                            home.putExtra("UserAccount", user);
                            startActivity(home);
                            finish();


                    }
                }
            });
            builder.show();





        }
    }

    private ArrayList<String> get_door_names(ArrayList<Door> user_doors_list) {

        ArrayList<String> door_names = new ArrayList<>();

        for (int i = 0; i < user_doors_list.size(); i++) {
            door_names.add(user_doors_list.get(i).getSsid());
        }

        return door_names;
    }

    public void clicked_open_door_od(View view) {

        for (int i = 0; i < user_doors.size(); i++) {
            if (user_doors.get(i).getSsid().equals(selected_door_name)) {
                selected_door = user_doors.get(i);
            }
        }
        System.out.println(selected_door.getSsid() + " 22 " + " 1111111111111");
        try {

            editText_door_password = findViewById(R.id.editText_door_password);
            door_password_entered = editText_door_password.getText().toString();

            if(selected_door.getDoor_password().equals(door_password_entered)){
                url_door_lock = "http://192.168.4.1/lock/auth";

            }
            else{
               url_door_lock = "http://192.168.4.1/lock/intruder";
            }
            request_sending(url_door_lock);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clicked_close_door_od(View view) {

        for (int i = 0; i < user_doors.size(); i++) {
            if (user_doors.get(i).getSsid().equals(selected_door_name)) {
                selected_door = user_doors.get(i);
            }
        }

        try {

            editText_door_password = findViewById(R.id.editText_door_password);
            door_password_entered = editText_door_password.getText().toString();

            if(selected_door.getDoor_password().equals(door_password_entered)){
                url_door_lock = "http://192.168.4.1/lock/lock";
            }
            else{
                url_door_lock = "http://192.168.4.1/lock/invalid";
            }
            request_sending(url_door_lock);


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void clicked_on_wifi(View view) {

        manageWifi = new ManageWifi(this);
        manageWifi.onWifi();


    }

    public void clicked_off_wifi(View view) {

        manageWifi = new ManageWifi(this);
        manageWifi.offWifi();


    }

    public void clicked__connect_door(View view) {

        for (int i = 0; i < user_doors.size(); i++) {
            if (user_doors.get(i).getSsid().equals(selected_door_name)) {
                selected_door = user_doors.get(i);
            }
        }
        System.out.println(selected_door.getSsid() + " 1111111111111");
        try {
            manageWifi = new ManageWifi(this);
            manageWifi.connectToDoor(selected_door.getSsid(), selected_door.getWifi_password());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void request_sending(String url_door_lock){


        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, url_door_lock, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Response Error");
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
                );

        queue.add(stringRequest);
    }

}
