package com.example.ObatAPP;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class BrgAdapter extends BaseAdapter {
    Activity activity;
    List<Data> items;
    private LayoutInflater inflater;

    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    public BrgAdapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
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

        if (convertView == null) convertView = inflater.inflate(R.layout.list, null);


        TextView kdobat = (TextView) convertView.findViewById(R.id.kd_obat);
        TextView nmobat = (TextView) convertView.findViewById(R.id.nm_obat);
        TextView satuan = (TextView) convertView.findViewById(R.id.satuan);
        TextView jumlah = (TextView) convertView.findViewById(R.id.jumlah);
        TextView expired = (TextView) convertView.findViewById(R.id.expired);

        Data data = items.get(position);

        // Convert ke format rupiah


        kdobat.setText(data.getKdobat());
        nmobat.setText(data.getNmobat());
        satuan.setText(data.getSatuan());
        jumlah.setText(data.getJumlah());
        expired.setText(data.getExpired());

        return convertView;
    }


}
