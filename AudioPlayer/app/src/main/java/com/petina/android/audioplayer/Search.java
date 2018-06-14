package com.petina.android.audioplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View.OnKeyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    private ListView listView;
    private String searchPhrase = "";
    private DataStore myDataStore = new DataStore();
    private ArrayList<Song> mySongs = new ArrayList<Song>();
    private TextView searchLabel;
    private ImageView backButton;
    private ImageView searchImage;
    private Intent movingIntent;
    private EditText mySearchBox;

    private int mySongId;


    private Boolean searchState = false;

    /**
     * Trigger search against the list
     */
    private void searchAction() {
        searchState = true;

        searchPhrase = mySearchBox.getText().toString();

        mySongs = myDataStore.searchSongByPhrase(searchPhrase);


        if (mySongs.size() > 0) {
            searchLabel.setVisibility(View.GONE);

            SongAdapter adapter = new SongAdapter(Search.this, mySongs);

            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mySongId = mySongs.get(position).getSongId();
                    movingIntent = new Intent(Search.this, SongActivity.class);
                    movingIntent.putExtra("song_id", mySongId);
                    startActivity(movingIntent);

                }
            });
        } else {

            searchLabel.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            searchLabel.setText(getString(R.string.search_not_found));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mySearchBox = (EditText) findViewById(R.id.search_text_id);
        listView = (ListView) findViewById(R.id.search_result_id);
        backButton = (ImageView) findViewById(R.id.back_btn_id);
        searchLabel = (TextView) findViewById(R.id.search_label_id);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movingIntent = new Intent(Search.this, MainActivity.class);
                startActivity(movingIntent);
            }
        });

        searchImage = (ImageView) findViewById(R.id.search_btn_id);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAction();
            }
        });
        mySearchBox.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    searchAction();
                    return true;
                }
                return false;
            }
        });


    }
}
