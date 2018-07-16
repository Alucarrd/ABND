package com.petina.android.tourguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AttractionFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    public AttractionFragmentPagerAdapter(Context vContext, FragmentManager fm){
        super(fm);
        context = vContext;
    }

    @Override
    public Fragment getItem(int position){
        if(position == 0){
            return new AttractionFragment();
        }
        else if(position == 1){
            return new RestaurantFragment();
        }
        else if(position == 2){
            return new GelatoFragment();
        }
        else if(position == 3){
            return new HotelFragment();
        }
        else{
            return new AttractionFragment();
        }
    }
    @Override
    public int getCount(){
        return 4;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.category_attraction);
        } else if (position == 1) {
            return context.getString(R.string.category_restaurant);
        } else if (position == 2) {
            return context.getString(R.string.category_gelato);
        } else {
            return context.getString(R.string.category_hotel);
        }
    }
}
