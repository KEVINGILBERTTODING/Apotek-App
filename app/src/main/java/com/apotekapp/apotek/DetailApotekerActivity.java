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

public class DetailApotekerActivity extends AppCompatActivity {

    String xId, xNm, xKota, xNohp, xShift,xAlamat;

    TextView tv_idApoteker, tv_nmApoteker, tv_kotaApoteker, tv_nohpApoteker, tv_shiftApoteker, tv_alamatApoteker;
    private ImageButton btnBack;

    public static final String TAG_USERNAME = "username";
    String username;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_apoteker);

        // Menyimpan username ke dalam sharedpreferance

        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);



        // Fungsi untuk menyembunyikan navbar

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

//        // Fungsi untuk menyembunyikan status bar
//
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);



        // Deklarasikan TextView

        tv_idApoteker       =   (TextView) findViewById(R.id.det_idApoteker);
        tv_nmApoteker       =   (TextView) findViewById(R.id.det_nmApoteker);
        tv_kotaApoteker     =   (TextView) findViewById(R.id.det_kotaApoteker);
        tv_nohpApoteker     =   (TextView) findViewById(R.id.det_nohpApoteker);
        tv_shiftApoteker    =   (TextView) findViewById(R.id.det_shiftApoteker);
        tv_alamatApoteker   =   (TextView) findViewById(R.id.det_alamatApoteker);

        // Memanggil intent dan mengambil text yang telah dikirim dari ApotekerActivity

        Intent intent       =   getIntent();
        this.xId            =   intent.getStringExtra("id_apoteker");
        this.xNm            =   intent.getStringExtra("nama");
        this.xKota          =   intent.getStringExtra("kota");
        this.xNohp          =   intent.getStringExtra("no_hp");
        this.xShift         =   intent.getStringExtra("shift");
        this.xAlamat        =   intent.getStringExtra("alamat");

        // Settext menggunakan data yang telah di dapatkan dari intent

        tv_idApoteker.setText(xId);
        tv_nmApoteker.setText(xNm);
        tv_kotaApoteker.setText(xKota);
        tv_nohpApoteker.setText(xNohp);
        tv_shiftApoteker.setText(xShift);
        tv_alamatApoteker.setText(xAlamat);

        // Inisialisasi button back

        btnBack =   (ImageButton) findViewById(R.id.btnBack);

        // fungsi ketika button back di klik

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(DetailApotekerActivity.this, ApotekerActivity.class);

                //   Mengirim username menggunakan intent
                kembali.putExtra(TAG_USERNAME, username);

                startActivity(kembali);
            }
        });

    }
}