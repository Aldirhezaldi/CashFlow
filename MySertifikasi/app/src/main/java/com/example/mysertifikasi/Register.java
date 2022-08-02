package com.example.mysertifikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    DatabaseHelper db;
    Button login, register;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        username = (EditText) findViewById(R.id.reg_username);
        password = (EditText) findViewById(R.id.reg_password);
        login = (Button)findViewById(R.id.reg_login);
        register = (Button) findViewById(R.id.reg_register);

        //login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Register.this, login.class);
                startActivity(loginIntent);
                finish();
            }
        });

        //register
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String strUsername = username.getText().toString();
//                String strPassword = password.getText().toString();
//                Boolean daftar = db.insertUser(strUsername, strPassword);
//                if (daftar == true) {
//                    Boolean updateSession = db.upgradeSession("ada", 1);
//                    if (updateSession == true) {
//                        Toast.makeText(getApplicationContext(), "Berhasil Register", Toast.LENGTH_SHORT).show();
//                        Intent mainIntent = new Intent(Register.this, login.class);
//                        startActivity(mainIntent);
//                        finish();
//                    }
//                }
//                else {
//                    Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
}