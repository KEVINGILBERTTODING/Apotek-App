package com.apotekapp.apotek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailObatActivity extends AppCompatActivity {

    private ImageButton btnBack;


    String namaObat, satuanObat, jumlah, jenis, deskripsi, expired;

    TextView tv_detNamaObat, tv_detSatuanObat, tv_detJumlah, tv_detJenis, tv_detDeskripsi, tv_detExpired;

    public static final String TAG_USERNAME = "username";
    String username;
    SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_obat);

        // Fungsi untuk menyembunyikan navbar

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // Fungsi untuk menyembunyikan status bar

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // Deklarasikan textview

        tv_detNamaObat      =   (TextView) findViewById(R.id.det_NmObat);
        tv_detSatuanObat    =   (TextView) findViewById(R.id.det_Satuan);
        tv_detJumlah        =   (TextView) findViewById(R.id.det_Jumlah);
        tv_detJenis         =   (TextView) findViewById(R.id.det_Jenis);
        tv_detDeskripsi     =   (TextView) findViewById(R.id.det_Desc);
        tv_detExpired       =   (TextView) findViewById(R.id.det_Expired);


        // Menyimpan username ke dalam sharedpreferance

        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);

        // Inisialisasi button back

        btnBack =   (ImageButton) findViewById(R.id.btnBack);

        // fungsi ketika button back di klik

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(DetailObatActivity.this, ObatActivity.class);

                //   Mengirim username menggunakan intent
                kembali.putExtra(TAG_USERNAME, username);

                startActivity(kembali);
            }
        });



        // Memanggil intent dan mengambil text yang telah dikirim dari ObatActvity
        // menggunakan intent

        Intent intent       =   getIntent();
        this.namaObat       =   intent.getStringExtra("nama_obat");
        this.satuanObat     =   intent.getStringExtra("satuan_obat");
        this.jumlah         =   intent.getStringExtra("jumlah");
        this.jenis          =   intent.getStringExtra("jenis");
        this.deskripsi      =   intent.getStringExtra("desc");
        this.expired        =   intent.getStringExtra("expired");


        // Settext menggunakan data yang telah di dapatkan dari intent

        tv_detNamaObat.setText(namaObat);
        tv_detSatuanObat.setText(satuanObat);
        tv_detJumlah.setText(jumlah);
        tv_detJenis.setText(jenis);
        tv_detDeskripsi.setText(deskripsi);
        tv_detExpired.setText(expired);


    }
}