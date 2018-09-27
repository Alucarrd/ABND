package com.petina.android.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.petina.android.inventoryapp.data.ShoesContract.ShoesEntry;

public class ShoesRecyclerAdapter extends RecyclerView.Adapter<ShoesRecyclerAdapter.ItemViewHolder> {
    private Cursor _cursor;
    private Context _context;
    private boolean _dataValid;
    private int _rowIDColumn;
    private Uri _currentShoesUri;
    private int quantity;

    /*
    Recyclerview's viewholder class
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView topItemView, bottomItemView;
        public Button soldButton;

        public ItemViewHolder(View itemView) {
            super(itemView);
            topItemView = (TextView) itemView.findViewById(R.id.list_item_top);
            bottomItemView = (TextView) itemView.findViewById(R.id.list_item_bottom);
            soldButton = (Button) itemView.findViewById(R.id.item_button);

        }
    }
    //Constructor for the adapter
    public ShoesRecyclerAdapter(Context context, Cursor cursor) {
        _context = context;
        _cursor = cursor;
    }

    //CreateViewHolder that will read the list_item_card_view layout and return the viewHolder object
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(_context).inflate(R.layout.list_item_card_view, parent, false);
        return new ItemViewHolder(view);
    }

    //BindViewHolder class that binds the data to viewHolder for each item.
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        _cursor.moveToPosition(position);
        Log.v("cursorAtMain", "current position:" + String.valueOf(position));
        int nameColumnIndex = _cursor.getColumnIndex(ShoesEntry.COLUMN_SHOES_NAME);
        int brandColumnIndex = _cursor.getColumnIndex(ShoesEntry.COLUMN_BRAND);
        int priceColumnIndex = _cursor.getColumnIndex(ShoesEntry.COLUMN_PRICE);
        int sizeColumnIndex = _cursor.getColumnIndex(ShoesEntry.COLUMN_SHOES_SIZE);
        int typeColumnIndex = _cursor.getColumnIndex(ShoesEntry.COLUMN_CATEGORY_TYPE);
        int quantityColumnIndex = _cursor.getColumnIndex(ShoesEntry.COLUMN_QUANTITY);
        int idColumnIndex = _cursor.getColumnIndex(ShoesEntry._ID);

        String top_item = _context.getText(R.string.item_list_top_bar).toString();
        String bottom_item = _context.getText(R.string.item_list_bottom_bar).toString();
        String type_item = String.format(_context.getText(R.string.item_category_holder).toString(), shoeTypeMapping(_cursor.getInt(typeColumnIndex)));
        quantity = _cursor.getInt(quantityColumnIndex);

        final int id = _cursor.getInt(idColumnIndex);
        String quantity_item = String.format(_context.getText(R.string.item_quantity).toString(), String.valueOf(quantity));
        Log.i("cursorAtMain", String.valueOf(_cursor.getInt(quantityColumnIndex)));
        String price_item = String.format(_context.getText(R.string.item_price_holder).toString(), String.format("%.2f", Double.valueOf(_cursor.getInt(priceColumnIndex))));
        String size_item = String.format(_context.getText(R.string.item_size_holder).toString(), String.valueOf(_cursor.getInt(sizeColumnIndex)));
        holder.topItemView.setText(String.format(top_item, _cursor.getString(brandColumnIndex), _cursor.getString(nameColumnIndex)));
        holder.bottomItemView.setText(String.format(bottom_item, size_item, price_item, type_item, quantity_item));

        //Click listener for item to go into edit activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, DetailActivity.class);
                Uri currentPetUri = ContentUris.withAppendedId(ShoesEntry.CONTENT_URI, id);
                intent.setData(currentPetUri);
                _context.startActivity(intent);

            }
        });

        //Click listener for the sold button that will lower the quantity by 1 until 0
        holder.soldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 0) {
                    Toast.makeText(_context, _context.getText(R.string.no_item_alert).toString(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues myValue = new ContentValues();
                    _currentShoesUri = ContentUris.withAppendedId(ShoesEntry.CONTENT_URI, id);
                    int newQuantity = quantity - 1;
                    myValue.put(ShoesEntry.COLUMN_QUANTITY, newQuantity);
                    int rowCount = _context.getContentResolver().update(_currentShoesUri, myValue, null, null);
                    String msg = String.format(_context.getText(R.string.item_sold_alert).toString(), String.valueOf(newQuantity));
                    Toast.makeText(_context, msg,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        if (_cursor == null)
            return 0;
        else
            return _cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (newCursor == _cursor) {
            return;
        }

        if (newCursor != null) {
            Log.i("cursorAtMain", "actually swapping");
            _cursor = newCursor;
            _rowIDColumn = _cursor.getColumnIndexOrThrow(ShoesEntry._ID);
            _dataValid = true;
            // notify the observers about the new cursor
            notifyDataSetChanged();
        } else {
            notifyItemRangeRemoved(0, getItemCount());
            _cursor = null;
            _rowIDColumn = -1;
            _dataValid = false;
        }
    }

    private String shoeTypeMapping(int category) {
        switch (category) {
            case 1:
                return _context.getText(R.string.category_women).toString();
            case 2:
                return _context.getText(R.string.category_men).toString();
            case 3:
                return _context.getText(R.string.category_girls).toString();
            case 4:
                return _context.getText(R.string.category_boys).toString();
            default:
                return _context.getText(R.string.category_unknown).toString();

        }
    }

}
