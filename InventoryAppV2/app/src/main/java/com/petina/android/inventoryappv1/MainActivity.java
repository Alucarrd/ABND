package com.petina.android.inventoryappv1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.petina.android.inventoryappv1.data.ShoesContract;
import com.petina.android.inventoryappv1.data.ShoesContract.ShoesEntry;
import com.petina.android.inventoryappv1.data.ShoesDBHelper;


import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ShoesDBHelper _DbHelper;
    private static final int RANDOM_MAX = 1000;
    private static final int RANDOM_SIZE_MAX = 15;
    private static final int RANDOM_CATEGORY_MAX = 4;
    private static final int RANDOM_QUANTITY_MAX = 20;
    private static final int RANDOM_PRICE_MAX = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _DbHelper = new ShoesDBHelper(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_dummy_action, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertDummyData();
                displayDatabaseInfo();
                return true;

            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                deleteAllRecord(ShoesContract.ShoesEntry.TABLE_NAME);
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to display current data at sample data view
     */
    private void displayDatabaseInfo() {


        TextView displayView = (TextView) findViewById(R.id.displayInfo);
        Cursor mySampleData = querySampleData();
        try {

            displayView.setText("The Shoes table contains " + mySampleData.getCount() + " products.\n\n");
            displayView.append(ShoesEntry._ID + " - "
                    + ShoesEntry.COLUMN_SHOES_NAME + " - "
                    + ShoesEntry.COLUMN_BRAND + " - "
                    + ShoesEntry.COLUMN_SHOES_SIZE + " - "
                    + ShoesEntry.COLUMN_SHOES_COLOR + " - "
                    + ShoesEntry.COLUMN_CATEGORY_TYPE + " - "
                    + ShoesEntry.COLUMN_PRICE + " - "
                    + ShoesEntry.COLUMN_QUANTITY + " - "
                    + ShoesEntry.COLUMN_SUPPLIER_NAME + " - "
                    + ShoesEntry.COLUMN_SUPPLIER_PHONE

            );


            int idColumnIndex = mySampleData.getColumnIndex(ShoesEntry._ID);
            int nameColumnIndex = mySampleData.getColumnIndex(ShoesEntry.COLUMN_SHOES_NAME);
            int brandColumnIndex = mySampleData.getColumnIndex(ShoesEntry.COLUMN_BRAND);
            int sizeColumnIndex = mySampleData.getColumnIndex(ShoesEntry.COLUMN_SHOES_SIZE);
            int colorColumnIndex = mySampleData.getColumnIndex(ShoesEntry.COLUMN_SHOES_COLOR);
            int categroyColumnIndex = mySampleData.getColumnIndex(ShoesEntry.COLUMN_CATEGORY_TYPE);
            int priceColumnIndex = mySampleData.getColumnIndex(ShoesEntry.COLUMN_PRICE);
            int quantityColumnIndex = mySampleData.getColumnIndex(ShoesEntry.COLUMN_QUANTITY);
            int supplierNameColumnIndex = mySampleData.getColumnIndex(ShoesEntry.COLUMN_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = mySampleData.getColumnIndex(ShoesEntry.COLUMN_SUPPLIER_PHONE);

            while (mySampleData.moveToNext()) {
                String id = String.valueOf(mySampleData.getInt(idColumnIndex));
                String name = mySampleData.getString(nameColumnIndex);
                String brand = mySampleData.getString(brandColumnIndex);
                String size = String.valueOf(mySampleData.getInt(sizeColumnIndex));
                String color = mySampleData.getString(colorColumnIndex);
                String category = getCategroyType(mySampleData.getInt(categroyColumnIndex));
                String price = String.valueOf(mySampleData.getInt(priceColumnIndex));
                String quantity = String.valueOf(mySampleData.getInt(quantityColumnIndex));
                String supplierName = mySampleData.getString(supplierNameColumnIndex);
                String supplierPhone = mySampleData.getString(supplierPhoneColumnIndex);
                displayView.append("\n\n" + id + " - "
                        + name + " - "
                        + brand + " - "
                        + size + " - "
                        + color + " - "
                        + category + " - "
                        + price + " - "
                        + quantity + " - "
                        + supplierName + " - "
                        + supplierPhone

                );

            }
        } finally {
            mySampleData.close();
        }


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
     * @return Cursor object for demo data
     */
    private Cursor querySampleData() {
        SQLiteDatabase db = _DbHelper.getReadableDatabase();
        Cursor myCursor = db.query(ShoesEntry.TABLE_NAME, null, null, null, null, null, null);
        return myCursor;
    }

    /**
     * Purge all data from the table
     *
     * @param TableName - table name where we want to purge
     */
    private void deleteAllRecord(String TableName) {
        SQLiteDatabase db = _DbHelper.getWritableDatabase();
        db.delete(ShoesEntry.TABLE_NAME, null, null);
        Toast.makeText(this, getString(R.string.demo_delete_msg), Toast.LENGTH_SHORT);
    }

    /**
     * Insert dummy data into the table to test out connection
     */
    private void insertDummyData() {
        SQLiteDatabase db = _DbHelper.getWritableDatabase();
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
        db.insert(ShoesEntry.TABLE_NAME, null, myValue);


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

}
