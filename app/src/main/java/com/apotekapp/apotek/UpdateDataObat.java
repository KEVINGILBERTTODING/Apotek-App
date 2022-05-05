package com.apotekapp.apotek;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.DatePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.apotekapp.apotek.Utill.ServerAPI;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UpdateDataObat extends ObatActivity {


    ProgressDialog progressDialog;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener expDate;
    private ImageButton btnBack;

    private RadioButton rbSirup, rbTablet, rbObatOles, rbLainnya;

    private RadioGroup rgJenisObat;

    EditText updtKodeObat, updtNamaObat, updtSatuanObat, updtJumlahObat, updtDeksripsiObat, updtExpiredDate;

    String idObat, kodeObat, namaObat, satuanObat, jumlahObat, deskripsiObat, expiredDate;

    //String untuk hashmap
     String xid, xkodeobat, xnamaobat, xsatuanobat, xjumlahobat, xjenisobat,  xdeskripsiobat, xexpiredate;

    public static final String TAG_USERNAME = "username";
    String username;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_obat);

        // Deklarasikan textview

        updtKodeObat        =   findViewById(R.id.updt_KodeObat);
        updtNamaObat        =   findViewById(R.id.updt_NamaObat);
        updtSatuanObat      =   findViewById(R.id.updt_SatuanObat);
        updtJumlahObat      =   findViewById(R.id.updt_JumlahObat);
        updtDeksripsiObat   =   findViewById(R.id.updt_Desc);
        updtExpiredDate     =   findViewById(R.id.updt_ExpiredDate);

        // Inisialisasi Radio Button Jenis Obat

        rbSirup             =   (RadioButton) findViewById(R.id.rbSirup);
        rbTablet            =   (RadioButton) findViewById(R.id.rbTablet);
        rbObatOles          =   (RadioButton) findViewById(R.id.rbObatOles);
        rbLainnya           =   (RadioButton) findViewById(R.id.rbLainnya);


        // Menyimpan username ke dalam sharedpreferance

        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);



        // Memanggil intent dan mengambil text yang telah dikirim dari MainActivity
        // menggunakan intent


        Intent intent       =   getIntent();
        this.idObat         =   intent.getStringExtra("idobat");
        this.kodeObat       =   intent.getStringExtra("kodeobat");
        this.namaObat       =   intent.getStringExtra("namaobat");
        this.satuanObat     =   intent.getStringExtra("satuanobat");
        this.jumlahObat     =   intent.getStringExtra("jumlahobat");
        this.deskripsiObat  =   intent.getStringExtra("descobat");
        this.expiredDate    =   intent.getStringExtra("expireddate");

        // Mengatur agar tiap textview memiliki text data yang dikirim dari MainActivity

        updtKodeObat.setText(kodeObat);
        updtNamaObat.setText(namaObat);
        updtSatuanObat.setText(satuanObat);
        updtJumlahObat.setText(jumlahObat);
        updtDeksripsiObat.setText(deskripsiObat);
        updtExpiredDate.setText(expiredDate);


        // Inisialisasi button back

        btnBack =   (ImageButton) findViewById(R.id.btnBack);

        // fungsi ketika button back di klik

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(UpdateDataObat.this, ObatActivity.class);

                //   Mengirim username menggunakan intent
                kembali.putExtra(TAG_USERNAME, username);

                startActivity(kembali);
            }
        });



        // Membuat objek myCalendar

        myCalendar = Calendar.getInstance();

        // Inisialisasi Radio Group Shift

        rgJenisObat = (RadioGroup) findViewById(R.id.rg_JenisObat);



        // Memanggil method untuk mengambil value radio button

        selectedRadioJenisObat();


        // Funcition saat imgCalendar di klik

        updtExpiredDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(UpdateDataObat.this, expDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Fungsi saat calendar/ datepicker di klik

        expDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateTanggal();
            }
        };

    }

    private void updateTanggal() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        updtExpiredDate.setText(sdf.format(myCalendar.getTime()));
    }

    // Method pada saat button update di klik

    public void UpdateObat(View view) {
        updateDataObat();
    }


    // Method untuk update data obat

    private void updateDataObat() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ServerAPI.URL_Update_Obat,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(UpdateDataObat.this, "Berhasil memperbarui Data Obat", Toast.LENGTH_LONG).show();
                        Intent intent   =   new Intent(UpdateDataObat.this, ObatActivity.class);
                        startActivity(intent);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();

            }
        }) {

            @Override
            protected Map<String, String> getParams()  {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                int checkedButtonId = rgJenisObat.getCheckedRadioButtonId();

                RadioButton checkedButton = findViewById(checkedButtonId);




                // Mengambil value dari edittext

                xkodeobat       =   updtKodeObat.getText().toString();
                xnamaobat       =   updtNamaObat.getText().toString();
                xsatuanobat     =   updtSatuanObat.getText().toString();
                xjumlahobat     =   updtJumlahObat.getText().toString();
                xjenisobat      =   checkedButton.getText().toString();
                xdeskripsiobat  =   updtDeksripsiObat.getText().toString();
                xexpiredate     =   updtExpiredDate.getText().toString();


                // Menyimpan value ke dalam hashmap lalu mengirimnya


                params.put("id_obat", idObat);
                params.put("kode_obat", xkodeobat);
                params.put("nama_obat", xnamaobat);
                params.put("satuan_obat", xsatuanobat);
                params.put("jumlah_obat", xjumlahobat);
                params.put("jenis_obat", xjenisobat);
                params.put("desc_obat", xdeskripsiobat);
                params.put("expired_date", xexpiredate);
                return params;

            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);


    }

    // Method untuk set selected radio button shift

    private void selectedRadioJenisObat () {

        String selectedRadioTxt = getIntent().getExtras().getString("jenis");

        if (selectedRadioTxt.equals(rbSirup.getText()))
            rbSirup.setChecked(true);

        if (selectedRadioTxt.equals(rbTablet.getText()))
            rbTablet.setChecked(true);

        if (selectedRadioTxt.equals(rbObatOles.getText()))
            rbObatOles.setChecked(true);

        if (selectedRadioTxt.equals(rbLainnya.getText()))
            rbLainnya.setChecked(true);
    }


}