package com.apotekapp.apotek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.apotekapp.apotek.Adapter.SliderAdapter;
import com.apotekapp.apotek.Model.SliderItem;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {



    public static final String TAG_USERNAME = "username";
    public static final String TAG_KODE_KONS = "nm_kons";
    String username, nm_kons;
    SharedPreferences sharedPreferences;
    private ImageButton btnSirup, btnTablet, btnOles, btnLainnya;
    private ImageButton btnLocation, btnCallCenter, btnSmsCenter;

    TextView tv_Username;


    SliderView sliderView;
    private SliderAdapter adapter;

    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Fungsi untuk menyembunyikan navbar

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        username = getIntent().getStringExtra(TAG_USERNAME);

        // Inisialisasi text username

        tv_Username =   findViewById(R.id.tv_UserName);

        // Set username pada navbar

        tv_Username.setText("Hai, " + username);

        // Inisialisasi ImageButton menu kategori obat

        btnSirup        =   (ImageButton) findViewById(R.id.btnSirup);
        btnTablet       =   (ImageButton) findViewById(R.id.btnTablet);
        btnOles         =   (ImageButton) findViewById(R.id.btnObatOles);
        btnLainnya      =   (ImageButton) findViewById(R.id.btnLainnya);

        // Inisialisasi ImageButton menu Utilitas

        btnLocation     =   (ImageButton) findViewById(R.id.btnLocation);
        btnCallCenter   =   (ImageButton) findViewById(R.id.btnCallCenter);
        btnSmsCenter    =   (ImageButton) findViewById(R.id.btnSmsCenter);

        // Inisialisasi ImageButton kategori obat

        sliderView = findViewById(R.id.imageSlider);

        adapter = new SliderAdapter(this);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });

        renewItems();

        // Inisialisasi bottom menu

        chipNavigationBar = findViewById(R.id.nav_menu);


        // Memanggil method bottommenu

        bottommenu();


        // Fungsi saat button menu kategori obat di klik

        btnSirup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ObatSirup.class);
                startActivity(intent);

            }
        });
        btnTablet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ObatTablet.class);
                startActivity(intent);

            }
        });
        btnOles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ObatOles.class);
                startActivity(intent);

            }
        });
        btnLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ObatLain.class);
                startActivity(intent);

            }
        });


        // Fungsi saat button menu utilitas di klik

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo: -6.269427,106.9119?q=-6.269427,106.9119");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        btnCallCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri number = Uri.parse("tel:0811111111");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);

            }
        });

        btnSmsCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String no = "08111111111";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", no, null)));

            }
        });



    }

    // Method untuk menambahkan image pada sliderImage

    public void renewItems() {
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 2; i++) {
            SliderItem sliderItem = new SliderItem();
//            sliderItem.setDescription("Apoteker " + i); // for slider description
            if (i % 2 == 0) {
                sliderItem.setImageUrl("http://192.168.11.19/apotek/slider/slider6.png");
            }
            else {
                sliderItem.setImageUrl("http://192.168.11.19/apotek/slider/slider4.png");
            }
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }

    // Method untuk untuk menu bottom bar

    private void bottommenu() {

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Activity activity =null;
                switch (i){
                    case R.id.nav_home:

                        break;
                    case R.id.nav_notification:
//
                        break;
                    case R.id.nav_cart:

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent i=new Intent(MainActivity.this, AccountActivity.class);
                                startActivity(i);
                            }
                        }, 500);

                        break;
                }
//
            }
        });

    }


    // Method pada saat menu utama data apoteker di klik

    public void data_Apoteker(View view) {

        Intent dataApoteker =   new Intent(MainActivity.this, ApotekerActivity.class);
        startActivity(dataApoteker);

    }

    // Method pada saat menu utama data obat di klik

    public void data_Obat(View view) {

        Intent dataObat =   new Intent(MainActivity.this, ObatActivity.class);
        startActivity(dataObat);

    }

//    //        Method jika tombol back ditekan
//
//    @Override
//    public void onBackPressed(){
//        Context context;
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(false);
//        builder.setMessage("Apakah Kamu Ingin Keluar? ");
//        builder.setPositiveButton("Iya ", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                startActivity(intent);
//                int pid = android.os.Process.myPid();
//                android.os.Process.killProcess(pid);
//            }
//        });
//
//        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        AlertDialog alert = builder.create();
//        alert.show();
//
//    }
}