package com.example.petertan.audioplayertest;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Handler;


import java.util.concurrent.TimeUnit;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private double startTime = 0;
    private Handler myHandler = new Handler();
    private double finalTime = 0;
    private SeekBar seekbar;
    private TextView durationTxt;
    MediaPlayer mySong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySong = MediaPlayer.create(this, R.raw.sampleaudio_04mb);
        setContentView(R.layout.activity_main);
        Button playButton = (Button) findViewById(R.id.play);
        Button pauseButton = (Button) findViewById(R.id.pause);
        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setClickable(false);
        durationTxt = (TextView) findViewById(R.id.duration);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               playSong();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseSong();
            }
        });
        seekbar.setProgress((int)startTime);
        myHandler.postDelayed(UpdateSongTime,100);
    }



    public void playSong(){
        mySong.start();

    }
    public void pauseSong(){
        mySong.pause();
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mySong.getCurrentPosition();
            durationTxt.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };
}
