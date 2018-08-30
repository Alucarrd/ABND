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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Loader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<NewsArticle>> {
    private String apiKey = BuildConfig.ApiKey;
    private static final int NEWSARTICLE_LOADER_ID = 1;
    private static final String LOG_TAG = MainActivity.class.getName();
    private TextView _emptyStateTextView;
    private NewsArticleAdapter _newsArticleAdapter;
    private SwipeRefreshLayout mySwiper;
    private final String NEWS_URL = "http://content.guardianapis.com/search?q=[TOPIC]&show-tags=contributor&api-key=[API_KEY]";

    private String myTopicURL = buildTopicURL("entertainment");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySwiper = findViewById(R.id.swiperefresh);
        //check internet connection
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        _emptyStateTextView = (TextView) findViewById(R.id.empty_view);
        if(networkInfo != null && networkInfo.isConnected()){
            //I have intenet connection
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(NEWSARTICLE_LOADER_ID, null, this);
        }
        else{
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.indeterminateBar);
            loadingIndicator.setVisibility(View.GONE);
            _emptyStateTextView.setText(R.string.no_internet_connection);
        }
        ListView newsArticleListView = (ListView) findViewById(R.id.list);


        newsArticleListView.setEmptyView(_emptyStateTextView);

        _newsArticleAdapter = new NewsArticleAdapter(this, new ArrayList<NewsArticle>());

        newsArticleListView.setAdapter(_newsArticleAdapter);
        newsArticleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsArticle myItem = _newsArticleAdapter.getItem(position);
                String myURL = myItem.getURL();
                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                urlIntent.setData(Uri.parse(myURL));
                startActivity(urlIntent);
            }
        });
        mySwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "OnRefresh called");
                restartLoader(NEWSARTICLE_LOADER_ID);
                mySwiper.setRefreshing(false);

            }
        });

    }





    private String buildTopicURL(String myTopic){
        String myRequestURL = NEWS_URL.replace("[API_KEY]", apiKey).replace("[TOPIC]", myTopic);
        Log.e(LOG_TAG, "building my API URL: " + myRequestURL);
        return myRequestURL;

    }

    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int i, Bundle bundle){
        Log.v(LOG_TAG, "OnCreateLoader");
        return new NewsArticleLoader(this, myTopicURL);

    }
    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> data){
        Log.v(LOG_TAG, "OnLoaderFinished");
        _emptyStateTextView.setText(R.string.no_article);

        View loadingIndicator = findViewById(R.id.indeterminateBar);
        loadingIndicator.setVisibility(View.GONE);
        _newsArticleAdapter.clear();
        if(data != null && !data.isEmpty())
            _newsArticleAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader){
        Log.v(LOG_TAG, "OnLoaderReset");
        _newsArticleAdapter.clear();
    }

    private void restartLoader(int loaderId){
        getLoaderManager().restartLoader(loaderId,null, this );
    }

}


