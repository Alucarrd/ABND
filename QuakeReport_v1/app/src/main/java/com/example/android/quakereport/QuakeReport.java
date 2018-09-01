package com.example.android.quakereport;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuakeReport {
    private double magnitude;
    private String city;
    private long eventTimeMilli;
    private String url;


    public QuakeReport(double vMagnitude, String vCity, long vEventTimeMilli, String vURL){
        magnitude = vMagnitude;
        city = vCity;
        eventTimeMilli = vEventTimeMilli;
        url = vURL;


    }

    public double getMagnitude(){
        return magnitude;

    }
    public String getMagnitudeStr(){
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(magnitude);
        return output;
    }

    public String getCity() {
        //Log.v("QuakeReport", "before split:" + city);
        String[] cityArray = city.split("of");
        //Log.v("QuakeReport", "after split:" + cityArray[0]);
        if(cityArray.length > 1)
            return cityArray[1].trim();
        else
            return city;


    }
    public String getUrl(){
        return url;
    }

    public long getEventTimeMilli() {
        return eventTimeMilli;
    }
    private String getTimeFormat(String formatTemplate){
        Date dateObject = new Date(eventTimeMilli);
        SimpleDateFormat dateFormatter = new SimpleDateFormat(formatTemplate);
        String dateToDisplay = dateFormatter.format(dateObject);
        return dateToDisplay;
    }
    public String getEventTime(){
        return getTimeFormat("h:mm a");
    }

    public String getEventDate(){
        return getTimeFormat("LLL dd, yyyy");
    }
}

