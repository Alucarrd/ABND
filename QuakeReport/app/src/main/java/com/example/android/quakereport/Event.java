package com.example.android.quakereport;


/**
 * {@Event} represents an earthquake event.
 */
public class Event {

    /** Title of the earthquake event */


    public final double mag;
    public final String place;
    public final long time;
    public final String url;



    /**
     * Constructs a new {@link Event}.
     *
     * @param vMag is the title of the earthquake event
     * @param vPlace is the number of people who felt the earthquake and reported how
     *                         strong it was
     * @param vTime is the perceived strength of the earthquake from the responses
     * @param vUrl is the perceived strength of the earthquake from the responses
     */
    public Event(double vMag, String vPlace, long vTime, String vUrl) {
        mag = vMag;
        place = vPlace;
        time = vTime;
        url = vUrl;
    }
}
