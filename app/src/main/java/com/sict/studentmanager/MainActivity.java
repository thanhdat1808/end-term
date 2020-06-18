package com.sict.studentmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private Button btn,btncancel;
private ProgressBar progressBar;
private EditText email, pass;
private LinearLayout linear;
public ArrayList arrayList;
public String id;
String url ="http://192.168.1.7/studentmanager/blog/public/account";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn =(Button) findViewById(R.id.button);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
        btncancel =(Button) findViewById(R.id.btncancel);
        linear =(LinearLayout) findViewById(R.id.linear1);
        arrayList = new ArrayList();
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = findViewById(R.id.email);
                String stemail = email.getText().toString();
                if(TextUtils.isEmpty(stemail)) {
                    email.setError("Please enter your email");
                    return;
                }
                pass =  findViewById(R.id.pass);
                String stpass = pass.getText().toString();
                if(TextUtils.isEmpty(stpass)) {
                    pass.setError("Cannot be empty");
                    return;
                }
                GetData(url);
                Intent intent = new Intent(MainActivity.this,Home.class);
                intent.putExtra("title", "Thanh Dat");
                startActivity(intent);
            }
        });
    }
    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        arrayList.add(new Info(
                                jsonObject.getString("id"),
                                jsonObject.getString("user"),
                                jsonObject.getInt("password")
                        ));
                       id = jsonObject.getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
