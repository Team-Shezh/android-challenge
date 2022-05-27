package com.example.android_programming_task;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class repo_summary extends AppCompatActivity {


    RequestQueue queue;
    TextView display,username_text;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_summary);

        display = findViewById(R.id.display);
        username_text = findViewById(R.id.textView2);

        Intent myIntent = getIntent();
        String username = myIntent.getStringExtra(MainActivity.EXTRA_TEXT);

        username_text.setText(String.format("Username - %s", username));

        queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(get_api(username));


    }

    public JsonArrayRequest get_api(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";

        return new JsonArrayRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                //textView.setText("Response: " + response.toString());
                                try {
                                    String data = "";
                                    //Log.i("api_error",response.getJSONObject(1).get("name").toString());
                                    int maxItems = response.length();
                                    for (int i = 0;i<maxItems;i++){
                                        data = data + response.getJSONObject(i).get("name").toString() + "\n\n";
                                    }
                                    display.setText(data);
                                    Log.i("api_error",data);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.i("api_error",e.toString());


                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("response Errors", error + "");
                        if (error instanceof NoConnectionError) {
                            Log.d("NoConnectionError>>>>>>>>>", "NoConnectionError.......");
                            Toast.makeText(repo_summary.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            Log.d("ServerError>>>>>>>>>", "ServerError.......");
                            Toast.makeText(repo_summary.this, "Server Error", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(repo_summary.this, No_repo_found.class);
                            startActivity(intent);
                        } else if (error instanceof NetworkError) {
                            Log.d("NetworkError>>>>>>>>>", "NetworkError.......");
                            Toast.makeText(repo_summary.this, "Network Error", Toast.LENGTH_SHORT).show();
                        } else if (error instanceof TimeoutError) {
                            Log.d("TimeoutError>>>>>>>>>", "TimeoutError.......");
                            Toast.makeText(repo_summary.this, "Timeout Error", Toast.LENGTH_SHORT).show();
                        }
                    }


                });
    }
    private JsonArrayRequest searchNameStringRequest(String userID) {

        String url = "https://api.github.com/users/tirth5828/repos";

        return new JsonArrayRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        //textView.setText("Response: " + response.toString());
                        try {
                            Log.i("api_error",response.getJSONArray(0).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("api_error", error.getMessage());

                    }
                });
    }
}