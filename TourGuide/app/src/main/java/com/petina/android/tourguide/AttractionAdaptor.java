package com.petina.android.tourguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class AttractionAdaptor extends ArrayAdapter<Attraction> {
    private Context myContext;
    private static final int SHORT_DESCRIPTION_COUNT = 150;

    public AttractionAdaptor(Activity context, ArrayList<Attraction> vMyAttraction) {
        super(context, 0, vMyAttraction);
        myContext = context;
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
                    R.layout.list_item, parent, false);

        }

        Attraction myAttraction = getItem(position);

        ImageView myAttractionImage = (ImageView) listItemView.findViewById(R.id.image_id);

        int resourceId = myContext.getResources().getIdentifier(
                myAttraction.getPictureName().replace(".jpg", "")
                , "drawable", "com.petina.android.tourguide");

        myAttractionImage.setImageResource(resourceId);

        TextView myName = (TextView) listItemView.findViewById(R.id.name_id);
        myName.setText(myAttraction.getAttractionName());

        TextView myDescription = (TextView) listItemView.findViewById(R.id.description_id);
        myDescription.setText(myAttraction.getShortDescription(SHORT_DESCRIPTION_COUNT));

        String ratingTemplate = myContext.getString(R.string.rating_template);

        TextView myRating = (TextView) listItemView.findViewById(R.id.rating_id);
        myRating.setText(String.format(ratingTemplate, myAttraction.getRating()));

        TextView myPrice = (TextView) listItemView.findViewById(R.id.price_id);
        String myPriceIndicator = myAttraction.getPriceIndicator();


        if (myPriceIndicator.equalsIgnoreCase("")) {
            myPrice.setVisibility(View.GONE);
        } else {
            myPrice.setText(myPriceIndicator);
        }

        String myStyle = myAttraction.getStyle();
        TextView myStyleText = (TextView) listItemView.findViewById(R.id.style_id);
        if (myStyle.equalsIgnoreCase("")) {
            myStyleText.setVisibility(View.GONE);
        } else {
            myStyleText.setText(myStyle);
        }

        return listItemView;
    }


}
