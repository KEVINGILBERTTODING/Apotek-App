package com.apotek.ObatAPP;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.apotek.ObatAPP.utill.ServerAPI;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CreateDataObat extends MainActivity {


    ProgressDialog progressDialog;
    String id, tkode_obat, tnama_obat, tsatuan_obat, tjumlah_obat, texpired_date;
    EditText edtKodeObat, edtNamaObat, edtSatuanObat, edtJumlahObat, edtExpiredObat;

    List<Data> itemList = new ArrayList<Data>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data_obat);

        // Definisikan EditText

        edtKodeObat = (EditText) findViewById(R.id.edt_KodeObat);
        edtNamaObat = (EditText) findViewById(R.id.edt_NamaObat);
        edtSatuanObat = (EditText) findViewById(R.id.edt_SatuanObat);
        edtJumlahObat = (EditText) findViewById(R.id.edt_JumlahObat);
        edtExpiredObat = (EditText) findViewById(R.id.edt_ExpiredDate);

    }

    // Method pada saat button save di klik

    public void insertObat(View view) {
      insertdataObat();
    }

    // Method untuk menginput data obat

    private void insertdataObat() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ServerAPI.URL_Create_Obat,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(CreateDataObat.this, "Berhasil Menambahkan Obat Baru", Toast.LENGTH_LONG).show();
                        Intent intent   =   new Intent(CreateDataObat.this, MainActivity.class);
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

                tkode_obat      =   edtKodeObat.getText().toString();
                tnama_obat      =   edtNamaObat.getText().toString();
                tsatuan_obat    =   edtSatuanObat.getText().toString();
                tjumlah_obat    =   edtJumlahObat.getText().toString();
                texpired_date   =   edtExpiredObat.getText().toString();


                // Menyimpan value ke dalam hashmap

                params.put("kode_obat", tkode_obat);
                params.put("nama_obat", tnama_obat);
                params.put("satuan_obat", tsatuan_obat);
                params.put("jumlah", tjumlah_obat);
                params.put("expired", texpired_date);
                return params;

            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);


    }




}