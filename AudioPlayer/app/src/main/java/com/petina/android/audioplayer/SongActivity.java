package com.petina.android.audioplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SongActivity extends AppCompatActivity {
    private DataStore myDataStore = new DataStore();
    private int songId;
    private int albumId;
    private int songOrderId;
    private int maxOrderId;
    private Song mySong;
    private Album myAlbum;

    private Intent movingIntent;
    private ImageView albumImage;
    private ImageView backButton;
    private ImageView searchImage;
    private ImageView playImage;
    private ImageView backImage;
    private ImageView fastForwardImage;
    private TextView albumTitle;
    private TextView songTitle;
    private TextView songArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        songId = getIntent().getExtras().getInt("song_id");

        mySong = myDataStore.getSongById(songId);

        refreshActivity(mySong);

        backButton = (ImageView) findViewById(R.id.back_btn_id);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movingIntent = new Intent(SongActivity.this, MainActivity.class);
                startActivity(movingIntent);
            }
        });

        searchImage = (ImageView) findViewById(R.id.search_btn_id);

        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movingIntent = new Intent(SongActivity.this, Search.class);
                startActivity(movingIntent);
            }
        });

        playImage = (ImageView) findViewById(R.id.song_play_id);
        playImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getString(R.string.current_question, Integer.toString(currentQuestion));
                String msg = getString(R.string.play_underconstruction);
                popToast(msg);
            }
        });

        //paint back and forward song
        backImage = (ImageView) findViewById(R.id.song_back_id);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backOrderId = getPreviousOrderId(mySong.getOrderId(), maxOrderId);
                mySong = myDataStore.getSongByOrderAndAlbumId(backOrderId, albumId);
                refreshActivity(mySong);
            }
        });


        fastForwardImage = (ImageView) findViewById(R.id.song_forward_id);
        fastForwardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int forwardOrderId = getNextOrderId(mySong.getOrderId(), maxOrderId);
                mySong = myDataStore.getSongByOrderAndAlbumId(forwardOrderId, albumId);
                refreshActivity(mySong);
            }
        });


    }

    /**
     * repaint the song activity page
     *
     * @param vMySong the current song object
     */
    public void refreshActivity(Song vMySong) {
        /*
        2. get album object
         */

        myAlbum = myDataStore.getAlbumById(vMySong.getAlbumId());
        songOrderId = vMySong.getOrderId();
        albumId = vMySong.getAlbumId();
        maxOrderId = myDataStore.getMaxOrderId(albumId);
        //load album related data
        albumImage = (ImageView) findViewById(R.id.album_image_id);

        int resourceId = getResources().getIdentifier(
                myAlbum.getAlbumImage().replace(".jpg", "")
                , "drawable", "com.petina.android.audioplayer");

        albumImage.setImageResource(resourceId);

        //load album title
        albumTitle = (TextView) findViewById(R.id.album_name_id);
        albumTitle.setText(getString(R.string.album_label, myAlbum.getAlbumTitle()));

        String songLabel = getString(R.string.song_label, mySong.getOrderId(), mySong.getSongTitle(), mySong.getSongLength());

        songTitle = (TextView) findViewById(R.id.song_title_id);
        songTitle.setText(songLabel);


        songArtist = (TextView) findViewById(R.id.song_artist_id);
        songArtist.setText(getString(R.string.artist_label, mySong.getSongArtist()));


    }

    /**
     * Based on the current index of the song and the total songs in an album,
     * return the previous song's index. If giving order id is the first song,
     * then it will return the last song in the same album
     *
     * @param curOrderId the current order id (index) of the song in an album
     * @param maxOrderId the max (highest) order Id (index) of the songs in an album
     * @return the order id of the song for the previous song
     */
    public int getPreviousOrderId(int curOrderId, int maxOrderId) {
        curOrderId--;
        if (curOrderId == 0)
            curOrderId = maxOrderId;
        return curOrderId;
    }

    /**
     * Based on the current index of the song and the total songs in an album,
     * return the next song's index. If giving order id is the last song,
     * then it will return the first song in the album
     *
     * @param curOrderId the current order id (index) of the song in an album
     * @param maxOrderId the max (highest) order Id (index) of the songs in an album
     * @return the order id of the song for the next song
     */
    public int getNextOrderId(int curOrderId, int maxOrderId) {
        curOrderId++;
        if (curOrderId > maxOrderId)
            curOrderId = 1;
        return curOrderId;
    }

    /**
     * pop the toast msg
     *
     * @param msg the string msg for toast msg
     */
    public void popToast(String msg) {
        Toast.makeText(SongActivity.this, msg,
                Toast.LENGTH_LONG).show();
    }
}
