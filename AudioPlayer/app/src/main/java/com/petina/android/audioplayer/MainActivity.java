package com.petina.android.audioplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageView searchImage;
    private Intent movingIntent;

    private DataStore myDataStore;
    private AlbumAdapter adapter;
    private GridView gridView;

    private int myAlbumId;
    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchImage = (ImageView) findViewById(R.id.search_btn_id);

        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movingIntent = new Intent(MainActivity.this, Search.class);
                startActivity(movingIntent);
            }
        });


        myDataStore = new DataStore();
        final ArrayList<Album> albums = myDataStore.getAlbums();



        adapter = new AlbumAdapter(this, albums);

        gridView = (GridView) findViewById(R.id.list);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the GridView selected/clicked item text
                myAlbumId = albums.get(position).getId();
                selectedItem = parent.getItemAtPosition(position).toString();
                movingIntent = new Intent(MainActivity.this, AlbumActivity.class);
                movingIntent.putExtra("album_id", myAlbumId);
                startActivity(movingIntent);
            }
        });


    }
}
