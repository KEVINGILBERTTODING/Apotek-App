package com.apotekapp.apotek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {



    public static final String TAG_USERNAME = "username";
    public static final String TAG_KODE_KONS = "nm_kons";
    String username, nm_kons;
    SharedPreferences sharedPreferences;
    private MeowBottomNavigation bnv_Main;

    TextView tv_Username;

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

        tv_Username.setText("Hai, " + username);
        bnv_Main = findViewById(R.id.bnvMain);
        bnv_Main.add(new MeowBottomNavigation.Model(1,R.drawable.home));
        bnv_Main.add(new MeowBottomNavigation.Model(2,R.drawable.search));
        bnv_Main.add(new MeowBottomNavigation.Model(3,R.drawable.bookmark));
        bnv_Main.add(new MeowBottomNavigation.Model(4,R.drawable.person));

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
}