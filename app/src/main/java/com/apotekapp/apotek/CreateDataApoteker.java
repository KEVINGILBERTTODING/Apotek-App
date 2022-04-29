package com.apotekapp.apotek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apotekapp.apotek.Model.DataApoteker;
import com.apotekapp.apotek.Utill.ServerAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateDataApoteker extends ApotekerActivity {

    String idApoteker, nmApoteker, kotaApoteker, nohpApoteker, shiftApoteker, alamatApoteker;
    EditText edtIdApoteker, edtNmApoteker, edtKotaApoteker, edtNohpApoteker, edtAlamatApoteker;

    private ImageButton btnBack;
    private RadioGroup rgShift;


    List<DataApoteker> itemList =   new ArrayList<DataApoteker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data_apoteker);

        // Definisikan EditText

        edtIdApoteker       =   findViewById(R.id.edt_idApoteker);
        edtNmApoteker       =   findViewById(R.id.edt_namaApoteker);
        edtKotaApoteker     =   findViewById(R.id.edt_kotaApoteker);
        edtNohpApoteker     =   findViewById(R.id.edt_nohpApoteker);
        edtAlamatApoteker   =   findViewById(R.id.edt_alamatApoteker);


        // Inisialisasi Radio Group Shift

        rgShift = (RadioGroup) findViewById(R.id.rg_shift);


        // Inisialisasi button back

        btnBack =   (ImageButton) findViewById(R.id.btnBack);


        // fungsi ketika button back di klik

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(CreateDataApoteker.this, ApotekerActivity.class);
                startActivity(kembali);
            }
        });




    }



    public void CreateApoteker(View view) {
        insertDataApoteker();
    }

    private void insertDataApoteker() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ServerAPI.URL_Create_Apoteker,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CreateDataApoteker.this, "Berhasil Menambahkan Data Baru", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CreateDataApoteker.this, ApotekerActivity.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                // Posting parameter ke post url

                Map<String, String> params = new HashMap<String, String>();

                int checkedButtonId = rgShift.getCheckedRadioButtonId();

                RadioButton checkedButton = findViewById(checkedButtonId);

                // Mengambil value dari EditText

                idApoteker          =   edtIdApoteker.getText().toString();
                nmApoteker          =   edtNmApoteker.getText().toString();
                kotaApoteker        =   edtKotaApoteker.getText().toString();
                nohpApoteker        =   edtNohpApoteker.getText().toString();
                shiftApoteker       =   checkedButton.getText().toString();
                alamatApoteker      =   edtAlamatApoteker.getText().toString();

                // Menyimpan value ke dalam HashMap

                params.put("id_apoteker", idApoteker);
                params.put("nama", nmApoteker);
                params.put("kota", kotaApoteker);
                params.put("no_hp", nohpApoteker);
                params.put("shift", shiftApoteker);
                params.put("alamat", alamatApoteker);

                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
}