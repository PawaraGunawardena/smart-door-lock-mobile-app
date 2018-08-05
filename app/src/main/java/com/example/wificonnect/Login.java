package com.example.wificonnect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private EditText username_edittext;
    private EditText password_edittext;

    private String user_name;
    private String password;

    private UserData userDatabaseConnectvity;
    private User user;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String username_saved_shared;
    String password_saved_shared;
    Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userDatabaseConnectvity = new UserData(this);


        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        username_saved_shared = sharedPreferences.getString("username_shared", "");
        password_saved_shared = sharedPreferences.getString("password_shared", "");
        if (username_saved_shared.length() > 0 && password_saved_shared.length() > 0) {
            Intent home = new Intent(this, Home.class);
            user = (User) userDatabaseConnectvity.getUser(username_saved_shared);
            home.putExtra("UserAccount", user);
            startActivity(home);
            finish();
        }
    }

    public void clicked_login_login(View view){

        username_edittext = (EditText) findViewById(R.id.editText_username_login);
        password_edittext = (EditText) findViewById(R.id.editText_password_login);

        user_name = username_edittext.getText().toString();
        password = password_edittext.getText().toString();

//        userDatabaseConnectvity = new UserData(this);
        user = userDatabaseConnectvity.getUser(user_name);

        if(user == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            builder.setMessage("Given user credentials are invalid")
                    .setNegativeButton("Retry", null)
                    .create()
                    .show();
        }else {

            if (user.getPassword().equals(password)) {
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username_shared", user_name);
                editor.putString("password_shared", password);
                editor.apply();


                Intent home = new Intent(this, Home.class);
                home.putExtra("UserAccount", user);
                startActivity(home);
                finish();
            } else {
                System.out.println("Wrong user");
                System.out.println(user_name + " " + password);

                System.out.println(user.getPassword() + " " + user.getUsername());

                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setMessage("Given user credentials are invalid")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
        }

    }

    public void clicked_create_account_login(View view){

        Intent signup = new Intent(this, Signup.class);
        startActivity(signup);
        finish();
    }


}
