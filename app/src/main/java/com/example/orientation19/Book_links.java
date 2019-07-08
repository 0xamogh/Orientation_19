package com.example.orientation19;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mlsdev.animatedrv.AnimatedRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Book_links extends AppCompatActivity {
    String url="https://spider.nitt.edu/orientation/books/details";

    ArrayList<Book> dataModelArrayList;
    private Bookadapter rvAdapter;
    private AnimatedRecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_links);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Orientaion'19");
        toolbar.setTitleTextColor(Color.WHITE);

        final String department=getIntent().getStringExtra("department");
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);
                        try {

                                dataModelArrayList = new ArrayList<>();
                            JSONArray dataArray = new JSONArray(response);
                                for (int i = 0; i < dataArray.length(); i++) {
                                    Book playerModel = new Book();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setName(dataobj.getString("name"));
                                    playerModel.setDepartment(dataobj.getString("department"));
                                    playerModel.setLink(dataobj.getString("link"));
                                    Log.d("name",dataobj.getString("name"));
                                    dataModelArrayList.add(playerModel);

                                }

                            recyclerView=(AnimatedRecyclerView)findViewById(R.id.recycler_view);
                                rvAdapter=new Bookadapter(Book_links.this,dataModelArrayList);
                                recyclerView.setAdapter(rvAdapter);
                                //recyclerView.setLayoutManager(new LinearLayoutManager(Book_links.this));

                            rvAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(rvAdapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("department", department);
                return params;
            }
        };
        queue.add(postRequest);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            Toast.makeText(this, "User Signed Out", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }



}
