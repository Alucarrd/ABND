package com.petina.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class NewsArticleLoader extends AsyncTaskLoader<List<NewsArticle>> {
    private String _url;
    private static final String LOG_TAG = NewsArticleLoader.class.getName();

    //constructor for loader class
    public NewsArticleLoader(Context context, String url) {
        super(context);
        _url = url;
    }

    /**
     * Override method for onStartLoading
     */
    @Override
    protected void onStartLoading() {
        Log.v(LOG_TAG, "LoaderLogging");
        forceLoad();
    }

    /**
     * Override method for loadInBackground
     */
    @Override
    public List<NewsArticle> loadInBackground() {
        Log.v(LOG_TAG, "Load in background");
        if (_url == null)
            return null;
        List<NewsArticle> newsArticles = WebHelper.fetchNewsArticle(_url);
        return newsArticles;
    }


}



