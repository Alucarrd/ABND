package com.petina.android.inventoryapp;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.petina.android.inventoryapp.data.ShoesContract;
import com.petina.android.inventoryapp.data.ShoesContract.ShoesEntry;


import java.util.Random;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    //initialize private variables
    private RecyclerView _recyclerView;
    private ShoesRecyclerAdapter _adapter;
    private TextView _emptyView;
    private static final int RANDOM_MAX = 1000;
    private static final int RANDOM_SIZE_MAX = 15;
    private static final int RANDOM_CATEGORY_MAX = 4;
    private static final int RANDOM_QUANTITY_MAX = 20;
    private static final int RANDOM_PRICE_MAX = 1000;
    private static final int SHOE_LOADER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup floating button to add new shoes
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        //load recycler view with adapter

        _recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        _emptyView = (TextView) findViewById(R.id.empty_view);

        _recyclerView.setLayoutManager(new LinearLayoutManager(this));
        _adapter = new ShoesRecyclerAdapter(this, null);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(SHOE_LOADER, null, this);
        _recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        _recyclerView.setAdapter(_adapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_dummy_action, menu);
        return true;
    }


    //action for selected option menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertDummyData();
            return true;

            // delete all items in inventory
            case R.id.action_all_delete:
                deleteAllRecord(ShoesContract.ShoesEntry.TABLE_NAME);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Takes in category type index and return the string value for them
     *
     * @param categoryIndex - Category type index
     * @return string value for each category
     */
    private String getCategroyType(int categoryIndex) {
        String output = "";
        switch (categoryIndex) {
            case 1:
                output = getString(R.string.category_women);
                break;
            case 2:
                output = getString(R.string.category_men);
                break;
            case 3:
                output = getString(R.string.category_girls);
                break;
            case 4:
                output = getString(R.string.category_boys);
                break;
            default:
                output = getString(R.string.category_unknown);
                break;

        }
        return output;
    }

    /**
     * Purge all data from the table
     *
     * @param TableName - table name where we want to purge
     */
    private void deleteAllRecord(String TableName) {
        int rowsDeleted = getContentResolver().delete(ShoesEntry.CONTENT_URI, null, null);
        Toast.makeText(this, getString(R.string.demo_delete_msg), Toast.LENGTH_SHORT);
    }

    /**
     * Insert dummy data into the table to test out connection
     */
    private void insertDummyData() {

        String myRandomString = String.valueOf(getRandom(RANDOM_MAX));
        ContentValues myValue = new ContentValues();
        myValue.put(ShoesEntry.COLUMN_SHOES_NAME, "name" + myRandomString);
        myValue.put(ShoesEntry.COLUMN_BRAND, "brand" + myRandomString);
        myValue.put(ShoesEntry.COLUMN_SHOES_SIZE, getRandom(RANDOM_SIZE_MAX));
        myValue.put(ShoesEntry.COLUMN_SHOES_COLOR, "color" + myRandomString);
        myValue.put(ShoesEntry.COLUMN_CATEGORY_TYPE, getRandom(RANDOM_CATEGORY_MAX));
        myValue.put(ShoesEntry.COLUMN_PRICE, getRandom(RANDOM_PRICE_MAX));
        myValue.put(ShoesEntry.COLUMN_QUANTITY, getRandom(RANDOM_QUANTITY_MAX));
        myValue.put(ShoesEntry.COLUMN_SUPPLIER_NAME, "supplierName" + myRandomString);
        myValue.put(ShoesEntry.COLUMN_SUPPLIER_PHONE, "supplierPhone" + myRandomString);

        getContentResolver().insert(ShoesEntry.CONTENT_URI, myValue);


    }

    /**
     * takes in a ceiling integer and return a random number from 0 to this ceiling number.
     *
     * @param max - max int value of the random range from 0
     * @return generate random value
     */
    private int getRandom(int max) {
        Random rand = new Random();
        return rand.nextInt(max) + 1;
    }

    //create cursorloader to return list of items from db
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String from[] = {
                ShoesEntry._ID
                , ShoesEntry.COLUMN_SHOES_NAME
                , ShoesEntry.COLUMN_BRAND
                , ShoesEntry.COLUMN_CATEGORY_TYPE
                , ShoesEntry.COLUMN_SHOES_SIZE
                , ShoesEntry.COLUMN_SHOES_COLOR
                , ShoesEntry.COLUMN_PRICE
                , ShoesEntry.COLUMN_QUANTITY

        };

        return new CursorLoader(this, ShoesEntry.CONTENT_URI,
                from, null, null, null);
    }

    //Once load finishes, call the swapCursor to swap in the new data
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.i("cursorAtMain", "I have these many data:" + String.valueOf(data.getCount()));
        int itemCount = data == null ? 0 : data.getCount();
        Log.i("cursorAtMain", "Current cursor count:" + String.valueOf(itemCount));
        if (itemCount == 0) {
            _recyclerView.setVisibility(View.GONE);
            _emptyView.setVisibility(View.VISIBLE);

        } else {
            _emptyView.setVisibility(View.GONE);
            _recyclerView.setVisibility(View.VISIBLE);
            Log.i("cursorAtMain", String.format("swapping cursor with %s items", String.valueOf(data.getCount())));
            _adapter.swapCursor(data);

        }
    }

    //upon loader reset, empty out the data in adapter
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        _adapter.swapCursor(null);
    }

}
