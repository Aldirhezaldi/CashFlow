package com.example.mysertifikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    RecyclerView recyclerView, recycleview2;
    DatabaseHelper helper;
    private ArrayList<DetailFLow> DetailArrayList;

    CustomAdapter customAdapter;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = findViewById(R.id.recycleview);
        back = findViewById(R.id.btn_back_detail);

        DetailArrayList = new ArrayList<>();
        helper = new DatabaseHelper(DetailActivity.this);

        DetailArrayList = helper.detail();

        customAdapter = new CustomAdapter(DetailArrayList, DetailActivity.this);
        recyclerView = findViewById(R.id.recycleview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_back();
            }
        });

    }

    private void btn_back() {
        Intent backIntent = new Intent(DetailActivity.this, MainActivity.class);
        startActivity(backIntent);
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
                Intent loginIntent = new Intent(DetailActivity.this, login.class);
                startActivity(loginIntent);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}