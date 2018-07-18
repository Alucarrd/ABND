package com.petina.android.tourguide;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;



public class RestaurantFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.attraction_list, container, false);

        // Fetch for restaurant list
        final ArrayList<Attraction> myRestaurantList = new DataStore(getActivity()).getRestaurantList();

        //Load adaptor from the gelato list
        AttractionAdaptor adapter = new AttractionAdaptor(getActivity(), myRestaurantList);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        //Set up on click listener to pass type/id through intent to detail screen
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Attraction myRestaurant = myRestaurantList.get(position);
                Intent movingIntent = new Intent(getActivity(), AttractionDetail.class);
                movingIntent.putExtra("type", myRestaurant.getAttractionType());
                movingIntent.putExtra("id", myRestaurant.getId());
                startActivity(movingIntent);
            }
        });


        return rootView;
    }

}
