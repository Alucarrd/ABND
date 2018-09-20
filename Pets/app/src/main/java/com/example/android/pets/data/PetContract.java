package com.example.android.pets.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

public final class PetContract {
    public static final String CONTENT_AUTHORITY = "com.example.android.pets";
    public static final String PATH_PETS = "pets";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    private PetContract(){}

    public static final class PetEntry implements BaseColumns{
        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);
        public static final String TABLE_NAME = "pets";
        public static final String COLUMN_PETS_NAME = "name";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PETS_GENDER = "gender";
        public static final String COLUMN_PETS_BREED = "breed";
        public static final String COLUMN_PETS_WEIGHT = "weight";

        public final static String WEIGHT_DEFAULT = "0";
        public final static int GENDER_MALE = 1;
        public final static int GENDER_FEMALE = 2;
        public final static int GENDER_UNKNOWN = 0;

        public static Boolean isValidGender(int gender){
            if(gender == GENDER_MALE || gender == GENDER_FEMALE || gender == GENDER_UNKNOWN)
                return true;
            else
                return false;
        }

    }



}