package com.petina.android.inventoryapp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.petina.android.inventoryapp.data.ShoesContract.ShoesEntry;

public class EditActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int EXISTING_SHOES_LOADER = 2;
    private Uri _currentShoesUri;
    private EditText editTextName, editTextBrand, editTextColor, editTextSize,
            editTextPrice, editTextQuantity, editTextSupplier, editTextSupplierPhone;
    private Spinner spinnerCategory;
    private int categoryType = ShoesEntry.CATEGORY_UNKNOWN;
    private Boolean _shoeHasChanged = false;

    private View.OnTouchListener _touchListener = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent){
            _shoeHasChanged = true;
            return false;
        }
    };

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        super.onPrepareOptionsMenu(menu);
        if(_currentShoesUri == null){
            MenuItem item = menu.findItem(R.id.action_delete);
            item.setVisible(false);
        }
        return true;
    }

    /*
            // If the intent DOES NOT contain a pet content URI, then we know that we are
        // creating a new pet.
        if (mCurrentPetUri == null) {
            // This is a new pet, so change the app bar to say "Add a Pet"
            setTitle(getString(R.string.editor_activity_title_new_pet));

            // Invalidate the options menu, so the "Delete" menu option can be hidden.
            // (It doesn't make sense to delete a pet that hasn't been created yet.)
            invalidateOptionsMenu();
        } else {
            // Otherwise this is an existing pet, so change app bar to say "Edit Pet"
            setTitle(getString(R.string.editor_activity_title_edit_pet));

            // Initialize a loader to read the pet data from the database
            // and display the current values in the editor
            getLoaderManager().initLoader(EXISTING_PET_LOADER, null, this);
        }
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        _currentShoesUri = intent.getData();
        if(_currentShoesUri == null){
            //add new pet
            setTitle(getString(R.string.edit_title_add));
            invalidateOptionsMenu();

        }
        else{
            setTitle(getString(R.string.edit_title_edit));
            getLoaderManager().initLoader(EXISTING_SHOES_LOADER, null, this);
        }


        /*
        private EditText editTextName, editTextBrand, editTextColor, editTextSize,
            editTextPrice, editTextQuantity, editTextSupplier, editTextSupplierPhone;
    private Spinner spinnerCategory;
         */
        editTextName = (EditText) findViewById(R.id.edit_input_name);
        editTextBrand = (EditText) findViewById(R.id.edit_input_brand);
        editTextColor = (EditText) findViewById(R.id.edit_input_color);
        editTextSize = (EditText) findViewById(R.id.edit_input_size);
        editTextPrice = (EditText) findViewById(R.id.edit_input_price);
        editTextQuantity = (EditText) findViewById(R.id.edit_input_quantity);
        editTextSupplier = (EditText) findViewById(R.id.edit_input_supplier_name);
        editTextSupplierPhone = (EditText) findViewById(R.id.edit_input_supplier_phone);
        spinnerCategory = (Spinner) findViewById(R.id.edit_input_category);

        /*
         mNameEditText.setOnTouchListener(mTouchListener);
        mBreedEditText.setOnTouchListener(mTouchListener);
         */
        editTextName.setOnTouchListener(_touchListener);
        editTextBrand.setOnTouchListener(_touchListener);
        editTextColor.setOnTouchListener(_touchListener);
        editTextSize.setOnTouchListener(_touchListener);
        editTextPrice.setOnTouchListener(_touchListener);
        editTextQuantity.setOnTouchListener(_touchListener);
        editTextSupplier.setOnTouchListener(_touchListener);
        editTextSupplierPhone.setOnTouchListener(_touchListener);
        spinnerCategory.setOnTouchListener(_touchListener);

        setupSpinner();




    }

    public void setupSpinner(){
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter categorySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_category_options, android.R.layout.simple_spinner_item);
        // Specify dropdown layout style - simple list view with 1 item per line
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        // Apply the adapter to the spinner
        spinnerCategory.setAdapter(categorySpinnerAdapter);

        // Set the integer mSelected to the constant values
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.category_women))) {
                        categoryType = ShoesEntry.CATEGORY_WOMEN;
                    } else if (selection.equals(getString(R.string.category_men))) {
                        categoryType = ShoesEntry.CATEGORY_MEN;
                    } else if (selection.equals(getString(R.string.category_girls))) {
                        categoryType = ShoesEntry.CATEGORY_GIRLS;
                    } else if (selection.equals(getString(R.string.category_boys))) {
                        categoryType = ShoesEntry.CATEGORY_BOYS;
                    } else {
                        categoryType = ShoesEntry.CATEGORY_UNKNOWN;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                categoryType = ShoesEntry.CATEGORY_UNKNOWN;
            }
        });

    }
    private void saveShoes(){
        String name = editTextName.getText().toString().trim();
        String brand = editTextBrand.getText().toString().trim();
        String color = editTextColor.getText().toString().trim();
        String sizeString = editTextSize.getText().toString().trim();
        String priceString = editTextPrice.getText().toString().trim();
        String quantityString = editTextQuantity.getText().toString().trim();
        String supplier = editTextSupplier.getText().toString().trim();
        String supplierPhone = editTextSupplierPhone.getText().toString().trim();

        if(_currentShoesUri == null &&
                TextUtils.isEmpty(name) &&
                TextUtils.isEmpty(brand)){
            return;
        }
        int size = 0;
        double price = 0.0;
        int quantity = 0;
        if (!TextUtils.isEmpty(sizeString)) {
            size = Integer.parseInt(sizeString);
        }
        if (!TextUtils.isEmpty(quantityString)) {
            quantity = Integer.parseInt(quantityString);
        }
        if (!TextUtils.isEmpty(priceString)) {
            price = Double.parseDouble(priceString);
        }

        ContentValues myValue = new ContentValues();
        myValue.put(ShoesEntry.COLUMN_SHOES_NAME, name);
        myValue.put(ShoesEntry.COLUMN_BRAND, brand);
        myValue.put(ShoesEntry.COLUMN_SHOES_COLOR, color);
        myValue.put(ShoesEntry.COLUMN_SHOES_SIZE, size);
        myValue.put(ShoesEntry.COLUMN_QUANTITY, quantity);
        myValue.put(ShoesEntry.COLUMN_PRICE, price);
        myValue.put(ShoesEntry.COLUMN_SUPPLIER_NAME, supplier);
        myValue.put(ShoesEntry.COLUMN_SUPPLIER_PHONE, supplierPhone);
        myValue.put(ShoesEntry.COLUMN_CATEGORY_TYPE, categoryType);


        if(_currentShoesUri == null){
            Uri myUri = getContentResolver().insert(ShoesEntry.CONTENT_URI, myValue);
            if(myUri == null){
                //failed
                Toast.makeText(this, getString(R.string.editor_insert_shoes_failed),
                        Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, getString(R.string.editor_insert_shoes_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
        else{
            int rowCount = getContentResolver().update(_currentShoesUri, myValue, null, null);
            if(rowCount == 0){
                Toast.makeText(this, getString(R.string.editor_update_shoes_failed),
                        Toast.LENGTH_SHORT).show();
            }
            else{
                // Otherwise, the update was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.editor_update_shoes_successful),
                        Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void deleteShoes(){
        if(_currentShoesUri != null){
            int rowDeleted = getContentResolver().delete(_currentShoesUri, null, null);
            if(rowDeleted == 0){
                Toast.makeText(this, getString(R.string.editor_delete_shoes_failed),
                        Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, getString(R.string.editor_delete_shoes_successful),
                        Toast.LENGTH_SHORT).show();
            }



        }
        finish();
    }

    private void showDeleteconfirmationDialog(){
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
                if(dialog != null){
                    dialog.dismiss();
                }
            }
        });

    }
    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_save:
                saveShoes();
                finish();
                return true;
            case R.id.action_delete:
                showDeleteconfirmationDialog();
                return true;
            case android.R.id.home:
                if(!_shoeHasChanged){
                    NavUtils.navigateUpFromSameTask(EditActivity.this);
                    return true;

                }
                DialogInterface.OnClickListener discardButtonListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                NavUtils.navigateUpFromSameTask(EditActivity.this);
                            }
                        };
                showUnsavedChangesDialog(discardButtonListener);
                return true;

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        // If the pet hasn't changed, continue with handling back button press
        if (!_shoeHasChanged) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }
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

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(cursor == null || cursor.getCount() < 1){
            return;
        }
        if(cursor.moveToFirst()){
            int nameColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_SHOES_NAME);
            int brandColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_BRAND);
            int sizeColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_SHOES_SIZE);
            int colorColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_SHOES_COLOR);
            int quantityColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_PRICE);
            int categoryColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_CATEGORY_TYPE);
            int supplierColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(ShoesEntry.COLUMN_SUPPLIER_PHONE);

            String name = cursor.getString(nameColumnIndex);
            String brand = cursor.getString(brandColumnIndex);
            int size = cursor.getInt(sizeColumnIndex);
            String color = cursor.getString(colorColumnIndex);
            int quantity = cursor.getInt(quantityColumnIndex);
            int price = (int)cursor.getDouble(priceColumnIndex);
            int category = cursor.getInt(categoryColumnIndex);
            String supplier = cursor.getString(supplierColumnIndex);
            String supplierPhone = cursor.getString(supplierPhoneColumnIndex);

            editTextName.setText(name);
            editTextBrand.setText(brand);
            editTextColor.setText(color);
            editTextSize.setText(String.valueOf(size));
            editTextQuantity.setText(String.valueOf(quantity));
            editTextPrice.setText(String.valueOf(price));
            editTextSupplier.setText(supplier);
            editTextSupplierPhone.setText(supplierPhone);
            switch(category){
                case ShoesEntry.CATEGORY_WOMEN:
                    spinnerCategory.setSelection(1);
                    break;
                case ShoesEntry.CATEGORY_MEN:
                    spinnerCategory.setSelection(2);
                    break;
                case ShoesEntry.CATEGORY_GIRLS:
                    spinnerCategory.setSelection(3);
                    break;
                case ShoesEntry.CATEGORY_BOYS:
                    spinnerCategory.setSelection(4);
                    break;
                default:
                    spinnerCategory.setSelection(0);
                    break;

            }


        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        editTextName.setText("");
        editTextBrand.setText("");
        editTextColor.setText("");
        editTextSize.setText("");
        editTextQuantity.setText("");
        editTextPrice.setText("");
        editTextSupplier.setText("");
        editTextSupplierPhone.setText("");
        spinnerCategory.setSelection(0);
    }
}
