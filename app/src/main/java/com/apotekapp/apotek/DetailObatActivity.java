package com.apotekapp.apotek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailObatActivity extends AppCompatActivity {

    private ImageButton btnBack;


    String namaObat, satuanObat, jumlah, deskripsi, expired;

    TextView tv_detNamaObat, tv_detSatuanObat, tv_detJumlah, tv_detDeskripsi, tv_detExpired;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_obat);

        // Deklarasikan textview

        tv_detNamaObat      =   (TextView) findViewById(R.id.det_NmObat);
        tv_detSatuanObat    =   (TextView) findViewById(R.id.det_Satuan);
        tv_detJumlah        =   (TextView) findViewById(R.id.det_Jumlah);
        tv_detDeskripsi     =   (TextView) findViewById(R.id.det_Desc);
        tv_detExpired       =   (TextView) findViewById(R.id.det_Expired);

        // Inisialisasi button back

        btnBack =   (ImageButton) findViewById(R.id.btnBack);

        // fungsi ketika button back di klik

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(DetailObatActivity.this, ObatActivity.class);
                startActivity(kembali);
            }
        });



        // Memanggil intent dan mengambil text yang telah dikirim dari ObatActvity
        // menggunakan intent

        Intent intent       =   getIntent();
        this.namaObat       =   intent.getStringExtra("nama_obat");
        this.satuanObat     =   intent.getStringExtra("satuan_obat");
        this.jumlah         =   intent.getStringExtra("jumlah");
        this.deskripsi      =   intent.getStringExtra("desc");
        this.expired        =   intent.getStringExtra("expired");


        // Settext menggunakan data yang telah di dapatkan dari intent

        tv_detNamaObat.setText(namaObat);
        tv_detSatuanObat.setText(satuanObat);
        tv_detJumlah.setText(jumlah);
        tv_detDeskripsi.setText(deskripsi);
        tv_detExpired.setText(expired);


    }
}