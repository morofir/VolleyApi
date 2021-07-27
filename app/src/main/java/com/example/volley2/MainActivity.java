package com.example.volley2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String URL = "https://www.json-generator.com/api/json/get/cfsXpFGwwO?indent=2";
    RecyclerView recyclerView;
    List<MovieObject> moviesList;
    RequestQueue requestQueue;
    RecyAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Singleton.getInstance(this).getRequestQueue();
        moviesList = new ArrayList<>();

        recyclerView = findViewById(R.id.movies_iv);
        recyclerView.setHasFixedSize(true);

       recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyAdapter(MainActivity.this,moviesList);
        recyclerView.setAdapter(adapter);


       getData();

    }

    private void getData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i=0;i<response.length();i++){
                        JSONObject jsonObject = response.getJSONObject(i);

                        String title= jsonObject.getString("title");
                        String rating= jsonObject.getString("rating");
                        String desc= jsonObject.getString("overview");
                        String url = jsonObject.getString("poster");

                        MovieObject movieObject = new MovieObject(title,rating,url,desc);
                        moviesList.add(movieObject);
                    }
                }catch (Exception e){
                    Log.e("e",e.getMessage());
                }
                adapter = new RecyAdapter(MainActivity.this,moviesList);
                recyclerView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("e",error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sortaz:
                Collections.sort(moviesList,MovieObject.comparatorAZ);
                adapter.notifyDataSetChanged();
                return true;


            case R.id.sortza:
                Collections.sort(moviesList,MovieObject.comparatorZA);
                adapter.notifyDataSetChanged();
                return true;



            case R.id.sort_rating:
                Collections.sort(moviesList,MovieObject.comparatorRatings);
                adapter.notifyDataSetChanged();

                return true;



        }
        return super.onOptionsItemSelected(item);
    }
}