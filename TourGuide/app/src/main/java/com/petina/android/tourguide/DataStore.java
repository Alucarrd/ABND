package com.petina.android.tourguide;

import android.content.Context;
import android.content.ContextWrapper;

import java.util.ArrayList;

public class DataStore extends ContextWrapper {
    private ArrayList<Attraction> attractionList = new ArrayList<Attraction>();
    private ArrayList<Attraction> gelatoList = new ArrayList<Attraction>();
    private ArrayList<Attraction> restaurantList = new ArrayList<Attraction>();
    private ArrayList<Attraction> hotelList = new ArrayList<Attraction>();

    /**
     * Constructor to load hardcoded data
     */
    public DataStore(Context base) {
        super(base);

        hotelList.add(new Attraction(1, "hotel"
                , getResources().getString(R.string.hotel_1_name)
                , getResources().getString(R.string.hotel_1_price)
                , getResources().getString(R.string.hotel_1_address)
                , getResources().getString(R.string.hotel_1_top_comment)
                , getResources().getString(R.string.hotel_1_style)
                , getResources().getString(R.string.hotel_1_phone)
                , getResources().getString(R.string.hotel_1_yelp)
                , getResources().getString(R.string.hotel_1_rating)
                , getResources().getString(R.string.hotel_1_picture)

        ));

        hotelList.add(new Attraction(2, "hotel"
                , getResources().getString(R.string.hotel_2_name)
                , getResources().getString(R.string.hotel_2_price)
                , getResources().getString(R.string.hotel_2_address)
                , getResources().getString(R.string.hotel_2_top_comment)
                , getResources().getString(R.string.hotel_2_style)
                , getResources().getString(R.string.hotel_2_phone)
                , getResources().getString(R.string.hotel_2_yelp)
                , getResources().getString(R.string.hotel_2_rating)
                , getResources().getString(R.string.hotel_2_picture)

        ));

        hotelList.add(new Attraction(3, "hotel"
                , getResources().getString(R.string.hotel_3_name)
                , getResources().getString(R.string.hotel_3_price)
                , getResources().getString(R.string.hotel_3_address)
                , getResources().getString(R.string.hotel_3_top_comment)
                , getResources().getString(R.string.hotel_3_style)
                , getResources().getString(R.string.hotel_3_phone)
                , getResources().getString(R.string.hotel_3_yelp)
                , getResources().getString(R.string.hotel_3_rating)
                , getResources().getString(R.string.hotel_3_picture)

        ));

        hotelList.add(new Attraction(4, "hotel"
                , getResources().getString(R.string.hotel_4_name)
                , getResources().getString(R.string.hotel_4_price)
                , getResources().getString(R.string.hotel_4_address)
                , getResources().getString(R.string.hotel_4_top_comment)
                , getResources().getString(R.string.hotel_4_style)
                , getResources().getString(R.string.hotel_4_phone)
                , getResources().getString(R.string.hotel_4_yelp)
                , getResources().getString(R.string.hotel_4_rating)
                , getResources().getString(R.string.hotel_4_picture)

        ));

        hotelList.add(new Attraction(5, "hotel"
                , getResources().getString(R.string.hotel_5_name)
                , getResources().getString(R.string.hotel_5_price)
                , getResources().getString(R.string.hotel_5_address)
                , getResources().getString(R.string.hotel_5_top_comment)
                , getResources().getString(R.string.hotel_5_style)
                , getResources().getString(R.string.hotel_5_phone)
                , getResources().getString(R.string.hotel_5_yelp)
                , getResources().getString(R.string.hotel_5_rating)
                , getResources().getString(R.string.hotel_5_picture)

        ));

        hotelList.add(new Attraction(6, "hotel"
                , getResources().getString(R.string.hotel_6_name)
                , getResources().getString(R.string.hotel_6_price)
                , getResources().getString(R.string.hotel_6_address)
                , getResources().getString(R.string.hotel_6_top_comment)
                , getResources().getString(R.string.hotel_6_style)
                , getResources().getString(R.string.hotel_6_phone)
                , getResources().getString(R.string.hotel_6_yelp)
                , getResources().getString(R.string.hotel_6_rating)
                , getResources().getString(R.string.hotel_6_picture)

        ));

        hotelList.add(new Attraction(7, "hotel"
                , getResources().getString(R.string.hotel_7_name)
                , getResources().getString(R.string.hotel_7_price)
                , getResources().getString(R.string.hotel_7_address)
                , getResources().getString(R.string.hotel_7_top_comment)
                , getResources().getString(R.string.hotel_7_style)
                , getResources().getString(R.string.hotel_7_phone)
                , getResources().getString(R.string.hotel_7_yelp)
                , getResources().getString(R.string.hotel_7_rating)
                , getResources().getString(R.string.hotel_7_picture)

        ));

        hotelList.add(new Attraction(8, "hotel"
                , getResources().getString(R.string.hotel_8_name)
                , getResources().getString(R.string.hotel_8_price)
                , getResources().getString(R.string.hotel_8_address)
                , getResources().getString(R.string.hotel_8_top_comment)
                , getResources().getString(R.string.hotel_8_style)
                , getResources().getString(R.string.hotel_8_phone)
                , getResources().getString(R.string.hotel_8_yelp)
                , getResources().getString(R.string.hotel_8_rating)
                , getResources().getString(R.string.hotel_8_picture)

        ));

        hotelList.add(new Attraction(9, "hotel"
                , getResources().getString(R.string.hotel_9_name)
                , getResources().getString(R.string.hotel_9_price)
                , getResources().getString(R.string.hotel_9_address)
                , getResources().getString(R.string.hotel_9_top_comment)
                , getResources().getString(R.string.hotel_9_style)
                , getResources().getString(R.string.hotel_9_phone)
                , getResources().getString(R.string.hotel_9_yelp)
                , getResources().getString(R.string.hotel_9_rating)
                , getResources().getString(R.string.hotel_9_picture)

        ));

        hotelList.add(new Attraction(10, "hotel"
                , getResources().getString(R.string.hotel_10_name)
                , getResources().getString(R.string.hotel_10_price)
                , getResources().getString(R.string.hotel_10_address)
                , getResources().getString(R.string.hotel_10_top_comment)
                , getResources().getString(R.string.hotel_10_style)
                , getResources().getString(R.string.hotel_10_phone)
                , getResources().getString(R.string.hotel_10_yelp)
                , getResources().getString(R.string.hotel_10_rating)
                , getResources().getString(R.string.hotel_10_picture)

        ));

        restaurantList.add(new Attraction(1, "restaurant"
                , getResources().getString(R.string.restaurant_1_name)
                , getResources().getString(R.string.restaurant_1_price)
                , getResources().getString(R.string.restaurant_1_address)
                , getResources().getString(R.string.restaurant_1_top_comment)
                , getResources().getString(R.string.restaurant_1_style)
                , getResources().getString(R.string.restaurant_1_phone)
                , getResources().getString(R.string.restaurant_1_yelp)
                , getResources().getString(R.string.restaurant_1_rating)
                , getResources().getString(R.string.restaurant_1_picture)

        ));

        restaurantList.add(new Attraction(2, "restaurant"
                , getResources().getString(R.string.restaurant_2_name)
                , getResources().getString(R.string.restaurant_2_price)
                , getResources().getString(R.string.restaurant_2_address)
                , getResources().getString(R.string.restaurant_2_top_comment)
                , getResources().getString(R.string.restaurant_2_style)
                , getResources().getString(R.string.restaurant_2_phone)
                , getResources().getString(R.string.restaurant_2_yelp)
                , getResources().getString(R.string.restaurant_2_rating)
                , getResources().getString(R.string.restaurant_2_picture)

        ));

        restaurantList.add(new Attraction(3, "restaurant"
                , getResources().getString(R.string.restaurant_3_name)
                , getResources().getString(R.string.restaurant_3_price)
                , getResources().getString(R.string.restaurant_3_address)
                , getResources().getString(R.string.restaurant_3_top_comment)
                , getResources().getString(R.string.restaurant_3_style)
                , getResources().getString(R.string.restaurant_3_phone)
                , getResources().getString(R.string.restaurant_3_yelp)
                , getResources().getString(R.string.restaurant_3_rating)
                , getResources().getString(R.string.restaurant_3_picture)

        ));

        restaurantList.add(new Attraction(4, "restaurant"
                , getResources().getString(R.string.restaurant_4_name)
                , getResources().getString(R.string.restaurant_4_price)
                , getResources().getString(R.string.restaurant_4_address)
                , getResources().getString(R.string.restaurant_4_top_comment)
                , getResources().getString(R.string.restaurant_4_style)
                , getResources().getString(R.string.restaurant_4_phone)
                , getResources().getString(R.string.restaurant_4_yelp)
                , getResources().getString(R.string.restaurant_4_rating)
                , getResources().getString(R.string.restaurant_4_picture)

        ));

        restaurantList.add(new Attraction(5, "restaurant"
                , getResources().getString(R.string.restaurant_5_name)
                , getResources().getString(R.string.restaurant_5_price)
                , getResources().getString(R.string.restaurant_5_address)
                , getResources().getString(R.string.restaurant_5_top_comment)
                , getResources().getString(R.string.restaurant_5_style)
                , getResources().getString(R.string.restaurant_5_phone)
                , getResources().getString(R.string.restaurant_5_yelp)
                , getResources().getString(R.string.restaurant_5_rating)
                , getResources().getString(R.string.restaurant_5_picture)

        ));

        restaurantList.add(new Attraction(6, "restaurant"
                , getResources().getString(R.string.restaurant_6_name)
                , getResources().getString(R.string.restaurant_6_price)
                , getResources().getString(R.string.restaurant_6_address)
                , getResources().getString(R.string.restaurant_6_top_comment)
                , getResources().getString(R.string.restaurant_6_style)
                , getResources().getString(R.string.restaurant_6_phone)
                , getResources().getString(R.string.restaurant_6_yelp)
                , getResources().getString(R.string.restaurant_6_rating)
                , getResources().getString(R.string.restaurant_6_picture)

        ));

        restaurantList.add(new Attraction(7, "restaurant"
                , getResources().getString(R.string.restaurant_7_name)
                , getResources().getString(R.string.restaurant_7_price)
                , getResources().getString(R.string.restaurant_7_address)
                , getResources().getString(R.string.restaurant_7_top_comment)
                , getResources().getString(R.string.restaurant_7_style)
                , getResources().getString(R.string.restaurant_7_phone)
                , getResources().getString(R.string.restaurant_7_yelp)
                , getResources().getString(R.string.restaurant_7_rating)
                , getResources().getString(R.string.restaurant_7_picture)

        ));

        restaurantList.add(new Attraction(8, "restaurant"
                , getResources().getString(R.string.restaurant_8_name)
                , getResources().getString(R.string.restaurant_8_price)
                , getResources().getString(R.string.restaurant_8_address)
                , getResources().getString(R.string.restaurant_8_top_comment)
                , getResources().getString(R.string.restaurant_8_style)
                , getResources().getString(R.string.restaurant_8_phone)
                , getResources().getString(R.string.restaurant_8_yelp)
                , getResources().getString(R.string.restaurant_8_rating)
                , getResources().getString(R.string.restaurant_8_picture)

        ));

        restaurantList.add(new Attraction(9, "restaurant"
                , getResources().getString(R.string.restaurant_9_name)
                , getResources().getString(R.string.restaurant_9_price)
                , getResources().getString(R.string.restaurant_9_address)
                , getResources().getString(R.string.restaurant_9_top_comment)
                , getResources().getString(R.string.restaurant_9_style)
                , getResources().getString(R.string.restaurant_9_phone)
                , getResources().getString(R.string.restaurant_9_yelp)
                , getResources().getString(R.string.restaurant_9_rating)
                , getResources().getString(R.string.restaurant_9_picture)

        ));

        restaurantList.add(new Attraction(10, "restaurant"
                , getResources().getString(R.string.restaurant_10_name)
                , getResources().getString(R.string.restaurant_10_price)
                , getResources().getString(R.string.restaurant_10_address)
                , getResources().getString(R.string.restaurant_10_top_comment)
                , getResources().getString(R.string.restaurant_10_style)
                , getResources().getString(R.string.restaurant_10_phone)
                , getResources().getString(R.string.restaurant_10_yelp)
                , getResources().getString(R.string.restaurant_10_rating)
                , getResources().getString(R.string.restaurant_10_picture)

        ));

        gelatoList.add(new Attraction(1, "gelato"
                , getResources().getString(R.string.gelato_1_name)
                , getResources().getString(R.string.gelato_1_price)
                , getResources().getString(R.string.gelato_1_address)
                , getResources().getString(R.string.gelato_1_top_comment)
                , getResources().getString(R.string.gelato_1_style)
                , getResources().getString(R.string.gelato_1_phone)
                , getResources().getString(R.string.gelato_1_yelp)
                , getResources().getString(R.string.gelato_1_rating)
                , getResources().getString(R.string.gelato_1_picture)

        ));

        gelatoList.add(new Attraction(2, "gelato"
                , getResources().getString(R.string.gelato_2_name)
                , getResources().getString(R.string.gelato_2_price)
                , getResources().getString(R.string.gelato_2_address)
                , getResources().getString(R.string.gelato_2_top_comment)
                , getResources().getString(R.string.gelato_2_style)
                , getResources().getString(R.string.gelato_2_phone)
                , getResources().getString(R.string.gelato_2_yelp)
                , getResources().getString(R.string.gelato_2_rating)
                , getResources().getString(R.string.gelato_2_picture)

        ));
        gelatoList.add(new Attraction(3, "gelato"
                , getResources().getString(R.string.gelato_3_name)
                , getResources().getString(R.string.gelato_3_price)
                , getResources().getString(R.string.gelato_3_address)
                , getResources().getString(R.string.gelato_3_top_comment)
                , getResources().getString(R.string.gelato_3_style)
                , getResources().getString(R.string.gelato_3_phone)
                , getResources().getString(R.string.gelato_3_yelp)
                , getResources().getString(R.string.gelato_3_rating)
                , getResources().getString(R.string.gelato_3_picture)

        ));
        gelatoList.add(new Attraction(4, "gelato"
                , getResources().getString(R.string.gelato_4_name)
                , getResources().getString(R.string.gelato_4_price)
                , getResources().getString(R.string.gelato_4_address)
                , getResources().getString(R.string.gelato_4_top_comment)
                , getResources().getString(R.string.gelato_4_style)
                , getResources().getString(R.string.gelato_4_phone)
                , getResources().getString(R.string.gelato_4_yelp)
                , getResources().getString(R.string.gelato_4_rating)
                , getResources().getString(R.string.gelato_4_picture)

        ));
        gelatoList.add(new Attraction(5, "gelato"
                , getResources().getString(R.string.gelato_5_name)
                , getResources().getString(R.string.gelato_5_price)
                , getResources().getString(R.string.gelato_5_address)
                , getResources().getString(R.string.gelato_5_top_comment)
                , getResources().getString(R.string.gelato_5_style)
                , getResources().getString(R.string.gelato_5_phone)
                , getResources().getString(R.string.gelato_5_yelp)
                , getResources().getString(R.string.gelato_5_rating)
                , getResources().getString(R.string.gelato_5_picture)

        ));
        gelatoList.add(new Attraction(6, "gelato"
                , getResources().getString(R.string.gelato_6_name)
                , getResources().getString(R.string.gelato_6_price)
                , getResources().getString(R.string.gelato_6_address)
                , getResources().getString(R.string.gelato_6_top_comment)
                , getResources().getString(R.string.gelato_6_style)
                , getResources().getString(R.string.gelato_6_phone)
                , getResources().getString(R.string.gelato_6_yelp)
                , getResources().getString(R.string.gelato_6_rating)
                , getResources().getString(R.string.gelato_6_picture)

        ));
        gelatoList.add(new Attraction(7, "gelato"
                , getResources().getString(R.string.gelato_7_name)
                , getResources().getString(R.string.gelato_7_price)
                , getResources().getString(R.string.gelato_7_address)
                , getResources().getString(R.string.gelato_7_top_comment)
                , getResources().getString(R.string.gelato_7_style)
                , getResources().getString(R.string.gelato_7_phone)
                , getResources().getString(R.string.gelato_7_yelp)
                , getResources().getString(R.string.gelato_7_rating)
                , getResources().getString(R.string.gelato_7_picture)

        ));
        gelatoList.add(new Attraction(8, "gelato"
                , getResources().getString(R.string.gelato_8_name)
                , getResources().getString(R.string.gelato_8_price)
                , getResources().getString(R.string.gelato_8_address)
                , getResources().getString(R.string.gelato_8_top_comment)
                , getResources().getString(R.string.gelato_8_style)
                , getResources().getString(R.string.gelato_8_phone)
                , getResources().getString(R.string.gelato_8_yelp)
                , getResources().getString(R.string.gelato_8_rating)
                , getResources().getString(R.string.gelato_8_picture)

        ));
        gelatoList.add(new Attraction(9, "gelato"
                , getResources().getString(R.string.gelato_9_name)
                , getResources().getString(R.string.gelato_9_price)
                , getResources().getString(R.string.gelato_9_address)
                , getResources().getString(R.string.gelato_9_top_comment)
                , getResources().getString(R.string.gelato_9_style)
                , getResources().getString(R.string.gelato_9_phone)
                , getResources().getString(R.string.gelato_9_yelp)
                , getResources().getString(R.string.gelato_9_rating)
                , getResources().getString(R.string.gelato_9_picture)

        ));

        gelatoList.add(new Attraction(10, "gelato"
                , getResources().getString(R.string.gelato_10_name)
                , getResources().getString(R.string.gelato_10_price)
                , getResources().getString(R.string.gelato_10_address)
                , getResources().getString(R.string.gelato_10_top_comment)
                , getResources().getString(R.string.gelato_10_style)
                , getResources().getString(R.string.gelato_10_phone)
                , getResources().getString(R.string.gelato_10_yelp)
                , getResources().getString(R.string.gelato_10_rating)
                , getResources().getString(R.string.gelato_10_picture)

        ));
        attractionList.add(new Attraction(1, "attraction"
                , getResources().getString(R.string.attraction_1_name)
                , getResources().getString(R.string.attraction_1_description)
                , getResources().getString(R.string.attraction_1_phone)
                , getResources().getString(R.string.attraction_1_address)
                , getResources().getString(R.string.attraction_1_rating)
                , getResources().getString(R.string.attraction_1_picture)
        ));
        attractionList.add(new Attraction(2, "attraction"
                , getResources().getString(R.string.attraction_2_name)
                , getResources().getString(R.string.attraction_2_description)
                , getResources().getString(R.string.attraction_2_phone)
                , getResources().getString(R.string.attraction_2_address)
                , getResources().getString(R.string.attraction_2_rating)
                , getResources().getString(R.string.attraction_2_picture)
        ));
        attractionList.add(new Attraction(3, "attraction"
                , getResources().getString(R.string.attraction_3_name)
                , getResources().getString(R.string.attraction_3_description)
                , getResources().getString(R.string.attraction_3_phone)
                , getResources().getString(R.string.attraction_3_address)
                , getResources().getString(R.string.attraction_3_rating)
                , getResources().getString(R.string.attraction_3_picture)
        ));
        attractionList.add(new Attraction(4, "attraction"
                , getResources().getString(R.string.attraction_4_name)
                , getResources().getString(R.string.attraction_4_description)
                , getResources().getString(R.string.attraction_4_phone)
                , getResources().getString(R.string.attraction_4_address)
                , getResources().getString(R.string.attraction_4_rating)
                , getResources().getString(R.string.attraction_4_picture)
        ));
        attractionList.add(new Attraction(5, "attraction"
                , getResources().getString(R.string.attraction_5_name)
                , getResources().getString(R.string.attraction_5_description)
                , getResources().getString(R.string.attraction_5_phone)
                , getResources().getString(R.string.attraction_5_address)
                , getResources().getString(R.string.attraction_5_rating)
                , getResources().getString(R.string.attraction_5_picture)
        ));
        attractionList.add(new Attraction(6, "attraction"
                , getResources().getString(R.string.attraction_6_name)
                , getResources().getString(R.string.attraction_6_description)
                , getResources().getString(R.string.attraction_6_phone)
                , getResources().getString(R.string.attraction_6_address)
                , getResources().getString(R.string.attraction_6_rating)
                , getResources().getString(R.string.attraction_6_picture)
        ));
        attractionList.add(new Attraction(7, "attraction"
                , getResources().getString(R.string.attraction_7_name)
                , getResources().getString(R.string.attraction_7_description)
                , getResources().getString(R.string.attraction_7_phone)
                , getResources().getString(R.string.attraction_7_address)
                , getResources().getString(R.string.attraction_7_rating)
                , getResources().getString(R.string.attraction_7_picture)
        ));


    }

