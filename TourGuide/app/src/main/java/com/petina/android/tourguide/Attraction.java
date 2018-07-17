package com.petina.android.tourguide;

public class Attraction {
    private int id;
    private String attractionType = "";
    private String attractionName = "";
    private String priceIndicator = "";
    private String address = "";
    private String description = "";
    private String phone = "";
    private String yelpURL = "";
    private String rating = "";
    private String pictureName = "";
    private String style = "";

    /**
     * constructor for attraction
     */
    public Attraction(int vId, String vAttractionType, String vAttractionName, String vDescription, String vPhone, String vAddress, String vRating, String vPicture) {
        id = vId;
        attractionName = vAttractionName;
        description = vDescription;
        attractionType = vAttractionType;
        phone = vPhone;
        pictureName = vPicture;
        address = vAddress;
        rating = vRating;
    }

    /**
     * constructor for gelato, hotel and restaurant
     */
    public Attraction(int vId, String vAttractionType, String vAttractionName, String vPriceIndicator, String vAddress, String vDescription, String vStyle, String vPhone, String vYelp, String vRating, String vPicture) {
        id = vId;
        attractionType = vAttractionType;
        attractionName = vAttractionName;
        address = vAddress;
        priceIndicator = vPriceIndicator;
        description = vDescription;
        style = vStyle;
        phone = vPhone;
        yelpURL = vYelp;
        rating = vRating;
        pictureName = vPicture;
    }


    /**
     * Get the id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the attraction type
     */
    public String getAttractionType() {
        return attractionType;
    }

    /**
     * Get the attraction name
     */
    public String getAttractionName() {
        return attractionName;
    }

    /**
     * Get the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get the priceIndicator
     */
    public String getPriceIndicator() {
        return priceIndicator;
    }

    /**
     * Get the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * Get the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Get the yelpURL
     */
    public String getYelpURL() {
        return yelpURL;
    }

    /**
     * Get the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * Get the pictureName
     */
    public String getPictureName() {
        return pictureName;
    }


    /**
     * Get the Shorten description
     *
     * @param charCount The minimum number of characters I want to return
     * @return Substring from beginning of the string until the first space after the min char count.
     */
    public String getShortDescription(int charCount) {
        if (description.length() <= charCount) {
            return description;
        } else {
            int endLength = description.indexOf(" ", charCount);
            return description.substring(0, endLength) + "...";
        }
    }
}
