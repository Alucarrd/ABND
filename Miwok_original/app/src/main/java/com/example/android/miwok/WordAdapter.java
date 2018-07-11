package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word>{
    private int mBackgroundColor;
    public WordAdapter(Activity context, ArrayList<Word> words, int backgroundColor){
        super(context, 0, words);
        mBackgroundColor = backgroundColor;
    }
    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

        }
        int color = ContextCompat.getColor(getContext(), mBackgroundColor);
        listItemView.setBackgroundColor(color);
        Word myWord = getItem(position);
        TextView myMiWok = (TextView) listItemView.findViewById(R.id.miwok_item);
        myMiWok.setText(myWord.getMiwokTranslation());

        TextView myDefaultWord = (TextView) listItemView.findViewById(R.id.default_item);
        myDefaultWord.setText(myWord.getDefaultTranslation());
        ImageView myResourceImage = (ImageView) listItemView.findViewById(R.id.image_icon);
        if (myWord.hasImage()){

            myResourceImage.setImageResource(myWord.getmResourceId());
            myResourceImage.setVisibility(View.VISIBLE);
        }
        else

        {
            myResourceImage.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
