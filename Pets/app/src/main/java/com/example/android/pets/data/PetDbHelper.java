package com.example.android.pets.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.pets.data.PetContract.PetEntry;
public class PetDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shelter.db";
    private static final int DATABASE_VERSION = 2;

    public PetDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_PETS_TABLE = "CREATE TABLE " + PetEntry.TABLE_NAME + "(";
        CREATE_PETS_TABLE += PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        CREATE_PETS_TABLE += PetEntry.COLUMN_PETS_NAME + " TEXT NOT NULL, ";
        CREATE_PETS_TABLE += PetEntry.COLUMN_PETS_BREED + " TEXT, ";
        CREATE_PETS_TABLE += PetEntry.COLUMN_PETS_GENDER + " INTEGER NOT NULL, ";
        CREATE_PETS_TABLE += PetEntry.COLUMN_PETS_WEIGHT + " INTEGER NOT NULL DEFAULT " + PetEntry.WEIGHT_DEFAULT + ") ";

        db.execSQL(CREATE_PETS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + PetEntry.TABLE_NAME);
            onCreate(db);
        }
    }

}