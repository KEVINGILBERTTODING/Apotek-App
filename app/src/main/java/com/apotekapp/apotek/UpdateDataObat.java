package com.apotekapp.apotek;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
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

    EditText updtKodeObat, updtNamaObat, updtSatuanObat, updtJumlahObat, updtDeksripsiObat, updtExpiredDate;

    String idObat, kodeObat, namaObat, satuanObat, jumlahObat, deskripsiObat, expiredDate;

    //String untuk hashmap
     String xid, xkodeobat, xnamaobat, xsatuanobat, xjumlahobat, xdeskripsiobat, xexpiredate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_obat);

        // Deklarasikan textview

        updtKodeObat        =   findViewById(R.id.updt_KodeObat);
        updtNamaObat        =   findViewById(R.id.updt_NamaObat);
        updtSatuanObat      =   findViewById(R.id.updt_SatuanObat);
        updtJumlahObat      =   findViewById(R.id.updt_JumlahObat);
        updtDeksripsiObat   =   findViewById(R.id.updt_Desc);
        updtExpiredDate     =   findViewById(R.id.updt_ExpiredDate);

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

        // Membuat objek myCalendar

        myCalendar = Calendar.getInstance();

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

        // Menampilkan datepicker / calendar saat edittext expired date di klik

        updtExpiredDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(UpdateDataObat.this, expDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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


                // Mengambil value dari edittext

                xkodeobat       =   updtKodeObat.getText().toString();
                xnamaobat       =   updtNamaObat.getText().toString();
                xsatuanobat     =   updtSatuanObat.getText().toString();
                xjumlahobat     =   updtJumlahObat.getText().toString();
                xdeskripsiobat  =   updtDeksripsiObat.getText().toString();
                xexpiredate     =   updtExpiredDate.getText().toString();


                // Menyimpan value ke dalam hashmap lalu mengirimnya


                params.put("id_obat", idObat);
                params.put("kode_obat", xkodeobat);
                params.put("nama_obat", xnamaobat);
                params.put("satuan_obat", xsatuanobat);
                params.put("jumlah_obat", xjumlahobat);
                params.put("desc_obat", xdeskripsiobat);
                params.put("expired_date", xexpiredate);
                return params;

            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);


    }


}