package com.petina.android.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class WebHelper {

    //setup constant
    public static final String LOG_TAG = WebHelper.class.getSimpleName();


    /**
     * Takes in string URL and create an URL object
     * @param strURL url in string format
     * @return url in URL object
     * @throws MalformedURLException
     */
    private static URL createUrl(String strURL) {
        URL url = null;
        try {
            url = new URL(strURL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL:", e);
        }
        return url;
    }

    /**
     * Helper method to read from input stream and output String
     * @param inputStream
     * @return parsed result from inputStream
     * @throws IOException
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();


    }

    /**
     * extract data from json object
     * @param rawJSON -> String format for the json file
     * @return List of NewsArticle objects
     * @throws JSONException
     */
    private static List<NewsArticle> extractFeatureFromJson(String rawJSON) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(rawJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<NewsArticle> newsArticles = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // Create a JSONObject from the JSON response string
            Log.v(LOG_TAG, "RawJson");
            Log.v(LOG_TAG, rawJSON);
            JSONObject baseJsonResponse = new JSONObject(rawJSON);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of results (or newsArticle).

            JSONArray newsArticleArray = baseJsonResponse.getJSONObject("response").getJSONArray("results");

            //looping through each article
            for (int i = 0; i < newsArticleArray.length(); i++) {
                JSONObject currentArticle = newsArticleArray.getJSONObject(i);
                String sectionName = currentArticle.getString("sectionName");
                String type = currentArticle.getString("type");
                String publicationDate = currentArticle.getString("webPublicationDate");
                String title = currentArticle.getString("webTitle");
                String url = currentArticle.getString("webUrl");
                JSONArray tagArray = currentArticle.getJSONArray("tags");
                String contributors = "";
                for (int j = 0; j < tagArray.length(); j++) {
                    JSONObject currentTag = tagArray.getJSONObject(j);
                    String contributor = currentTag.getString("webTitle");
                    contributors += (contributors == "") ? contributors : (contributors + ", " + contributor);
                }

                newsArticles.add(new NewsArticle(type, sectionName, publicationDate, title, contributors, url));


            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the newsArticle result", e);
        }
        return newsArticles;
    }

    /**
     * Helper function to make http call
     * @param url String url format where
     * @return json response from the url call
     * @throws IOException
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        //if the URL is null then return early
        if (url == null) {
            return null;
        }

        //create connection object
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;


        try {
            //making http connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code:" + String.valueOf(urlConnection.getResponseCode()));
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving HTTP call result:", e);
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
            // Closing the input stream could throw an IOException, which is why
            // the makeHttpRequest(URL url) method signature specifies than an IOException
            // could be thrown.
            if (inputStream != null)
                inputStream.close();
        }
        return jsonResponse;
    }

    /**
     * helper function to call guardian web api
     * @param requestURL => guardian news web api url in string format
     * @return list of NewsArticle objects
     * @throws IOException
     */
    public static List<NewsArticle> fetchNewsArticle(String requestURL) {
        //Create URL Object
        URL url = createUrl(requestURL);
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);

        }
        List<NewsArticle> newsArticles = extractFeatureFromJson(jsonResponse);
        return newsArticles;
    }

}
