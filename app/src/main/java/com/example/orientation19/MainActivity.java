package com.example.orientation19;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.android.volley.toolbox.Volley;
import com.example.orientation19.Database.MyDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import com.arcnavigationview.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    public List<EventPOJO> events= new ArrayList<>();
    private MyDatabase myDatabase=new MyDatabase(this);
    public RecyclerView.LayoutManager lm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
// us: 1101179
// pass: Ofeb03080
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        RecyclerView rv =findViewById(R.id.Events);
        rv.setLayoutManager(new LinearLayoutManager(this));
        events.add(new EventPOJO("Title","Description","time","Venue"));
        final HomeAdapter adapter=new HomeAdapter(events);
        lm= new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);

        String eventUrl = "https://spider.nitt.edu/orientation/events/details";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, eventUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i<response.length();i++){
                            try{
                            JSONObject jsonObject= response.getJSONObject(i);

                            Log.d("object1",response.toString());
                            events.add(new EventPOJO(jsonObject.getString("title"),"description",jsonObject.getString("start"),jsonObject.getString("department")));
                            Date datenew= new Date();
                            Log.d("lol",datenew.toString());
                            //int comp= datenew.compareTo(date);
                                adapter.notifyDataSetChanged();

                            Log.d("object2",events.get(1).getTime());
                            }catch (JSONException e){
                                Log.d("dude",e.toString());
                                Snackbar.make(drawer,"Error",Snackbar.LENGTH_LONG).show();

                            }
                        }
                    }
            }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(drawer,"Please check your internet connection",Snackbar.LENGTH_LONG).show();
                    }
        });
        requestQueue.add(jsonArrayRequest);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            myDatabase.deleteAll();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            Toast.makeText(this, "User Signed Out", Toast.LENGTH_SHORT).show();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_books) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Books()).commit();
        } else if (id == R.id.nav_calendar) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Calendar()).commit();
        } else if (id == R.id.nav_contacts) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new contacts()).commit();
        } else if (id == R.id.nav_maps) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new live_tracking()).commit();
        } else if (id == R.id.nav_t_shirts) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new t_shirt_reg()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
