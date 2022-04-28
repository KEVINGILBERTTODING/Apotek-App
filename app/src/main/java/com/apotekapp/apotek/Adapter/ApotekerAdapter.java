package com.apotekapp.apotek.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.apotekapp.apotek.ApotekerActivity;
import com.apotekapp.apotek.Model.DataApoteker;
import com.apotekapp.apotek.Model.DataObat;
import com.apotekapp.apotek.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ApotekerAdapter extends BaseAdapter {

    Activity    activity;
    private     List<DataApoteker> items;
    private     LayoutInflater inflater;
    private     ArrayList<DataApoteker> arrayList;

    public ApotekerAdapter(Activity activity, List<DataApoteker> items) {
        this.activity   = activity;
        this.items      = items;
        this.arrayList  = new ArrayList<>();
        this.arrayList.addAll(items);
    }



    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) convertView = inflater.inflate(R.layout.list_apoteker, null);



        // Deklarasi TextView

        TextView id                 = (TextView) convertView.findViewById(R.id.txt_id);
        TextView id_apoteker        = (TextView) convertView.findViewById(R.id.txt_id_apoteker);
        TextView nama_apoteker      = (TextView) convertView.findViewById(R.id.txt_nama_apoteker);
        TextView kota_apoteker      = (TextView) convertView.findViewById(R.id.txt_kota_apoteker);
        TextView nohp_apoteker      = (TextView) convertView.findViewById(R.id.txt_noHp_apoteker);
        TextView shift_apoteker     = (TextView) convertView.findViewById(R.id.txt_shift_apoteker);
        TextView alamat_apoteker    = (TextView) convertView.findViewById(R.id.txt_alamat_apoteker);

        DataApoteker dataApoteker = items.get(position);

        // Mengatur text dari value get and setter

        id.setText(dataApoteker.getId());
        id_apoteker.setText(dataApoteker.getId_apoteker());
        nama_apoteker.setText(dataApoteker.getNm_apoteker());
        kota_apoteker.setText(dataApoteker.getKota_apoteker());
        nohp_apoteker.setText(dataApoteker.getNoHp_apoteker());
        shift_apoteker.setText(dataApoteker.getShift());
        alamat_apoteker.setText(dataApoteker.getAlamat_apoteker());


        return convertView;
    }

    // Method untuk filter nama apoteker di searchview

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();

        if (charText.length() == 0) {
            items.addAll(arrayList);
        } else {
            for (DataApoteker an : arrayList) {
                if (an.getNm_apoteker().toLowerCase(Locale.getDefault()).contains(charText)) {
                    items.add(an);
                }

            }
        }
        notifyDataSetChanged();

    }

    public void updateSearchedList() {

        arrayList.addAll(items);
    }



}