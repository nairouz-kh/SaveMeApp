package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    String api_Link ="https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02";
    Button LoadBtn;
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadBtn =findViewById(R.id.btn);
        text =findViewById(R.id.txt);





        LoadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strText = text.getText().toString();

                ConnectToApi();



            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    //api function
   private void ConnectToApi(){

        RequestQueue queue = Volley.newRequestQueue(this);



        StringRequest stringRequest = new StringRequest(Request.Method.GET, api_Link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        queue.add(stringRequest);
    }


 }






