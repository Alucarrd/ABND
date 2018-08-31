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

    //define constants
    private static final String apiKey = BuildConfig.ApiKey;
    private static final int NEWSARTICLE_LOADER_ID = 1;
    private static final String LOG_TAG = MainActivity.class.getName();
    private final String NEWS_URL = "http://content.guardianapis.com/search?q=[TOPIC]&show-tags=contributor&api-key=[API_KEY]";
    private String myTopicURL = buildTopicURL("entertainment");


    //define private variable for internal class use
    private TextView _emptyStateTextView;
    private NewsArticleAdapter _newsArticleAdapter;
    private SwipeRefreshLayout _mySwiper;
    private View _loadingIndicator;
    private ListView _newsArticleListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        //Time to display list items
        _newsArticleListView = (ListView) findViewById(R.id.list);
        _newsArticleListView.setEmptyView(_emptyStateTextView);

        //setting up adapter
        _newsArticleAdapter = new NewsArticleAdapter(this, new ArrayList<NewsArticle>());
        _newsArticleListView.setAdapter(_newsArticleAdapter);

        //setup onclick listener to redirect clicker to actual news article web link
        _newsArticleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsArticle myItem = _newsArticleAdapter.getItem(position);
                String myURL = myItem.getURL();
                Intent urlIntent = new Intent(Intent.ACTION_VIEW);
                urlIntent.setData(Uri.parse(myURL));
                startActivity(urlIntent);
            }
        });

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
        Log.e(LOG_TAG, "building my API URL: " + myRequestURL);
        return myRequestURL;

    }

    /**
     * Override the onCreateLoader for async url fetch
     */
    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int i, Bundle bundle) {
        Log.v(LOG_TAG, "OnCreateLoader");
        return new NewsArticleLoader(this, myTopicURL);

    }

    /**
     * Override the onLoadFinished for async url fetch
     */
    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> data) {
        Log.v(LOG_TAG, "OnLoaderFinished");
        _emptyStateTextView.setText(R.string.no_article);

        View loadingIndicator = findViewById(R.id.indeterminateBar);
        loadingIndicator.setVisibility(View.GONE);
        _newsArticleAdapter.clear();
        if (data != null && !data.isEmpty())
            _newsArticleAdapter.addAll(data);
    }

    /**
     * Override the onLoaderReset for async url fetch
     */
    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {
        Log.v(LOG_TAG, "OnLoaderReset");
        _newsArticleAdapter.clear();
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


