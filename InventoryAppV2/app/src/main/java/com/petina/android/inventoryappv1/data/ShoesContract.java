package com.petina.android.inventoryappv1.data;

import android.provider.BaseColumns;

public final class ShoesContract {

    /*
    Contract and Entry class for shoes store
    */
    private ShoesContract() {
    }

    public static final class ShoesEntry implements BaseColumns {
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
        public static final String CATEGORY_UNKNOWN = "0";
        public static final String CATEGORY_WOMEN = "1";
        public static final String CATEGORY_MEN = "2";
        public static final String CATEGORY_GIRLS = "3";
        public static final String CATEGORY_BOYS = "4";


    }


}
   