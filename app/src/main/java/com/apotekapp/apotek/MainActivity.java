package com.apotekapp.apotek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apotekapp.apotek.Adapter.SliderAdapter;
import com.apotekapp.apotek.Model.SliderItem;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
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
    private MeowBottomNavigation bnv_Main;
    private Button btnSirup, btnTablet, btnOles, btnLainnya;

    TextView tv_Username;


    SliderView sliderView;
    private SliderAdapter adapter;

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
        tv_Username =   findViewById(R.id.tv_UserName);

        //  Set username pada navbar

//        tv_Username.setText("Hai, " + username);
        bnv_Main = findViewById(R.id.bnvMain);
        bnv_Main.add(new MeowBottomNavigation.Model(1,R.drawable.home));
        bnv_Main.add(new MeowBottomNavigation.Model(2,R.drawable.search));
        bnv_Main.add(new MeowBottomNavigation.Model(3,R.drawable.bookmark));
        bnv_Main.add(new MeowBottomNavigation.Model(4,R.drawable.person));

        // Inisialisasi button

        btnSirup        =   (Button) findViewById(R.id.btnSirup);
        btnTablet       =   (Button) findViewById(R.id.btnTablet);
        btnOles        =   (Button) findViewById(R.id.btnOles);
        btnLainnya      =   (Button) findViewById(R.id.btnLainnya);

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


        // Fungsi saat button di klik

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


        bnv_Main.show(1,true);

        bnv_Main.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        Intent intent = new Intent(MainActivity.this, ObatActivity.class);
                        startActivity(intent);
                        break;

                    case 2:
//                        replace(new SearchFragment());
                        break;

                    case 3:
//                        replace(new BookmarkFragment());
                        break;

                    case 4:
//                        replace(new ProfileFragment());
                        break;

                }
                return null;
            }
        });

    }

    public void renewItems() {
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription("Apoteker " + i);
            if (i % 2 == 0) {
                sliderItem.setImageUrl("http://192.168.11.19/apotek/slider/slider1.jpg");
            } else {
                sliderItem.setImageUrl("http://192.168.11.19/apotek/slider/slider2.jpg");
            }
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }
}