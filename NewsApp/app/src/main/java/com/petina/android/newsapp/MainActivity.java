package com.petina.android.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private String apiKey = BuildConfig.ApiKey;

    private final String NEWS_URL = "http://content.guardianapis.com/search?q=[TOPIC]&show-tags=contributor&api-key=[API_KEY]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
