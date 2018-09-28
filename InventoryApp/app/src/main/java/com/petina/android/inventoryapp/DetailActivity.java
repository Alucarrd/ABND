package com.petina.android.inventoryapp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.petina.android.inventoryapp.data.ShoesContract.ShoesEntry;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_SHOES_LOADER = 3;
    private Uri _currentShoesUri;
    private TextView textViewName, textViewBrand, textViewColor, textViewSize,
            textViewPrice, textViewQuantity, textViewSupplier, textViewSupplierPhone, textViewCategory;
    private Button btnMinus, btnPlus, btnOrder;

    private int quantity, id;
    private String supplierPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        _currentShoesUri = intent.getData();
        setTitle(getString(R.string.detail_title));
        getLoaderManager().initLoader(EXISTING_SHOES_LOADER, null, this);

        //setup floating button to add new shoes
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
        textViewName = (TextView) findViewById(R.id.edit_input_name);
        textViewBrand = (TextView) findViewById(R.id.edit_input_brand);
        textViewColor = (TextView) findViewById(R.id.edit_input_color);
        textViewSize = (TextView) findViewById(R.id.edit_input_size);
        textViewPrice = (TextView) findViewById(R.id.edit_input_price);
        textViewQuantity = (TextView) findViewById(R.id.edit_input_quantity);
        textViewSupplier = (TextView) findViewById(R.id.edit_input_supplier_name);
        textViewSupplierPhone = (TextView) findViewById(R.id.edit_input_supplier_phone);
        textViewCategory = (TextView) findViewById(R.id.edit_input_category);
        btnMinus = (Button) findViewById(R.id.btn_minus_quantity);
        btnPlus = (Button) findViewById(R.id.btn_plus_quantity);
        btnOrder = (Button) findViewById(R.id.btnOrder);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 0) {
                    Toast.makeText(DetailActivity.this, getText(R.string.no_item_alert).toString(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues myValue = new ContentValues();
                    _currentShoesUri = ContentUris.withAppendedId(ShoesEntry.CONTENT_URI, id);
                    int newQuantity = quantity - 1;
                    myValue.put(ShoesEntry.COLUMN_QUANTITY, newQuantity);
                    int rowCount = getContentResolver().update(_currentShoesUri, myValue, null, null);
                    String msg = String.format(getText(R.string.item_sold_alert).toString(), String.valueOf(newQuantity));
                    Toast.makeText(DetailActivity.this, msg,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues myValue = new ContentValues();
                _currentShoesUri = ContentUris.withAppendedId(ShoesEntry.CONTENT_URI, id);
                int newQuantity = quantity + 1;
                myValue.put(ShoesEntry.COLUMN_QUANTITY, newQuantity);
                int rowCount = getContentResolver().update(_currentShoesUri, myValue, null, null);
                String msg = String.format(getText(R.string.item_sold_alert).toString(), String.valueOf(newQuantity));
                Toast.makeText(DetailActivity.this, msg,
                        Toast.LENGTH_SHORT).show();

            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", supplierPhone, null));
                startActivity(intent);
            }
        });

    }


    //delete single pair of shoes
    private void deleteShoes() {
        if (_currentShoesUri != null) {
            int rowDeleted = getContentResolver().delete(_currentShoesUri, null, null);
            if (rowDeleted == 0) {
                Toast.makeText(this, getString(R.string.editor_delete_shoes_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_delete_shoes_successful),
                        Toast.LENGTH_SHORT).show();
            }


        }
        finish();
    }

    //delete confirmation dialog to ensure user does not delete an item by mistake
    private void showDeleteconfirmationDialog() {
        Log.v("option logging", "Start of the delete confirmation dialog call");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.action_delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteShoes();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    //create the option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    private void EditThisShoe() {
        Intent intent = new Intent(this, EditActivity.class);
        intent.setData(_currentShoesUri);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v("option logging", String.valueOf(item.getItemId()));
        switch (item.getItemId()) {
            case R.id.action_edit:
                EditThisShoe();
                return true;
            case R.id.action_delete:
                showDeleteconfirmationDialog();
                return true;


        }
        return super.onOptionsItemSelected(item);

    }

    //create the cursor loader to load data

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                ShoesEntry._ID,
                ShoesEntry.COLUMN_SHOES_NAME,
                ShoesEntry.COLUMN_BRAND,
                ShoesEntry.COLUMN_SHOES_COLOR,
                ShoesEntry.COLUMN_SHOES_SIZE,
                ShoesEntry.COLUMN_QUANTITY,
                ShoesEntry.COLUMN_PRICE,
                ShoesEntry.COLUMN_CATEGORY_TYPE,
                ShoesEntry.COLUMN_SUPPLIER_NAME,
                ShoesEntry.COLUMN_SUPPLIER_PHONE,
        };
        return new CursorLoader(this, _currentShoesUri, projection, null, null, null);
    }

    //once the cursor finishes loading, update the view
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        if (cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndex(ShoesEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_SHOES_NAME);
            int brandColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_BRAND);
            int sizeColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_SHOES_SIZE);
            int colorColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_SHOES_COLOR);
            int quantityColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_PRICE);
            int categoryColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_CATEGORY_TYPE);
            int supplierColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_SUPPLIER_PHONE);
            id = cursor.getInt(idColumnIndex);
            String name = cursor.getString(nameColumnIndex);
            String brand = cursor.getString(brandColumnIndex);
            double size = cursor.getDouble(sizeColumnIndex);
            String color = cursor.getString(colorColumnIndex);
            quantity = cursor.getInt(quantityColumnIndex);
            double price = cursor.getDouble(priceColumnIndex);
            int category = cursor.getInt(categoryColumnIndex);
            String supplier = cursor.getString(supplierColumnIndex);
            supplierPhone = cursor.getString(supplierPhoneColumnIndex);
            String price_item = String.format(this.getText(R.string.item_price_holder).toString(), String.format("%.2f", Double.valueOf(price)));
            String size_item = String.format(this.getText(R.string.item_size_holder).toString(), String.format("%.2f", Double.valueOf(size)));
            String quantity_item = String.format(this.getText(R.string.item_quantity).toString(), String.valueOf(quantity));
            textViewName.setText(name);
            textViewBrand.setText(brand);
            textViewColor.setText(color);
            textViewSize.setText(size_item);
            textViewCategory.setText(shoeTypeMapping(category));
            textViewPrice.setText(price_item);
            textViewQuantity.setText(quantity_item);
            textViewSupplier.setText(supplier);
            textViewSupplierPhone.setText(supplierPhone);
            if(supplierPhone.isEmpty()){
                btnOrder.setVisibility(View.GONE);
            }
            else{
                btnOrder.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        textViewName.setText("");
        textViewBrand.setText("");
        textViewColor.setText("");
        textViewSize.setText("");
        textViewCategory.setText("");
        textViewPrice.setText("");
        textViewQuantity.setText("");
        textViewSupplier.setText("");
        textViewSupplierPhone.setText("");
    }

    private String shoeTypeMapping(int category) {
        switch (category) {
            case 1:
                return this.getText(R.string.category_women).toString();
            case 2:
                return this.getText(R.string.category_men).toString();
            case 3:
                return this.getText(R.string.category_girls).toString();
            case 4:
                return this.getText(R.string.category_boys).toString();
            default:
                return this.getText(R.string.category_unknown).toString();

        }
    }


}
