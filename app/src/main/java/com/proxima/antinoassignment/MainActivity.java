package com.proxima.antinoassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.proxima.antinoassignment.Adapters.CardAdapter;
import com.proxima.antinoassignment.Configurations.Config;
import com.proxima.antinoassignment.utility.CardObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    Config config;
    ArrayList<CardObject> cardObjects;
    RecyclerView recyclerViewCard;
    RecyclerView.Adapter cardAdapter;
    RecyclerView.LayoutManager cardLayoutManager;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardObjects = new ArrayList<>();
        config = new Config();
        initializeRecyclerView();
        getCardData();
    }

    private void initializeRecyclerView() {
        recyclerViewCard = findViewById(R.id.recyclerView);
        recyclerViewCard.setNestedScrollingEnabled(false);
        recyclerViewCard.setHasFixedSize(false);
        cardLayoutManager = new LinearLayoutManager(this);
        recyclerViewCard.setLayoutManager(cardLayoutManager);
        cardAdapter = new CardAdapter(this,cardObjects);
        recyclerViewCard.setAdapter(cardAdapter);
    }

    private void getCardData() {

        requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, config.cardURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("Card Api Data" , response.toString());
                for (int i = 0 ; i < response.length() ; i++)
                {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        CardObject card = new CardObject(jsonObject.getString("url"),jsonObject.getString("name"),jsonObject.getString("age"),jsonObject.getString("location"));
                        cardObjects.add(card);
                        cardAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                
                Log.e("Error Card Api" , error.toString());
                Toast.makeText(MainActivity.this, "Internal Error Occurred Please Wait For Some Time", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
