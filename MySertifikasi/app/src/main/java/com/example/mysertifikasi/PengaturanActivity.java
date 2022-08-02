package com.example.mysertifikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PengaturanActivity extends AppCompatActivity {
    Button btn_back_pengaturan,btn_simpan_pengaturan;
    protected Cursor cursor;
    DatabaseHelper db;
    EditText pass_ini, pass_baru;
    String passbaru;
    private static final String MY_PREFS_NAME = "MyPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);

        btn_back_pengaturan = (Button)findViewById(R.id.btn_back_pengaturan);
        btn_simpan_pengaturan = (Button)findViewById(R.id.btn_simpan_pengaturan);
        pass_ini = (EditText)findViewById(R.id.pass_ini);
        pass_baru = (EditText)findViewById(R.id.pass_baru);

        SharedPreferences preferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String password = preferences.getString("password", "12345678");

//        db = new DatabaseHelper(this);
//
//        passbaru = getIntent().getStringExtra("pass baru");
//
//        pass_ini.setText("" + db.showPassword());
//        pass_baru.setText(passbaru);


        btn_back_pengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backPengaturanIntent = new Intent(PengaturanActivity.this, MainActivity.class);
                startActivity(backPengaturanIntent);
                finish();
            }
        });

        btn_simpan_pengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pass_ini.getText().toString().equals(password)){
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("username", "aldi");
                    editor.putString("password", pass_baru.getText().toString());
                    editor.apply();
                    Toast.makeText(getBaseContext(), "Password berhasil diubah!", Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent(PengaturanActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }else {
                    Toast.makeText(getBaseContext(), "Password tidak sama!", Toast.LENGTH_SHORT).show();
                }
//                db.updatePassword(pass_baru.getText().toString(), passbaru);
//                Toast.makeText(PengaturanActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();
//
//                // launching our main activity.
//                Intent i = new Intent(PengaturanActivity.this, MainActivity.class);
//                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_logout:
                Toast.makeText(getApplicationContext(), "Berhasil Logout", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(PengaturanActivity.this, login.class);
                startActivity(loginIntent);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}