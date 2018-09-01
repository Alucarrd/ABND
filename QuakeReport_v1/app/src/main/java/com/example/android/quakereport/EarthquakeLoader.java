package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<QuakeReport>> {
    private String url;
    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    public EarthquakeLoader(Context vContext, String vURL){
        super(vContext);
        url = vURL;

    }

    @Override
    protected void onStartLoading() {
        Log.v("loaderLogging", "OnStartLoading");
        forceLoad();
    }

    @Override
    public List<QuakeReport> loadInBackground() {
        // TODO: Implement this method
        Log.v("loaderLogging", "LoadInBackground");
        if (url == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<QuakeReport> earthquakes = QueryUtils.fetchEarthquakeData(url);
        return earthquakes;
    }
}



