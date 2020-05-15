package com.smsing.GoLee;


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

public class MainActivity extends AppCompatActivity {
    //TODO 서버 주소 및 URL 경로는 유동적으로 변경해야함
    String url ="http://13.125.164.239/get/http://malware.testing.google.test/testing/malware/";

    // Oncreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, com.smsing.GoLee.SplashActivity.class);
        makeRequest();
        startActivity(intent);
    }

    //makeRequest ( get 요청 함 )
    public void makeRequest(){
        //RequestQueue  서버 요청자. 다른 Request 클래스들의 정보대로 서버에 요청을 보내는 역할
        final RequestQueue queue = Volley.newRequestQueue(this);
        Log.d("Log point1 ","log point1"+queue.toString());
        //StringRequest 문자열을 결과로 받는 요청
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                //응답 리스너 설정
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //응답 결과처리
                        Log.d("Response is: ", response);
                    };
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //응답 실패시
                        Log.d("That didn't work!",error.toString());
                    }
                });
        //RequestQueue 객체의 add( ) 함수에 Request 객체를 매개변수로 지정하여 호출하면 서버 연동이 발생합니다.
        queue.add(stringRequest);
    }

}