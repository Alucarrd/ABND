package com.petina.android.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.petina.android.inventoryapp.data.ShoesContract.ShoesEntry;

public class ShoesDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shoes.db";
    private static final int DATABASE_VERSION = 1;

    public ShoesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //CREATE Table statement for onCreate in case to create the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SHOES_TABLE = "CREATE TABLE " + ShoesEntry.TABLE_NAME + "(";
        CREATE_SHOES_TABLE += ShoesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        CREATE_SHOES_TABLE += ShoesEntry.COLUMN_SHOES_NAME + " TEXT NOT NULL, ";
        CREATE_SHOES_TABLE += ShoesEntry.COLUMN_BRAND + " TEXT NOT NULL, ";
        CREATE_SHOES_TABLE += ShoesEntry.COLUMN_SHOES_COLOR + " TEXT NOT NULL, ";
        CREATE_SHOES_TABLE += ShoesEntry.COLUMN_SHOES_SIZE + " INTEGER NULL, ";
        CREATE_SHOES_TABLE += ShoesEntry.COLUMN_CATEGORY_TYPE + " INTEGER NOT NULL DEFAULT " + ShoesEntry.CATEGORY_TYPE_DEFAULT+ ", ";
        CREATE_SHOES_TABLE += ShoesEntry.COLUMN_PRICE + " INTEGER NOT NULL DEFAULT " + ShoesEntry.PRICE_DEFAULT + ", ";
        CREATE_SHOES_TABLE += ShoesEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT " + ShoesEntry.QUANTITY_DEFAULT + ", ";
        CREATE_SHOES_TABLE += ShoesEntry.COLUMN_SUPPLIER_NAME + " TEXT NULL, ";
        CREATE_SHOES_TABLE += ShoesEntry.COLUMN_SUPPLIER_PHONE + " TEXT NULL)";
        db.execSQL(CREATE_SHOES_TABLE);
    }

    //UPGrade schema method to wipe out and recreate the table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ShoesEntry.TABLE_NAME);
            onCreate(db);
        }
    }

}

