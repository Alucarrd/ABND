package com.petina.android.inventoryapp.data;


import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import java.nio.file.PathMatcher;

public final class ShoesContract {


    public static final String CONTENT_AUTHORITY = "com.petina.android.inventoryapp";
    public static final String PATH_SHOES = "shoes";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private ShoesContract() {
    }

    public static final class ShoesEntry implements BaseColumns {


        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SHOES;


        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SHOES;
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SHOES);


        public static final String TABLE_NAME = "shoes";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_SHOES_NAME = "ProductName";
        public static final String COLUMN_BRAND = "Brand";
        public static final String COLUMN_SHOES_SIZE = "Size";
        public static final String COLUMN_SHOES_COLOR = "Color";
        public static final String COLUMN_CATEGORY_TYPE = "Type";
        public static final String COLUMN_PRICE = "Price";
        public static final String COLUMN_QUANTITY = "Quantity";
        public static final String COLUMN_SUPPLIER_NAME = "SupplierName";
        public static final String COLUMN_SUPPLIER_PHONE = "SupplierPhone";

        public static final String PRICE_DEFAULT = "0";
        public static final String QUANTITY_DEFAULT = "0";
        public static final String CATEGORY_TYPE_DEFAULT = "0";
        public static final int CATEGORY_UNKNOWN = 0;
        public static final int CATEGORY_WOMEN = 1;
        public static final int CATEGORY_MEN = 2;
        public static final int CATEGORY_GIRLS = 3;
        public static final int CATEGORY_BOYS = 4;

        public static Boolean isValidCategory(int category) {
            if (category == CATEGORY_UNKNOWN || category == CATEGORY_WOMEN || category == CATEGORY_MEN ||
                    category == CATEGORY_GIRLS || category == CATEGORY_BOYS)
                return true;
            return false;
        }




    }


}
