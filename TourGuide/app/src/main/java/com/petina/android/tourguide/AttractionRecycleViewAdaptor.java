package com.petina.android.tourguide;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


public class AttractionRecycleViewAdaptor extends RecyclerView.Adapter<AttractionRecycleViewAdaptor.ViewHolder> {
    private Context myContext;
    private final ArrayList<Attraction> myAttractionList;


    private static final int SHORT_DESCRIPTION_COUNT = 150;
    //inner class ViewHolder for item view
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView myName, myDescription, myRating, myPrice, myStyleText;
        public ImageView myAttractionImage;

        public ViewHolder(View view) {
            super(view);
            myName = (TextView) view.findViewById(R.id.name_id);
            myDescription = (TextView) view.findViewById(R.id.description_id);
            myRating = (TextView) view.findViewById(R.id.rating_id);
            myPrice = (TextView) view.findViewById(R.id.price_id);
            myStyleText = (TextView) view.findViewById(R.id.style_id);
            myAttractionImage = (ImageView) view.findViewById(R.id.image_id);

        }
    }

    //constructor for AttractionRecycleViewAdaptor
    public AttractionRecycleViewAdaptor(Context context, ArrayList<Attraction> vMyAttraction) {

        myAttractionList = vMyAttraction;
        myContext = context;
    }

    //access layout xml and return ViewHolder object
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    //bind ViewHolder with data
    @Override
    public void onBindViewHolder(ViewHolder vMyViewHolder, int position) {
        final Attraction myAttraction = myAttractionList.get(position);

        int resourceId = myContext.getResources().getIdentifier(
                myAttraction.getPictureName().replace(".jpg", "")
                , "drawable", "com.petina.android.tourguide");

        vMyViewHolder.myAttractionImage.setImageResource(resourceId);

        vMyViewHolder.myName.setText(myAttraction.getAttractionName());


        vMyViewHolder.myDescription.setText(myAttraction.getShortDescription(SHORT_DESCRIPTION_COUNT));

        String ratingTemplate = myContext.getString(R.string.rating_template);


        vMyViewHolder.myRating.setText(String.format(ratingTemplate, myAttraction.getRating()));


        String myPriceIndicator = myAttraction.getPriceIndicator();


        if (myPriceIndicator.equalsIgnoreCase("")) {
            vMyViewHolder.myPrice.setVisibility(View.GONE);
        } else {
            vMyViewHolder.myPrice.setText(myPriceIndicator);
        }

        String myStyle = myAttraction.getStyle();
        if (myStyle.equalsIgnoreCase("")) {
            vMyViewHolder.myStyleText.setVisibility(View.GONE);
        } else {
            vMyViewHolder.myStyleText.setText(myStyle);
        }

        vMyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent movingIntent = new Intent(myContext, AttractionDetail.class);
                movingIntent.putExtra("type", myAttraction.getAttractionType());
                movingIntent.putExtra("id", myAttraction.getId());
                myContext.startActivity(movingIntent);
            }
        });
    }

    //return the size for ArrayList object
    @Override
    public int getItemCount() {
        return myAttractionList.size();
    }


}