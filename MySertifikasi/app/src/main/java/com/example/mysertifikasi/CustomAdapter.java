package com.example.mysertifikasi;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    DatabaseHelper helper;
    private ArrayList<DetailFLow> DetailArrayList;

    CustomAdapter(ArrayList<DetailFLow> DetailArrayList, Context context
                  ) {
        this.context = context;
        this.DetailArrayList = DetailArrayList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.data_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DetailFLow model = DetailArrayList.get(position);
        if (model.getPanah() == 0) {
            holder.txt_id_pemasukan.setText(model.getId_detail());
            holder.txt_nominal_pemasukan.setText(String.valueOf(model.getNominal()));
            holder.txt_keterangan_pemasukan.setText(model.getKeterangan());
            holder.txt_tanggal_pemasukan.setText(model.getTanggal());
            holder.simbol_pemasukan.setText(model.getStatus());
            holder.panah.setImageResource(R.drawable.ic_baseline_arrow_back_24);
        } else {
            holder.txt_id_pemasukan.setText(model.getId_detail());
            holder.txt_nominal_pemasukan.setText(String.valueOf(model.getNominal()));
            holder.txt_keterangan_pemasukan.setText(model.getKeterangan());
            holder.txt_tanggal_pemasukan.setText(model.getTanggal());
            holder.simbol_pemasukan.setText(model.getStatus());
            holder.panah.setImageResource(R.drawable.ic_baseline_arrow_forward_24);
        }
    }

    @Override
    public int getItemCount() {
        return DetailArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_id_pemasukan, txt_nominal_pemasukan, txt_keterangan_pemasukan, txt_tanggal_pemasukan, simbol_pemasukan;
        ImageView panah;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id_pemasukan = itemView.findViewById(R.id.txt_id_pemasukan);
            txt_id_pemasukan.setVisibility(View.INVISIBLE);
            txt_nominal_pemasukan = itemView.findViewById(R.id.txt_nominal_pemasukan);
            txt_keterangan_pemasukan = itemView.findViewById(R.id.txt_keterangan_pemasukan);
            txt_tanggal_pemasukan = itemView.findViewById(R.id.txt_tanggal_pemasukan);
            simbol_pemasukan = itemView.findViewById(R.id.simbol_pemasukan);
            panah = itemView.findViewById(R.id.panah);
        }
    }
}
