package com.apotekapp.apotek;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class AccountActivity extends AppCompatActivity {


    String username, kode_konsumen;
    SharedPreferences sharedPreferences;
    public static final String TAG_USERNAME = "username";
    public static final String TAG_KODE_KONS = "nm_kons";
    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fungsi untuk menyembunyikan navbar

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // Fungsi untuk menyembunyikan status bar

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_account);

        // Menyimpan username ke dalam sharedpreferance

        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);


        // Inisialisasi bottom menu

        chipNavigationBar = findViewById(R.id.nav_menu);


        // Memanggil method bottommenu

        bottommenu();



    }

    // Method untuk untuk menu bottom bar

    private void bottommenu() {

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Activity activity = null;
                switch (i) {
                    case R.id.nav_home:

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent i = new Intent(AccountActivity.this, MainActivity.class);

                                //   Mengirim username menggunakan intent
                                i.putExtra(TAG_USERNAME, username);

                                startActivity(i);
                            }
                        }, 500);


                        break;
                    case R.id.nav_notification:
//
                        break;
                    case R.id.nav_cart:


                        break;
                }

            }
        });
    }

    // Method saat menu logout di klik

    public void logOut(View view) {

        // Handle item selection

        Intent intent = new Intent();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(LoginActivity.session_status, false);
        editor.putString(TAG_USERNAME, null);
        editor.commit();

        intent.setClass(AccountActivity.this, LoginActivity.class);
        finish();
        startActivity(intent);


    }

    public void update(View view) {

        Intent i = new Intent(AccountActivity.this, UpdateAccountActivity.class);

        //   Mengirim username menggunakan intent
        i.putExtra(TAG_USERNAME, username);

        startActivity(i);


    }
}