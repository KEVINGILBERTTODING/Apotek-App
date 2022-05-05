package com.apotekapp.apotek;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apotekapp.apotek.Utill.AppController;
import com.apotekapp.apotek.Utill.ServerAPI;
import com.apotekapp.apotek.Utill.AppController;
import com.apotekapp.apotek.Utill.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.apotekapp.apotek.LoginActivity.TAG_USERNAME;
import static com.apotekapp.apotek.LoginActivity.session_status;

public class RegisterActivity extends AppCompatActivity {
    ConnectivityManager conMgr;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String tag_json_obj = "json_obj_req";
    SharedPreferences sharedpreferences;

    public static final String my_shared_preferences = "my_shared_preferences";
    private EditText txt_username, txt_pass, txt_pass_conf;
    private Button btn_register;
    private TextView login;
    private int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Fungsi untuk menyembunyikan navbar

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // Fungsi untuk menyembunyikan status bar

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    &&
                    conMgr.getActiveNetworkInfo().isAvailable()
                    &&
                    conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }

        sharedpreferences = getSharedPreferences(my_shared_preferences,Context.MODE_PRIVATE);


        txt_username = (EditText) findViewById(R.id.edt_username);
        txt_pass = (EditText) findViewById(R.id.edt_pass);
        txt_pass_conf = (EditText) findViewById(R.id.edt_pass_conf);
        btn_register = (Button) findViewById(R.id.button_register);
        login = (TextView) findViewById(R.id.tv_ToLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });


//        btn_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final ProgressDialog pDialog = new ProgressDialog(RegisterActivity.this);
//                pDialog.setCancelable(false);
//                pDialog.setMessage("Tunggu Sebentar ...");
//                pDialog.show();
//                StringRequest strReq = new StringRequest(Request.Method.POST, ServerApi.URL_REGISTER, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("Response", "register Response: " + response);
//                        pDialog.cancel();
//
//                        TextView regis_username = (TextView) findViewById(R.id.edt_username);
//                        TextView password = (TextView) findViewById(R.id.edt_pass);
//
//
//                        regis_username.setText(null);
//                        password.setText(null);
//                        try {
//                            JSONObject jObj = new JSONObject(response);
//                            success = jObj.getInt(TAG_SUCCESS);
//                            // Check for error node in json
//                            if (success == 1) {
//                                String username = jObj.getString(TAG_USERNAME);
//                                Log.e("Pendaftaran Berhasil!", jObj.toString());
//                                Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//                                // menyimpan login ke session
//                                SharedPreferences.Editor editor = sharedpreferences.edit();
//                                editor.putBoolean(session_status,true);
//                                editor.putString(TAG_USERNAME, username);
//                                editor.putString("kd_kons", jObj.getString("kd_kons"));
//                                editor.apply();
//
//                                // Memanggil main activity
//                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                intent.putExtra(TAG_USERNAME, username);
//                                startActivity(intent);
//                                finish();
//                            } else {
//                                Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//                            }
//                        } catch (JSONException e) {
//                            // JSON error
//                            e.printStackTrace();
//                        }
//                    }
//                },new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "Register Error: " + error.getMessage());
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                        pDialog.cancel();
//                    }
//                }){
//                    @Override
//                    protected Map<String, String> getParams() {
//                        // Posting parameters to login url
//                        // membuat objek hashmap
//                        Map<String, String> params = new HashMap<String, String>();
//
//                        params.put("username", txt_username.getText().toString());
//                        params.put("password", txt_pass.getText().toString());
//                        return params;
//                    }
//                };
//                AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
//            }
//        });



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = txt_username.getText().toString();
                String password = txt_pass.getText().toString();
                String confir_password = txt_pass_conf.getText().toString();


                //validasi form
                if( username.isEmpty() || password.length() < 8 || !confir_password.equals(password)){

                    if(username.isEmpty()){
                        txt_username.setError("Masukan Nama Pengguna");
                    }
                    if(password.length() < 8) {

                        txt_pass.setError("Password berisi minimal 8 karakter");
                    }
                    if(!confir_password.equals(password)) {

                        txt_pass_conf.setError("Password tidak sama");
                    }
                }else{
                    try {
                        if (conMgr.getActiveNetworkInfo() !=
                                null
                                &&
                                conMgr.getActiveNetworkInfo().isAvailable()
                                &&
                                conMgr.getActiveNetworkInfo().isConnected()) {
                            register(username, password);
                        } else {
                            Toast.makeText(getApplicationContext(), "Periksa Koneksi Internet", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void register(final String username, final String password) {
        final ProgressDialog pDialog = new ProgressDialog(RegisterActivity.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Tunggu Sebentar ...");
        pDialog.show();
        StringRequest strReq = new StringRequest(Request.Method.POST, ServerAPI.URL_Register, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response);
                pDialog.cancel();
                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);
                    // Check for error node in json
                    if (success == 1) {
                        String username = jObj.getString(TAG_USERNAME);
                        Log.e("Pendaftaran Berhasil!", jObj.toString());
                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        // menyimpan login ke session
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(session_status,true);
                        editor.putString(TAG_USERNAME, username);
                        editor.putString("kd_kons", jObj.getString("kd_kons"));
                        editor.apply();

                        // Memanggil main activity
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.putExtra(TAG_USERNAME, username);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Register Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                pDialog.cancel();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();

                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        // Adding request to request queue


        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}