    /**
     * @return attraction list with hardcoded data
     */
    public ArrayList<Attraction> getAttractionList() {
        return attractionList;
    }

    /**
     * @return hotel list with hardcoded data
     */
    public ArrayList<Attraction> getHotelList() {
        return hotelList;
    }

    /**
     * @return restaurant list with hardcoded data
     */
    public ArrayList<Attraction> getRestaurantList() {
        return restaurantList;
    }

    /**
     * @return gelato list with hardcoded data
     */
    public ArrayList<Attraction> getGelatoList() {
        return gelatoList;
    }

    /**
     * Takes in an attraction array list and return the object with the passed in primary key id
     *
     * @param myAttractionList arrayList of Attraction object
     * @param id               primary key for the item
     * @return Attraction object
     */
    public Attraction getAttractionById(ArrayList<Attraction> myAttractionList, int id) {
        for (int index = 0; index < myAttractionList.size(); index++) {
            if (myAttractionList.get(index).getId() == id) {
                return myAttractionList.get(index);
            }
        }
        return null;
    }

    /**
     * Takes the attraction type and return the attraction object taht matches the pk id.
     *
     * @param type -> the four types of attraction array list
     * @param id   -> primary key id of the attraction object
     * @return returning the attraction object
     */
    public Attraction getAttractionByTypeAndId(String type, int id) {
        if (type.equalsIgnoreCase("hotel"))
            return getAttractionById(getHotelList(), id);
        else if (type.equalsIgnoreCase("gelato"))
            return getAttractionById(getGelatoList(), id);
        else if (type.equalsIgnoreCase("restaurant"))
            return getAttractionById(getRestaurantList(), id);
        else
            return getAttractionById(getAttractionList(), id);
    }


}
