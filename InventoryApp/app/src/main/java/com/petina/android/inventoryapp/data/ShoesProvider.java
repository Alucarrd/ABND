package com.petina.android.inventoryapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.petina.android.inventoryapp.R;
import com.petina.android.inventoryapp.data.ShoesContract.ShoesEntry;

public class ShoesProvider extends ContentProvider
{
    /** Tag for the log messages */
    public static final String LOG_TAG = ShoesProvider.class.getSimpleName();
    /** URI matcher code for the content URI for the pets table */
    private static final int SHOES = 100;

    /** URI matcher code for the content URI for a single pet in the pets table */
    private static final int SHOE_ID = 101;
    private ShoesDBHelper _dbHelper;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // TODO: Add 2 content URIs to URI matcher
        sUriMatcher.addURI(ShoesContract.CONTENT_AUTHORITY, ShoesContract.PATH_SHOES, SHOES);
        sUriMatcher.addURI(ShoesContract.CONTENT_AUTHORITY, ShoesContract.PATH_SHOES + "/#", SHOE_ID);
    }

    /**
     * Initialize the provider and the database helper object.
     */
    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        _dbHelper = new ShoesDBHelper(getContext());

        SQLiteDatabase _db = _dbHelper.getReadableDatabase();
        Cursor _cursor;
        int match = sUriMatcher.match(uri);
        switch(match){
            case SHOES:
                _cursor = _db.query(ShoesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case SHOE_ID:
                selection = ShoesEntry._ID + "=?";
                selectionArgs = new String[]{ String.valueOf(ContentUris.parseId(uri))};
                _cursor = _db.query(ShoesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        _cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return _cursor;

    }


    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch(match){
            case SHOES:
                return insertShoes(uri, values);
            default:
                throw new IllegalArgumentException(String.format(getContext().getText(R.string.illegal_argument_insert).toString(), uri));
        }
    }
    private Uri insertShoes(Uri uri, ContentValues values){
        /*
        validation

         public static final String COLUMN_SHOES_NAME = "ProductName"; not null
        public static final String COLUMN_BRAND = "Brand"; not null
        public static final String COLUMN_SHOES_SIZE = "Size"; null
        public static final String COLUMN_SHOES_COLOR = "Color"; not null
        public static final String COLUMN_CATEGORY_TYPE = "Type"; validate type
        public static final String COLUMN_PRICE = "Price"; not null with default
        public static final String COLUMN_QUANTITY = "Quantity"; not null with default

        public static final String COLUMN_SUPPLIER_NAME = "SupplierName"; null
        public static final String COLUMN_SUPPLIER_PHONE = "SupplierPhone"; null


         */
        String name = values.getAsString(ShoesEntry.COLUMN_SHOES_NAME);
        if(name == null){
            throw new IllegalArgumentException(getContext().getText(R.string.illegal_shoe_name).toString());
        }
        String brand = values.getAsString(ShoesEntry.COLUMN_BRAND);
        if(brand == null){
            throw new IllegalArgumentException(getContext().getText(R.string.illegal_shoe_brand).toString());
        }
        String color = values.getAsString(ShoesEntry.COLUMN_SHOES_COLOR);
        if(color == null){
            throw new IllegalArgumentException(getContext().getText(R.string.illegal_shoe_color).toString());
        }
        Integer shoe_category = values.getAsInteger(ShoesEntry.COLUMN_CATEGORY_TYPE);
        if (shoe_category == null || !ShoesEntry.isValidCategory(shoe_category)) {
            throw new IllegalArgumentException(getContext().getText(R.string.illegal_shoe_type).toString());
        }


        _dbHelper = new ShoesDBHelper(getContext());
        SQLiteDatabase _db = _dbHelper.getWritableDatabase();
        long id = _db.insert(ShoesEntry.TABLE_NAME, null, values);
        // Once we know the ID of the new row in the table,
        // return the new URI with the ID appended to the end of it
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        _dbHelper = new ShoesDBHelper(getContext());
        SQLiteDatabase _db = _dbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowCount = 0;
        switch(match){
            case SHOES:
                //delete all
                rowCount = _db.delete(ShoesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case SHOE_ID:
                selection = ShoesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowCount = _db.delete(ShoesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException(String.format(getContext().getText(R.string.illegal_argument_delete).toString(), uri));

        }

        if(rowCount > 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowCount;



    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch(match){
            case SHOES:
                //update all items
                return updateShoes(uri, values, selection, selectionArgs);
            case SHOE_ID:
                selection = ShoesEntry._ID + "=?";
                selectionArgs = new String[]{ String.valueOf(ContentUris.parseId(uri))};
                return updateShoes(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException(String.format(getContext().getText(R.string.illegal_argument_update).toString(), uri));

        }

    }

    public int updateShoes(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        if (values.containsKey(ShoesEntry.COLUMN_SHOES_NAME)) {
            String name = values.getAsString(ShoesEntry.COLUMN_SHOES_NAME);
            if (name == null) {
                throw new IllegalArgumentException(getContext().getText(R.string.illegal_shoe_name).toString());
            }
        }
        if(values.containsKey(ShoesEntry.COLUMN_BRAND)) {
            String brand = values.getAsString(ShoesEntry.COLUMN_BRAND);
            if (brand == null) {
                throw new IllegalArgumentException(getContext().getText(R.string.illegal_shoe_brand).toString());
            }
        }
        if(values.containsKey(ShoesEntry.COLUMN_SHOES_COLOR)) {
            String color = values.getAsString(ShoesEntry.COLUMN_SHOES_COLOR);
            if (color == null) {
                throw new IllegalArgumentException(getContext().getText(R.string.illegal_shoe_color).toString());
            }
        }
        if(values.containsKey(ShoesEntry.COLUMN_CATEGORY_TYPE)) {
            Integer shoe_category = values.getAsInteger(ShoesEntry.COLUMN_CATEGORY_TYPE);
            if (shoe_category == null || !ShoesEntry.isValidCategory(shoe_category)) {
                throw new IllegalArgumentException(getContext().getText(R.string.illegal_shoe_type).toString());
            }
        }

        _dbHelper = new ShoesDBHelper(getContext());
        SQLiteDatabase _db = _dbHelper.getWritableDatabase();
        if(values.size() == 0)
            return 0;
        int rowCount = _db.update(ShoesEntry.TABLE_NAME, values, selection, selectionArgs);
        if(rowCount > 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowCount;


    }


    @Override
    public String getType(@NonNull Uri uri) {

        final int match = sUriMatcher.match(uri);
        switch(match){
            case SHOES:
                return ShoesEntry.CONTENT_LIST_TYPE;
            case SHOE_ID:
                return ShoesEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException(String.format(getContext().getText(R.string.illegal_uri).toString(), uri, match));
        }

    }
}
