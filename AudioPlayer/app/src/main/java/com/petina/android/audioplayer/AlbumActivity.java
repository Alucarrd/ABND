package com.petina.android.audioplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {
    private Intent movingIntent;
    private ImageView backButton;
    private ImageView searchImage;

    private ImageView albumImage;
    private TextView albumTitle;
    private TextView albumArtist;
    private SongAdapter adapter;
    private Album myAlbum;
    private ListView listView;
    private int myAlbumId;
    private int mySongId;
    private int resourceId;

    DataStore myDataStore = new DataStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        backButton = findViewById(R.id.back_btn_id);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movingIntent = new Intent(AlbumActivity.this, MainActivity.class);
                startActivity(movingIntent);
            }
        });

        searchImage = findViewById(R.id.search_btn_id);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movingIntent = new Intent(AlbumActivity.this, Search.class);
                startActivity(movingIntent);
            }
        });



        myAlbumId = getIntent().getExtras().getInt("album_id");
        myAlbum = myDataStore.getAlbumById(myAlbumId);
        final ArrayList<Song> mySongs = myDataStore.getSongsByAlbumId(myAlbumId);
        albumImage = findViewById(R.id.album_image_id);
        resourceId = getResources().getIdentifier(
                myAlbum.getAlbumImage().replace(".jpg", "")
                , "drawable", "com.petina.android.audioplayer");

        albumImage.setImageResource(resourceId);

        albumTitle = (TextView) findViewById(R.id.album_title_id);
        albumTitle.setText(myAlbum.getAlbumTitle());

        albumArtist = (TextView) findViewById(R.id.album_artist_id);
        albumArtist.setText(getString(R.string.artist_label, myAlbum.getArtist()));

        adapter = new SongAdapter(this, mySongs);

        listView = (ListView) findViewById(R.id.song_list_id);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mySongId = mySongs.get(position).getSongId();
                movingIntent = new Intent(AlbumActivity.this, SongActivity.class);
                movingIntent.putExtra("song_id", mySongId);
                startActivity(movingIntent);

            }
        });


    }
}
