package com.apotekapp.apotek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apotekapp.apotek.Utill.ServerAPI;

import java.util.HashMap;
import java.util.Map;

public class UpdateDataApoteker extends AppCompatActivity {

    // String untuk mengambil data dari intent

    String id, id_apoteker, nm_apoteker, kota_apoteker, nohp_apoteker, shift_apoteker, alamat_apoteker;

    EditText updt_id, updt_nama, updt_kota, updt_nohp, updt_shift, updt_alamat;

    //String untuk hashmap

    String  xid, xnama, xkota, xnohp, xshift, xalamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_apoteker);

        // Definisikan EditText

        updt_id = findViewById(R.id.updt_idApoteker);
        updt_nama = findViewById(R.id.updt_namaApoteker);
        updt_kota = findViewById(R.id.updt_kotaApoteker);
        updt_nohp = findViewById(R.id.updt_nohpApoteker);
        updt_shift = findViewById(R.id.updt_shiftApoteker);
        updt_alamat = findViewById(R.id.updt_alamatApoteker);



        // Memanggil intent dan mengambil text yang telah dikirim dari ApotekerActivity

        Intent intent           =   getIntent();
        this.id                 =   intent.getStringExtra("id");
        this.id_apoteker        =   intent.getStringExtra("id_apoteker");
        this.nm_apoteker        =   intent.getStringExtra("nama");
        this.kota_apoteker      =   intent.getStringExtra("kota");
        this.nohp_apoteker      =   intent.getStringExtra("no_hp");
        this.shift_apoteker     =   intent.getStringExtra("shift");
        this.alamat_apoteker    =   intent.getStringExtra("alamat");



        // Settext menggunakan data yang telah di dapatkan dari intent

        updt_id.setText(id_apoteker);
        updt_nama.setText(nm_apoteker);
        updt_kota.setText(kota_apoteker);
        updt_nohp.setText(nohp_apoteker);
        updt_shift.setText(shift_apoteker);
        updt_alamat.setText(alamat_apoteker);
    }

    public void UpdateApoteker(View view) {
        updateDataApoteker();
    }

    private void updateDataApoteker() {

        StringRequest stringRequest =   new StringRequest(Request.Method.POST, ServerAPI.URL_Update_Apoteker,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(UpdateDataApoteker.this, "Berhasil Memperbarui Data", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(UpdateDataApoteker.this, ApotekerActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // Posting parameters ke post url

                Map<String, String> params = new HashMap<String, String>();



                // Mengambil value dari EditText

                xid             = updt_id.getText().toString();
                xnama           = updt_nama.getText().toString();
                xkota           = updt_kota.getText().toString();
                xnohp           = updt_nohp.getText().toString();
                xshift          = updt_shift.getText().toString();
                xalamat         = updt_alamat.getText().toString();



                // Menyimpan value ke dalam hashmap lalu mengirimnya

                params.put("id", id);
                params.put("id_apoteker", xid);
                params.put("nama", xnama);
                params.put("kota", xkota);
                params.put("no_hp", xnohp);
                params.put("shift", xshift);
                params.put("alamat", xalamat);


                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);

    }
}