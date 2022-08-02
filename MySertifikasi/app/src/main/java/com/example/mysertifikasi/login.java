package com.example.mysertifikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class login extends AppCompatActivity {
    private static final String MY_PREFS_NAME = "MyPreferences";
    DatabaseHelper db;
    Button btn_login, register;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        btn_login = (Button)findViewById(R.id.btn_login);

        //register
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent registerIntent =  new Intent(login.this, Register.class);
//                startActivity(registerIntent);
//                finish();
//            }
//        });

        //login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();

                SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String prefUser = preferences.getString("username", "aldi");
                String prefPass = preferences.getString("password", "12345678");

                if (strUsername.equals(prefUser) && strPassword.equals(prefPass)){
                    startActivity(new Intent(login.this, MainActivity.class));
                } else {
                    Toast.makeText(getBaseContext(), "Username atau password salah!", Toast.LENGTH_SHORT).show();
                }
//                String strUsername = username.getText().toString();
//                String strPassword = password.getText().toString();
//                Boolean masuk = db.checkLogin(strUsername, strPassword);
//                if (masuk == true) {
//                    Boolean updateSession = db.upgradeSession("ada", 1);
//                    if (updateSession == true) {
//                        Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
//                        Intent mainIntent = new Intent(login.this, MainActivity.class);
//                        startActivity(mainIntent);
//                        finish();
//                    }
//                }
//                else {
//                    Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}