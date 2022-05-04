package com.apotekapp.apotek.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apotekapp.apotek.Model.DataApoteker;
import com.apotekapp.apotek.Model.DataObat;
import com.apotekapp.apotek.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ObatAdapter extends BaseAdapter {
    Activity    activity;
    private     List<DataObat> items;
    private     LayoutInflater inflater;
    private     ArrayList<DataObat> arrayList;


    public ObatAdapter(Activity activity, List<DataObat> items) {
        this.activity = activity;
        this.items = items;
        this.arrayList  =  new ArrayList<>();
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

        if (convertView == null) convertView = inflater.inflate(R.layout.list_obat, null);



        // Deklarasi TextView

        TextView nmobat     = (TextView) convertView.findViewById(R.id.nm_obat);
        TextView jenis      = (TextView) convertView.findViewById(R.id.jenis);
        TextView expired    = (TextView) convertView.findViewById(R.id.expired);

        DataObat dataObat = items.get(position);

        // Mengatur text dari value get and setter

        nmobat.setText(dataObat.getNmobat());
        jenis.setText(dataObat.getJenis());
        expired.setText(dataObat.getExpired());

        return convertView;
    }

    // Method untuk filter nama apoteker di searchview

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();

        if (charText.length() == 0) {
            items.addAll(arrayList);
        } else {
            for (DataObat an : arrayList) {
                if (an.getNmobat().toLowerCase(Locale.getDefault()).contains(charText)) {
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
