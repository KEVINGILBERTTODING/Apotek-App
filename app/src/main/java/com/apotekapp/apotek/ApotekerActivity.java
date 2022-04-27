package com.apotekapp.apotek;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import com.apotekapp.apotek.Adapter.ApotekerAdapter;
import com.apotekapp.apotek.Model.DataApoteker;
import com.apotekapp.apotek.Utill.ServerAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApotekerActivity extends AppCompatActivity implements  SwipeRefreshLayout.OnRefreshListener {

    ListView list;
    SearchView searchView;
    AlertDialog.Builder dialog;
    SwipeRefreshLayout swipe;
    List<DataApoteker> itemList = new ArrayList<DataApoteker>();
    ApotekerAdapter adapter;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apoteker);



        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        list = (ListView) findViewById(R.id.list_apoteker);

        fab = (FloatingActionButton) findViewById(R.id.add_apoteker);

        // Fungsi ketika button + diklik

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDataApoteker();
            }
        });

        adapter = new ApotekerAdapter(ApotekerActivity.this, itemList);
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
                dialog = new AlertDialog.Builder(ApotekerActivity.this);
                // dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                showDataApoteker(position);
                                break;
                            case 1:
                                updateDataApoteker(position);
                                break;
                            case 2:
                                deleteDataApoteker(idx);
                                break;
                        }
                    }
                }).show();
                return false;
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
        JsonArrayRequest jArr = new JsonArrayRequest(ServerAPI.URL_Read_Apoteker, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        DataApoteker item = new DataApoteker();

                        item.setId(obj.getString("id"));
                        item.setId_apoteker(obj.getString("id_apoteker"));
                        item.setNm_apoteker(obj.getString("nama"));
                        item.setKota_apoteker(obj.getString("kota"));
                        item.setNoHp_apoteker(obj.getString("no_hp"));
                        item.setShift(obj.getString("shift"));
                        item.setAlamat_apoteker(obj.getString("alamat"));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();

                adapter.updateSearchedList();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ApotekerActivity.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue

        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);

    }

    // Method untuk pindah ke activity CreateDataApoteker

    private void insertDataApoteker() {
        Intent intent = new Intent(ApotekerActivity.this, CreateDataApoteker.class);
        startActivity(intent);
    }

     // Method untuk pindah ke activity DetailApoteker

    private void showDataApoteker(int position) {

        final DataApoteker apoteker =   itemList.get(position);
        Intent  DetailApoteker  =   new Intent(ApotekerActivity.this, DetailApotekerActivity.class);

        // Mengirim data ke detailApoteker menggunakan intent

        DetailApoteker.putExtra("id_apoteker", apoteker.getId_apoteker());
        DetailApoteker.putExtra("nama", apoteker.getNm_apoteker());
        DetailApoteker.putExtra("kota", apoteker.getKota_apoteker());
        DetailApoteker.putExtra("no_hp", apoteker.getNoHp_apoteker());
        DetailApoteker.putExtra("shift", apoteker.getShift());
        DetailApoteker.putExtra("alamat", apoteker.getAlamat_apoteker());
        startActivity(DetailApoteker);
    }

     // Method untuk pindah ke UpdateDataActivity dan mengirim data menggunakan intent

    private void updateDataApoteker (int position) {

        // Mengirim data ke detailApoteker menggunakan intent

        final DataApoteker apoteker =   itemList.get(position);
        Intent DetailApoteker =   new Intent(ApotekerActivity.this, UpdateDataApoteker.class);
        DetailApoteker.putExtra("id", apoteker.getId());
        DetailApoteker.putExtra("id_apoteker", apoteker.getId_apoteker());
        DetailApoteker.putExtra("nama", apoteker.getNm_apoteker());
        DetailApoteker.putExtra("kota", apoteker.getKota_apoteker());
        DetailApoteker.putExtra("no_hp", apoteker.getNoHp_apoteker());
        DetailApoteker.putExtra("shift", apoteker.getShift());
        DetailApoteker.putExtra("alamat", apoteker.getAlamat_apoteker());
        startActivity(DetailApoteker);

    }


     // Method untuk menghapus data apoteker

    private void deleteDataApoteker (String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ServerAPI.URL_DELETE_Apoteker,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callVolley();
                        Toast.makeText(ApotekerActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ApotekerActivity.this, "gagal koneksi ke server, cek setingan koneksi anda", Toast.LENGTH_LONG).show();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
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
        return true;
    }
}