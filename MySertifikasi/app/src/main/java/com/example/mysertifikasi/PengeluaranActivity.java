package com.example.mysertifikasi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PengeluaranActivity extends AppCompatActivity {
    DatabaseHelper helper;
    EditText add_date, add_nominal, add_keterangan;
    long id;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    Button button_back, button_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengeluaran);

        helper = new DatabaseHelper(this);

        id = getIntent().getLongExtra(DatabaseHelper.id_pengeluaran, 0);

        add_nominal = (EditText)findViewById(R.id.nominal_pengeluaran);
        add_keterangan = (EditText)findViewById(R.id.keterangan_pengeluaran);
        add_date = (EditText)findViewById(R.id.date_pengeluaran);
        button_back = (Button)findViewById(R.id.btn_back_pengeluaran);
        button_simpan = (Button)findViewById(R.id.btn_simpan_pengeluaran);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        add_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_back();
            }
        });

        button_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_simpan();
            }
        });
    }

    private void btn_simpan() {
        String strNominal = add_nominal.getText().toString().trim();
        String strKeterangan = add_keterangan.getText().toString().trim();
        String strDate = add_date.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.nominal_pengeluaran, strNominal);
        values.put(DatabaseHelper.keterangan_pengeluaran, strKeterangan);
        values.put(DatabaseHelper.tanggal_pg, strDate);

        if (strNominal.equals("") || strKeterangan.equals("") || strDate.equals("")){
            Toast.makeText(PengeluaranActivity.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {
            helper.insertPengeluaran(values);
            Toast.makeText(PengeluaranActivity.this, "Data Pemasukan Tersimpan", Toast.LENGTH_SHORT);
            Intent mainIntent = new Intent(PengeluaranActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }

    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                add_date.setText(dateFormatter.format(newDate.getTime()));
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void btn_back() {
        Intent backPengeluaranIntent = new Intent(PengeluaranActivity.this, MainActivity.class);
        startActivity(backPengeluaranIntent);
        finish();
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
                Intent loginIntent = new Intent(PengeluaranActivity.this, login.class);
                startActivity(loginIntent);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}