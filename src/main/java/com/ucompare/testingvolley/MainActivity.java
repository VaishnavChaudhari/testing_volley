package com.ucompare.testingvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    TextView textView;
    ImageView imageView;
    TextView textView0;
    TextView textView1;



    public void generateWeather(View view) {
        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
        try {


            queue = Volley.newRequestQueue(this);
            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + String.valueOf(textView.getText()) + "&appid=43f3e717224d44c5f658c7b969b2e5b6";
            StringRequest
                    stringRequest
                    = new StringRequest(
                    Request.Method.GET,
                    url,
                    new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {

                            Log.d("done", response.toString());
                            try {
                                JSONObject jsonObject = new JSONObject(String.valueOf(response));
                                String weatherInfo = jsonObject.getString("weather");


                                JSONArray jsonArray = new JSONArray(weatherInfo);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject part = jsonArray.getJSONObject(i);

                                    textView0.setText(part.getString("main"));
                                    textView1.setText(part.getString("description"));
                                    Log.i("main", part.getString("main"));
                                    Log.i("description", part.getString("description"));
                                }

                            } catch (Exception e) {
                                Log.i("exception", String.valueOf(e));
                            }

                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("error", error.getMessage());
                        }
                    });
            queue.add(stringRequest);
        }catch(Exception e){
            Toast.makeText(this, "Connect to internet", Toast.LENGTH_SHORT).show();
        }
    }


        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            textView = findViewById(R.id.editTextTextPersonName2);
            imageView = findViewById(R.id.imageView2);
            textView0 = findViewById(R.id.textView6);
            textView1 = findViewById(R.id.textView7);

        }
    }
