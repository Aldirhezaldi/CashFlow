package com.example.mysertifikasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterPengeluaran extends RecyclerView.Adapter {

    Context context;
    private ArrayList id_pengeluaran, nominal_pengeluaran, keterangan_pengeluaran, tanggal_pengeluaran;
    private ArrayList id_pemasukan, nominal_pemasukan, keterangan_pemasukan, tanggal_pemasukan;
    CustomAdapterPengeluaran(Context context, ArrayList id_pengeluaran, ArrayList nominal_pengeluaran,
                  ArrayList keterangan_pengeluaran, ArrayList tanggal_pengeluaran,
                             ArrayList id_pemasukan, ArrayList nominal_pemasukan,
                             ArrayList keterangan_pemasukan, ArrayList tanggal_pemasukan
    ) {
        this.context = context;
        this.id_pengeluaran = id_pengeluaran;
        this.nominal_pengeluaran = nominal_pengeluaran;
        this.keterangan_pengeluaran = keterangan_pengeluaran;
        this.tanggal_pengeluaran = tanggal_pengeluaran;

        this.id_pemasukan = id_pemasukan;
        this.nominal_pemasukan = nominal_pemasukan;
        this.keterangan_pemasukan = keterangan_pemasukan;
        this.tanggal_pemasukan = tanggal_pemasukan;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
