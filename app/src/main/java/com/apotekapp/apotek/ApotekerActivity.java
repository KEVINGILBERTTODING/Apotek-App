package com.apotekapp.apotek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.apotekapp.apotek.utill.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApotekerActivity extends AppCompatActivity {

    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<DataApoteker> mItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apoteker);

        mRecyclerview   =   (RecyclerView) findViewById(R.id.recyclerView);

        mItems = new ArrayList<>();

        panggilData();

        mManager = new LinearLayoutManager(ApotekerActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new ApotekerAdapter(ApotekerActivity.this, mItems);
        mRecyclerview.setAdapter(mAdapter);
    }

    private void panggilData() {


        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_Read_Apoteker,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d("volley", "response : " + response.toString());
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                DataApoteker md = new DataApoteker();
                                md.setId(data.getString("id"));
                                md.setId_apoteker(data.getString("id_apoteker"));
                                md.setNm_apoteker(data.getString("nama"));
                                md.setKota_apoteker(data.getString("kota"));
                                md.setNoHp_apoteker(data.getString("no_hp"));
                                md.setShift(data.getString("shift"));
                                md.setAlamat_apoteker(data.getString("alamat"));

                                mItems.add(md);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        com.apotekapp.apotek.AppController.getInstance().addToRequestQueue(reqData);
    }

}