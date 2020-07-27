package com.example.shimbilabs;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> ImgUrl= new ArrayList<>();

    RecyclerView recyclerView;
    LinearLayoutManager Manager;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        jsonwork();
    }
    public void jsonwork()
    {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "\n" +
                "https://testphp.urdemo.net/images/images.php", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.d("Response from Api  ", ""+response.length());
                            for(int i =1;i<response.length()+1;i++)
                            {
                                String ur = Integer.toString(i);
                                ImgUrl.add(response.getString(ur));
//                                Log.d("Response from Api  ", ""+response.getString(ur));

                            }
                            recyclerviewwork();

                        }
                        catch (Exception e) {
                            Log.d("Exception ", e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error in App  ",error.toString());
            }
        }
        );

        requestQueue.add(jsonObjectRequest);

    }
    public void recyclerviewwork()
    {
        this.recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
//        Manager = new LinearLayoutManager(this);
 //       recyclerView.setLayoutManager(Manager);
        Log.d("Response from Api  ", ""+ImgUrl);
        adapter = new Adapter(ImgUrl, this);
        Log.d("after htiing from Api  ", ""+ImgUrl);
        recyclerView.setAdapter((RecyclerView.Adapter) adapter);
    }
}