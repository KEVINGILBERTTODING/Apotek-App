package com.apotekapp.apotek;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ObatAdapter extends BaseAdapter {
    Activity activity;
    List<DataObat> items;
    private LayoutInflater inflater;


    public ObatAdapter(Activity activity, List<DataObat> items) {
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



        // Deklarasi TextView

        TextView idobat = (TextView) convertView.findViewById(R.id.id_obat);
        TextView kdobat = (TextView) convertView.findViewById(R.id.kd_obat);
        TextView nmobat = (TextView) convertView.findViewById(R.id.nm_obat);
        TextView satuan = (TextView) convertView.findViewById(R.id.satuan);
        TextView jumlah = (TextView) convertView.findViewById(R.id.jumlah);
        TextView expired = (TextView) convertView.findViewById(R.id.expired);

        DataObat dataObat = items.get(position);

        // Mengatur text dari value get and setter

        idobat.setText(dataObat.getId());
        kdobat.setText(dataObat.getKdobat());
        nmobat.setText(dataObat.getNmobat());
        satuan.setText(dataObat.getSatuan());
        jumlah.setText(dataObat.getJumlah());
        expired.setText(dataObat.getExpired());

        return convertView;
    }


}
