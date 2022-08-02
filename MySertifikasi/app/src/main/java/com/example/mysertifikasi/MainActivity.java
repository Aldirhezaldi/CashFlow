package com.example.mysertifikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button logout;
    ImageButton pemasukan, pengaturan, pengeluaran, detail;
    DatabaseHelper helper;
    TextView txt_total_pemasukan, txt_total_pengeluaran;
    float tot_pemasukan;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        logout = (Button) findViewById(R.id.btn_logout);
        txt_total_pemasukan = (TextView)findViewById(R.id.txt_total_pemasukan);
        txt_total_pengeluaran = (TextView)findViewById(R.id.txt_total_pengeluaran);

        String resultPemasukan = "" + formatRupiah(Double.parseDouble(String.valueOf(db.totalPemasukan())));
        String resultPengeluaran = "" + formatRupiah(Double.parseDouble(String.valueOf(db.totalPengeluaran())));

        txt_total_pemasukan.setText("" + resultPemasukan);
        txt_total_pengeluaran.setText("" + resultPengeluaran);


//        Boolean checkSession = db.checkSession("ada");
//        if (checkSession == false) {
//            Intent loginIntent = new Intent(MainActivity.this, login.class);
//            startActivity(loginIntent);
//            finish();
//        }

        //image button pemasukan
        pemasukan = (ImageButton) findViewById(R.id.add_pemasukan);
        pemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pemasukanIntent = new Intent(MainActivity.this, PemasukanActivity.class);
                startActivity(pemasukanIntent);
                finish();
            }
        });

        //image button pengaturan
        pengaturan = (ImageButton) findViewById(R.id.pengaturan);
        pengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pengaturanIntent = new Intent(MainActivity.this, PengaturanActivity.class);
                startActivity(pengaturanIntent);
                finish();
            }
        });

        // image button pengeluaran
        pengeluaran = (ImageButton) findViewById(R.id.add_pengeluaran);
        pengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pengeluaranIntent = new Intent(MainActivity.this, PengeluaranActivity.class);
                startActivity(pengeluaranIntent);
                finish();
            }
        });

        // image button detail
        detail = (ImageButton) findViewById(R.id.detail);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(detailIntent);
                finish();
            }
        });
        helper = new DatabaseHelper(this);
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
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
                Intent loginIntent = new Intent(MainActivity.this, login.class);
                startActivity(loginIntent);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}