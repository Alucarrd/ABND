package com.petina.android.tourguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class AttractionFragment extends Fragment {
    private RecyclerView recyclerView;
    private AttractionRecycleViewAdaptor mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.attraction_list, container, false);

        //Fetch for attraction list
        final ArrayList<Attraction> myAttractionList = new DataStore(getActivity()).getAttractionList();

        //Load adaptor from the attraction list

        mAdapter = new AttractionRecycleViewAdaptor(getActivity(), myAttractionList);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        return rootView;
    }


}
