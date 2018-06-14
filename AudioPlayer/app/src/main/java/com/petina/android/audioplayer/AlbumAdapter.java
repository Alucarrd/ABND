package com.petina.android.audioplayer;

import android.content.Context;
import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumAdapter extends ArrayAdapter<Album> {
    private Activity mContext;
    private ImageView myAlbumImage;
    private TextView myAlbumTitle;
    private Album myAlbum;

    public AlbumAdapter(Activity context, ArrayList<Album> albums) {
        super(context, 0, albums);
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
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.album_list, parent, false);

        }
        myAlbum = getItem(position);
        myAlbumTitle = (TextView) listItemView.findViewById(R.id.album_title);
        myAlbumTitle.setText(myAlbum.getAlbumTitle());

        myAlbumImage = (ImageView) listItemView.findViewById(R.id.album_image);
        Log.i("albumAdapter", "My Album image is " + myAlbum.getAlbumImage());
        int resourceId = mContext.getResources().getIdentifier(
                myAlbum.getAlbumImage().replace(".jpg", "")
                , "drawable", "com.petina.android.audioplayer");

        myAlbumImage.setImageResource(resourceId);

        return listItemView;
    }


}



