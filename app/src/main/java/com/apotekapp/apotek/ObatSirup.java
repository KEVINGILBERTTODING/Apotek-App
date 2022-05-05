package com.apotekapp.apotek;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apotekapp.apotek.Adapter.ObatAdapter;
import com.apotekapp.apotek.Model.DataObat;
import com.apotekapp.apotek.Utill.ServerAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObatSirup extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    ListView list;
    AlertDialog.Builder dialog;
    SwipeRefreshLayout swipe;
    List<DataObat> itemList = new ArrayList<DataObat>();
    ObatAdapter adapter;
    FloatingActionButton fab;
    SearchView searchView;
    private ImageButton btnBack;

    private ImageButton btnSirup, btnTablet, btnOles, btnLainnya;

    public static final String TAG_USERNAME = "username";
    String username;
    SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fungsi untuk menyembunyikan navbar

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_obat);


        // Menyimpan username ke dalam sharedpreferance

        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);

        // Inisialisasi searchview, swipe, listview, and floating action button

        searchView  =   (SearchView) findViewById(R.id.search_barObat);
        swipe       =   (SwipeRefreshLayout) findViewById(R.id.swipe);
        list        =   (ListView) findViewById(R.id.list);
        fab         =   (FloatingActionButton) findViewById(R.id.fabAdd);

        // Inisialisasi ImageButton menu kategori obat

        btnSirup        =   (ImageButton) findViewById(R.id.btnSirup);
        btnTablet       =   (ImageButton) findViewById(R.id.btnTablet);
        btnOles         =   (ImageButton) findViewById(R.id.btnObatOles);
        btnLainnya      =   (ImageButton) findViewById(R.id.btnLainnya);

        // Mengatur warna tint fab

        fab.setColorFilter(getResources().getColor(R.color.white));

        // Inisialisasi button back

        btnBack =   (ImageButton) findViewById(R.id.btnBack);

        // fungsi ketika button back di klik

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kembali = new Intent(ObatSirup.this, MainActivity.class);

                //   Mengirim username menggunakan intent
                kembali.putExtra(TAG_USERNAME, username);

                startActivity(kembali);
            }
        });


        // Fungsi ketika button + diklik

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDataObat();
            }
        });



        adapter = new ObatAdapter(ObatSirup.this, itemList);
        list.setAdapter(adapter);

        swipe.setOnRefreshListener(this);



        // Fungsi ketika data refresh

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           adapter.notifyDataSetChanged();
                           callVolley();
                       }
                   }
        );

        // Fungsi saat listview diklik

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String idx = itemList.get(position).getId();
                final CharSequence[] dialogitem = {"Lihat", "Edit", "Delete"};
                dialog = new AlertDialog.Builder(ObatSirup.this);
                // dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                ShowDataObat(position);
                                break;
                            case 1:
                                editDataObat(position);
                                break;
                            case 2:
                                hapus(idx);
                                break;

                        }
                    }
                }).show();
                return false;
            }
        });



        // Fungsi saat memasukkan kata pencarian pada searchview



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    adapter.filter("");
                    list.clearTextFilter();
                } else {
                    adapter.filter(s);
                }
                adapter.filter(s);
                return true;

            }
        });


        // Fungsi saat button menu kategori obat di klik


        btnSirup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ObatSirup.this, ObatSirup.class);
                startActivity(intent);

            }
        });
        btnTablet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ObatSirup.this, ObatTablet.class);
                startActivity(intent);

            }
        });
        btnOles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ObatSirup.this, ObatOles.class);
                startActivity(intent);

            }
        });
        btnLainnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ObatSirup.this, ObatLain.class);
                startActivity(intent);

            }
        });


    }


    // Memanggil method callVolley

    @Override
    public void onRefresh() {
        callVolley();
    }



    // Method untuk memanggil data json menggunakan volley


    private void callVolley() {
        itemList.clear();
        adapter.notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(ServerAPI.URL_Read_Obat_Sirup, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        DataObat item = new DataObat();

                        item.setId(obj.getString("id"));
                        item.setKdobat(obj.getString("kode_obat"));
                        item.setNmobat(obj.getString("nama_obat"));
                        item.setSatuan(obj.getString("satuan_obat"));
                        item.setJumlah(obj.getString("jumlah"));
                        item.setJenis(obj.getString("jenis"));
                        item.setDesc(obj.getString("deskripsi"));
                        item.setExpired(obj.getString("expired"));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter

                adapter.notifyDataSetChanged();

                // Memanggil method updateSearchList paa ApotekerAdapter

                adapter.updateSearchedList();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ObatSirup.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue

        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);

    }


    // Method untuk pindah ke activity createdataobat

    private void insertDataObat() {
        Intent intent = new Intent(ObatSirup.this, CreateDataObat.class);
        startActivity(intent);
    }



    // Method untuk pindah ke activity detailobat

    private void ShowDataObat(int position) {

        final DataObat obat =   itemList.get(position);
        Intent  DetailObat  =   new Intent(ObatSirup.this, DetailObatActivity.class);
        DetailObat.putExtra("nama_obat", obat.getNmobat());
        DetailObat.putExtra("satuan_obat", obat.getSatuan());
        DetailObat.putExtra("jumlah", obat.getJumlah());
        DetailObat.putExtra("jenis", obat.getJenis());
        DetailObat.putExtra("desc", obat.getDesc());
        DetailObat.putExtra("expired", obat.getExpired());
        startActivity(DetailObat);
    }

    // Method untuk pindah ke activity updatedataobat dan membawa data menggunakan intent

    private void editDataObat (int position) {
        final DataObat obat =   itemList.get(position);
        Intent DataObat =   new Intent(ObatSirup.this, UpdateDataObat.class);
        DataObat.putExtra("idobat", obat.getId());
        DataObat.putExtra("kodeobat", obat.getKdobat());
        DataObat.putExtra("namaobat", obat.getNmobat());
        DataObat.putExtra("satuanobat", obat.getSatuan());
        DataObat.putExtra("jumlahobat", obat.getJumlah());
        DataObat.putExtra("jenis", obat.getJenis());
        DataObat.putExtra("descobat", obat.getDesc());
        DataObat.putExtra("expireddate", obat.getExpired());
        startActivity(DataObat);

    }


    // Method untuk menghapus data apotek

    private void hapus(String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ServerAPI.URL_DELETE_Obat,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callVolley();
                        Toast.makeText(ObatSirup.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ObatSirup.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();


                params.put("id", id );
                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }



    // *** Uncomment this method if u prefer using serchview in toolbar ***

    // Method untuk Menambahkan searchView pada toolbar, serta filter nama apoteker

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.search_menu, menu);
//
//        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                if (TextUtils.isEmpty(s)) {
//                    adapter.filter("");
//                    list.clearTextFilter();
//                } else {
//                    adapter.filter(s);
//                }
//                adapter.filter(s);
//                return true;
//            }
//        });
//        return true;
//    }


}