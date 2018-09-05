package com.petina.android.newsapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    //private final String NEWS_URL = "http://content.guardianapis.com/search?q=[TOPIC]&show-tags=contributor&api-key=[API_KEY]";
    private final String NEWS_URL_ROOT = "https://content.guardianapis.com/search";


    //define private variable for internal class use
    private TextView _emptyStateTextView;

    private SwipeRefreshLayout _mySwiper;
    private View _loadingIndicator;


    private RecyclerView _recyclerView;
    private NewsArticleRecyclerAdapter _myAdapter;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            Log.v(LOG_TAG, "selection selected");
            Log.v(LOG_TAG, "restart loader for the 1st time");
            restartLoader(NEWSARTICLE_LOADER_ID);
            return true;
        }
        Log.v(LOG_TAG, "restart loader for the 2nd time");
        restartLoader(NEWSARTICLE_LOADER_ID);
        return super.onOptionsItemSelected(item);
    }

    // This method initialize the contents of the Activity's options menu.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link view objects to UI elements
        _mySwiper = findViewById(R.id.swiperefresh);
        _emptyStateTextView = (TextView) findViewById(R.id.empty_view);


        //check internet connection

        boolean networkState = checkNetworkConnection();
        if(networkState)
        {
            //I have intenet connection
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWSARTICLE_LOADER_ID, null, this);

        } else {
            // First, hide loading indicator so error message will be visible
            _loadingIndicator = findViewById(R.id.indeterminateBar);
            _loadingIndicator.setVisibility(View.GONE);
            _emptyStateTextView.setText(R.string.no_internet_connection);
        }

        //setup refresh listener
        _mySwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "OnRefresh called");
                restartLoader(NEWSARTICLE_LOADER_ID);
                _mySwiper.setRefreshing(false);

            }
        });


        _myAdapter = new NewsArticleRecyclerAdapter(this, new ArrayList<NewsArticle>());
        _recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //Time to display list items
        _recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        _recyclerView.setAdapter(_myAdapter);

    }


    /**
     * Takes in a topic and build guardian news api url
     *
     * @param myTopic -> the topic to search with
     * @return string url to the guardian news api
     */
   /*
    private String buildTopicURL(String myTopic) {

        String myRequestURL = NEWS_URL.replace("[API_KEY]", apiKey).replace("[TOPIC]", myTopic);
        Log.v(LOG_TAG, "building my API URL: " + myRequestURL);
        return myRequestURL;

    }
 */

    /**
     * Override the onCreateLoader for async url fetch
     */
    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int i, Bundle bundle) {
        Log.v(LOG_TAG, "OnCreateLoader");
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        //collecting preference settings
        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_orderby_key),
                getString(R.string.settings_orderby_default)
        );

        String section = sharedPrefs.getString(
                getString(R.string.settings_section_key),
                getString(R.string.settings_section_default)
        );

        String fromDate = sharedPrefs.getString(
                getString(R.string.settings_fromdate_key),
                getString(R.string.settings_fromdate_default));

        String toDate = sharedPrefs.getString(
                getString(R.string.settings_todate_key),
                getString(R.string.settings_todate_default));

        // parse breaks apart the URI string that's passed into its parameter
        Uri baseUri = Uri.parse(NEWS_URL_ROOT);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        // Append query parameter and its value. For example, the `format=geojson`
        uriBuilder.appendQueryParameter("api-key", apiKey);
        uriBuilder.appendQueryParameter("show-tags", "contributor");

        String orderByNone = getString(R.string.settings_orderby_none_value);

        if (!orderBy.equalsIgnoreCase(orderByNone))
            uriBuilder.appendQueryParameter("order-by", orderBy);
        if (!fromDate.isEmpty())
            uriBuilder.appendQueryParameter("from-date", fromDate);
        if (!toDate.isEmpty())
            uriBuilder.appendQueryParameter("to-date", toDate);

        uriBuilder.appendQueryParameter("q", section);

        Log.v(LOG_TAG, uriBuilder.toString());
        return new NewsArticleLoader(this, uriBuilder.toString());

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


        if(data.size() > 0) {
            _recyclerView.setVisibility(View.VISIBLE);
            _emptyStateTextView.setVisibility(View.GONE);
            _myAdapter = new NewsArticleRecyclerAdapter(this, data);
            _recyclerView.setAdapter(_myAdapter);
        }
        else{
            _emptyStateTextView.setVisibility(View.VISIBLE);
            _recyclerView.setVisibility(View.GONE);
        }

    }

    /**
     * Override the onLoaderReset for async url fetch
     */
    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {
        Log.v(LOG_TAG, "OnLoaderReset");

    }

    /**
     * Restart loader action when swiper fresh's triggered
     *
     * @param loaderId int Id for the loader defined above.
     */
    private void restartLoader(int loaderId) {
        Log.v(LOG_TAG, "restarting my loader now");
        getLoaderManager().destroyLoader(loaderId);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(loaderId, null, this);
    }

    private boolean checkNetworkConnection(){
        //if there's no connectivity, then display No internet connection msg, otherwise, create the instance of loader
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


}


