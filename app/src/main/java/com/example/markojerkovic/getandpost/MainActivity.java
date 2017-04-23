package com.example.markojerkovic.getandpost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "getandpost";
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //put button pressed
    public void onPut(View V) {
        Log.d(LOG_TAG, "In onPut");
        final TextView txtBox = (TextView) findViewById(R.id.textView);

    }

    //get button pressed
    public void onGet(View V) {
        Log.d(LOG_TAG, "In onGet");
        final TextView txtBox = (TextView) findViewById(R.id.textView);
        String url = "https://luca-ucsc-teaching-backend.appspot.com/hw3/request_via_get?token=abracadabra";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //     mTextView.setText("Response json: " + response.toString());
                        Log.d(LOG_TAG, "Received: " + response.toString());
                        // Ok, let's disassemble a bit the json object.
                      try {
                            String responseStr = response.getString("result");
                            txtBox.setText(responseStr);
                        } catch (Exception e) {
                            txtBox.setText("Aaauuugh, received bad json: " + e.getStackTrace());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d(LOG_TAG, error.toString());
                    }
                });

        queue.add(jsObjRequest);

    }
}
