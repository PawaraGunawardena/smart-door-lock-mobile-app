package com.example.wificonnect;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Home extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("UserAccount");


    }

    public void clicked_add_door_home(View view){
        Intent addDoor = new Intent(this, AddDoor.class);

        addDoor.putExtra("UserAccount", user);
        startActivity(addDoor);
        finish();
    }

    public void clicked_open_door_home(View view){
        Intent addDoor = new Intent(this, OpenDoor.class);

        addDoor.putExtra("UserAccount", user);
        startActivity(addDoor);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SharedPreferences preferences =getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();

            Intent login = new Intent(Home.this, Login.class);
            startActivity(login);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
