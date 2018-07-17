package com.petina.android.tourguide;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AttractionDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_detail);

        //Fetch for id/type passed in from intent
        int id = getIntent().getExtras().getInt("id");
        String type = getIntent().getExtras().getString("type");

        //Load my attraction based on id and type from intent.
        final Attraction myAttraction = new DataStore(this).getAttractionByTypeAndId(type, id);

        //Loading UI elements
        ImageView myMainImage = (ImageView) findViewById(R.id.detail_image_id);
        int resourceId = getResources().getIdentifier(
                myAttraction.getPictureName().replace(".jpg", "")
                , "drawable", "com.petina.android.tourguide");

        myMainImage.setImageResource(resourceId);

        TextView myName = (TextView) findViewById(R.id.detail_name_id);
        myName.setText(myAttraction.getAttractionName());

        TextView myDetail = (TextView) findViewById(R.id.detail_description_id);
        myDetail.setText(myAttraction.getDescription());


        TextView myPhone = (TextView) findViewById(R.id.detail_phone_id);
        myPhone.setText(myAttraction.getPhone());


        TextView myAddress = (TextView) findViewById(R.id.detail_address_id);
        myAddress.setText(myAttraction.getAddress());

        TextView myStyle = (TextView) findViewById(R.id.detail_style_id);

        if (myAttraction.getPriceIndicator().equalsIgnoreCase("")) {
            myStyle.setVisibility(View.GONE);

        } else {

            myStyle.setText(myAttraction.getStyle());
        }


        TextView myPrice = (TextView) findViewById(R.id.detail_price_id);

        if (myAttraction.getPriceIndicator().equalsIgnoreCase("")) {
            myPrice.setVisibility(View.GONE);

        } else {

            myPrice.setText(myAttraction.getPriceIndicator());
        }


        TextView myRating = (TextView) findViewById(R.id.detail_rating_id);
        String myRatingTemplate = getString(R.string.rating_template);
        myRating.setText(String.format(myRatingTemplate, myAttraction.getRating()));

        //Setting up the onClickListeners
        View myPhoneLinearView = findViewById(R.id.detail_action_dial);
        myPhoneLinearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + myAttraction.getPhone()));
                startActivity(intent);

            }
        });


        View myAddressLinearView = findViewById(R.id.detail_action_direction);
        myAddressLinearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + myAttraction.getAddress());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

        //If the Yelp link is empty, then remove it off UI.  Else, setup the onClickListner
        View myYelpSeparator = findViewById(R.id.detail_yelp_separator);
        View myYelpLinearView = findViewById(R.id.detail_action_yelp);
        if (myAttraction.getYelpURL().equalsIgnoreCase("")) {
            myYelpLinearView.setVisibility(View.GONE);
            myYelpSeparator.setVisibility(View.GONE);
        } else {
            myYelpLinearView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = myAttraction.getYelpURL();
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        }


    }
}
