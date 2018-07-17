package com.petina.android.tourguide;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        AttractionFragmentPagerAdapter adapter = new AttractionFragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        // Find the tab layout that shows the tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        //Link up tabLayout object with viewPager
        tabLayout.setupWithViewPager(viewPager);

        //Hook up each tab with a custom image view in order to control the size of the tab icon
        View tabView0 = LayoutInflater.from(this).inflate(R.layout.tab, null);
        ImageView imageView0 = (ImageView) tabView0.findViewById(R.id.tab_icon);
        imageView0.setImageResource(R.drawable.baseline_place_white_24dp);
        tabLayout.getTabAt(0).setCustomView(tabView0);

        View tabView1 = LayoutInflater.from(this).inflate(R.layout.tab, null);
        ImageView imageView1 = (ImageView) tabView1.findViewById(R.id.tab_icon);
        imageView1.setImageResource(R.drawable.baseline_restaurant_white_24dp);
        tabLayout.getTabAt(1).setCustomView(tabView1);

        View tabView2 = LayoutInflater.from(this).inflate(R.layout.tab, null);
        ImageView imageView2 = (ImageView) tabView2.findViewById(R.id.tab_icon);
        imageView2.setImageResource(R.drawable.ice_cream_24);
        tabLayout.getTabAt(2).setCustomView(tabView2);

        View tabView3 = LayoutInflater.from(this).inflate(R.layout.tab, null);
        ImageView imageView3 = (ImageView) tabView3.findViewById(R.id.tab_icon);
        imageView3.setImageResource(R.drawable.baseline_hotel_white_24dp);
        tabLayout.getTabAt(3).setCustomView(tabView3);


    }
}
