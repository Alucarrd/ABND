package com.petina.android.tourguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AttractionFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context context;

    public AttractionFragmentPagerAdapter(Context vContext, FragmentManager fm) {
        super(fm);
        context = vContext;
    }

    /**
     * Return the Fragment based on passed in position
     *
     * @param position index of the fragment
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AttractionFragment();
        } else if (position == 1) {
            return new RestaurantFragment();
        } else if (position == 2) {
            return new GelatoFragment();
        } else if (position == 3) {
            return new HotelFragment();
        } else {
            return new AttractionFragment();
        }
    }

    /**
     * @return hardcoded count for the number of tabs
     */
    @Override
    public int getCount() {
        return 4;
    }

}
