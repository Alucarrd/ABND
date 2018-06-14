package com.petina.android.audioplayer;

import android.content.res.Resources;
import android.content.res.AssetManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Album {

    private String title;
    private String image;
    private String artist;
    private String record;
    private int id;


    public Album(int vId, String vTitle, String vImage, String vArtist, String vRecord) {
        title = vTitle;
        image = vImage;
        artist = vArtist;
        record = vRecord;
        id = vId;
    }
    /**
     * Return title of the album
     */
    public String getAlbumTitle() {
        return title;
    }
    /**
     * Return the image name of the album
     */
    public String getAlbumImage() {
        return image;
    }
    /**
     * Return the album artists
     */
    public String getArtist() {
        return artist;
    }
    /**
     * Return the album id
     */
    public int getId() {
        return id;
    }


}
