package com.petina.android.audioplayer;

import android.widget.ImageView;
import android.widget.TextView;

public class Song {

    private String title;
    private String artist;
    private int orderId;
    private String length;
    private int albumId;
    private int id;

    public Song(int vId, int vAlbumId, String vTitle, String vArtist, int vOrderId, String vLength) {
        albumId = vAlbumId;
        id = vId;
        title = vTitle;
        artist = vArtist;
        orderId = vOrderId;
        length = vLength;
    }

    /**
     * Get the title of the song
     */
    public String getSongTitle() {
        return title;
    }

    /**
     * Get the artist of the Song
     */
    public String getSongArtist() {
        return artist;
    }

    /**
     * Get the order of the song in an album
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Get the length/duration of a song
     */
    public String getSongLength() {
        return length;
    }

    /**
     * Get the album Id
     */

    public int getAlbumId() {
        return albumId;
    }

    /**
     * Get the song Id
     */
    public int getSongId() {
        return id;
    }


}

