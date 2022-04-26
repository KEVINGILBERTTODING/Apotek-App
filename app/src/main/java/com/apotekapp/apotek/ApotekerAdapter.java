package com.apotekapp.apotek;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ApotekerAdapter  extends RecyclerView.Adapter<ApotekerAdapter.HolderData> {

    private List<DataApoteker> mItems;
    private Context context;


    public ApotekerAdapter(Context context, List<DataApoteker> items) {
        this.mItems     =  items;
        this.context    =  context;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()). inflate(R.layout.list_apoteker, parent, false);
        HolderData holderData   =   new HolderData(layout);
        return holderData;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {

        DataApoteker dataApoteker   =   mItems.get(position);
        holder.tv_id.setText(dataApoteker.getId());
        holder.tv_idptk.setText(dataApoteker.getId_apoteker());
        holder.tv_nmptk.setText(dataApoteker.getNm_apoteker());
        holder.tv_kotaptk.setText(dataApoteker.getKota_apoteker());
        holder.tv_nohpptk.setText(dataApoteker.getNoHp_apoteker());
        holder.tv_shiftptk.setText(dataApoteker.getShift());
        holder.tv_alamatptk.setText(dataApoteker.getAlamat_apoteker());

        holder.dataApoteker = dataApoteker;

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {

        DataApoteker dataApoteker;
        TextView tv_id, tv_idptk, tv_nmptk, tv_kotaptk, tv_nohpptk, tv_shiftptk, tv_alamatptk;

        public HolderData(View view) {
            super(view);

            tv_id   =   (TextView) view.findViewById(R.id.txt_id);
            tv_idptk   =   (TextView) view.findViewById(R.id.txt_id_apoteker);
            tv_nmptk   =   (TextView) view.findViewById(R.id.txt_nama_apoteker);
            tv_kotaptk   =   (TextView) view.findViewById(R.id.txt_kota_apoteker);
            tv_nohpptk  =   (TextView) view.findViewById(R.id.txt_noHp_apoteker);
            tv_shiftptk   =   (TextView) view.findViewById(R.id.txt_shift_apoteker);
            tv_alamatptk  =   (TextView) view.findViewById(R.id.txt_alamat_apoteker);



        }
    }
}
