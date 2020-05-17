package com.example.golee;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // 변수 선언 부
    // 서버 주소값 및 url 포멧은 항상 고정적  .
    final static String serverdomain ="http://13.125.164.239/post";
    final static String url = "url";
    // Oncreate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, SplashActivity.class);
        restfulRequest();
        startActivity(intent);
    }
    //RestfulRequest ( get 요청 함 )

    public void restfulRequest(){

        //RequestQueue  서버 요청자. 다른 Request 클래스들의 정보대로 서버에 요청을 보내는 역할
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        // Json 형태로 Post를 요청하기 위해서 JSONObject를 사용함
        JSONObject object = new JSONObject();
        try {
            //TODO value 값은 메신저에서 하드코딩 방식이 아닌 메시지에서 받은
            // 변수값을 넣어주는 작업을 해야함
            object.put(url,"www.naver.com");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Object Log Point", object.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, serverdomain, object,
                new Response.Listener<JSONObject>() {
                    // jsonObjectRequest 정상적으로 실행했을 경우 반환값
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("OnResponse","String Response : "+ response.toString());
                    }
                },
                //응답실패시
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error","Error getting response");
            }
        });

        //RequestQueue 객체의 add( ) 함수에 Request 객체를 매개변수로 지정하여 호출하면 서버 연동이 발생합니다.
        requestQueue.add(jsonObjectRequest);
    }

}