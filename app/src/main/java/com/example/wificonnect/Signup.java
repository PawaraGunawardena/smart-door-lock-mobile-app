package com.example.wificonnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    private EditText username_edittext;
    private EditText password_edittext;

    private String user_name;
    private String password;

    private UserData userDatabaseConnectvity;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void clicked_login_signup(View view){
        Intent login = new Intent(this, Login.class);
        startActivity(login);
        finish();
    }

    public void clicked_signup_signup(View view){

        username_edittext = (EditText) findViewById(R.id.editText_username_signup);
        password_edittext = (EditText) findViewById(R.id.editText_password_signup);

        user_name = username_edittext.getText().toString();
        password = password_edittext.getText().toString();

        user = new User(user_name, password);
        userDatabaseConnectvity = new UserData(this);
        userDatabaseConnectvity.addUser(user);

        Intent login = new Intent(this, Login.class);
        startActivity(login);
        finish();
    }
}
