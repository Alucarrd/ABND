package com.example.android.miwok;

public class Word {
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mResourceId;
    private static final int NO_IMAGE_PROVIDED = -1;
    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     */
    public Word(String defaultTranslation, String miwokTranslation){
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;

    }
    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     *
     * @param resourceId is the int for the resource id
     */
    public Word(String defaultTranslation, String miwokTranslation, int resourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mResourceId = resourceId;
    }
    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    public int getmResourceId(){return mResourceId; }
    public boolean hasImage(){
        return mResourceId != NO_IMAGE_PROVIDED;
    }
}
