package com.petina.android.newsapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Loader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<NewsArticle>> {

    //define constants
    private static final String apiKey = BuildConfig.ApiKey;
    private static final int NEWSARTICLE_LOADER_ID = 1;
    private static final String LOG_TAG = MainActivity.class.getName();
    private final String NEWS_URL = "http://content.guardianapis.com/search?q=[TOPIC]&show-tags=contributor&api-key=[API_KEY]";
    private String myTopicURL = buildTopicURL("entertainment");
    private ProgressBar _progressBar;
    private RecyclerView recyclerView;
    private NewsArticleRecyclerViewAdapter rAdapter;

    //define private variable for internal class use
    private TextView _emptyStateTextView;
    //private NewsArticleAdapter _newsArticleAdapter;
    private SwipeRefreshLayout _mySwiper;
    private View _loadingIndicator;
   // private RecyclerView _newsArticleListView;
    /**
     * Override the onLoadFinished for async url fetch
     */

    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, ArrayList<NewsArticle> data) {
        Log.v(LOG_TAG, "OnLoaderFinished");
        _emptyStateTextView.setText(R.string.no_article);
        _progressBar.setVisibility(View.GONE);
        View loadingIndicator = findViewById(R.id.indeterminateBar);
        loadingIndicator.setVisibility(View.GONE);
/*
        if (data != null && !data.isEmpty()) {
            for(int i = 0; i< data.size(); i++){

                rAdapter = new NewsArticleRecyclerViewAdapter(MainActivity.this, data);
                recyclerView.setAdapter(rAdapter);
                //notifyDataSetChanged();
                rAdapter.notifyDataSetChanged();
                Log.v(LOG_TAG, String.valueOf(i+1) + ". " + data.get(i).getContributor());
            }

        }
        */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _progressBar = (ProgressBar) findViewById(R.id.indeterminateBar);
        //Time to display list items
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //link view objects to UI elements
        _mySwiper = findViewById(R.id.swiperefresh);
        _emptyStateTextView = (TextView) findViewById(R.id.empty_view);


        //check internet connection
        //if there's no connectivity, then display No internet connection msg, otherwise, create the instance of loader
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            //I have intenet connection
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWSARTICLE_LOADER_ID, null, this);

        } else {
            // First, hide loading indicator so error message will be visible
            _loadingIndicator = findViewById(R.id.indeterminateBar);
            _loadingIndicator.setVisibility(View.GONE);
            _emptyStateTextView.setText(R.string.no_internet_connection);
        }


        //setting up adapter

       //    recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getSupportLoaderManager().initLoader(0, null, this);
        //recyclerView.setAdapter(rAdapter);
        //_newsArticleListView.setEmptyView(_emptyStateTextView);



        //setup refresh listener
        _mySwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "OnRefresh called");
                restartLoader(NEWSARTICLE_LOADER_ID);
                _mySwiper.setRefreshing(false);

            }
        });

    }

    /**
     * Takes in a topic and build guardian news api url
     *
     * @param myTopic -> the topic to search with
     * @return string url to the guardian news api
     */
    private String buildTopicURL(String myTopic) {
        String myRequestURL = NEWS_URL.replace("[API_KEY]", apiKey).replace("[TOPIC]", myTopic);
        Log.v(LOG_TAG, "building my API URL: " + myRequestURL);
        return myRequestURL;

    }

    /**
     * Override the onCreateLoader for async url fetch
     */
    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int i, Bundle bundle) {
        Log.v(LOG_TAG, "OnCreateLoader");
        _progressBar.setVisibility(View.VISIBLE);
        return new NewsArticleLoader(this, myTopicURL);

    }



    /**
     * Override the onLoaderReset for async url fetch
     */
    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {
        Log.v(LOG_TAG, "OnLoaderReset");
        recyclerView.setAdapter(null);

    }

    /**
     * Restart loader action when swiper fresh's triggered
     *
     * @param loaderId int Id for the loader defined above.
     */
    private void restartLoader(int loaderId) {
        getLoaderManager().restartLoader(loaderId, null, this);
    }

}


