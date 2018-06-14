package com.petina.android.audioplayer;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {
    private Song mySong;
    private Activity mContext;

    public SongAdapter(Activity context, ArrayList<Song> songs) {
        super(context, 0, songs);
        mContext = context;

    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SongViewHolder holder;
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_list, parent, false);

            holder = new SongViewHolder();
            holder.songArtist = (TextView) listItemView.findViewById(R.id.song_artist_id);
            holder.songTitle = (TextView) listItemView.findViewById(R.id.song_title_id);
            holder.songLength = (TextView) listItemView.findViewById(R.id.song_length_id);
            listItemView.setTag(holder);

        } else {
            holder = (SongViewHolder) listItemView.getTag();


        }
        mySong = getItem(position);


        String artist_label = mContext.getString(R.string.song_artist_label);
        holder.songArtist.setText(String.format(artist_label, mySong.getSongArtist()));

        String song_title = mContext.getString(R.string.song_list_title_2);
        holder.songTitle.setText(String.format(song_title, mySong.getOrderId(), mySong.getSongTitle()));


        String song_length = mContext.getString(R.string.song_list_duration_2);
        holder.songLength.setText(String.format(song_length, mySong.getSongLength()));
        return listItemView;
    }

    private static class SongViewHolder {
        private TextView songArtist;
        private TextView songTitle;
        private TextView songLength;
    }


}



