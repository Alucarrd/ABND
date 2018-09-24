package com.petina.android.inventoryapp;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.petina.android.inventoryapp.data.ShoesContract.ShoesEntry;

public class ShoesRecyclerAdapter extends RecyclerView.Adapter<ShoesRecyclerAdapter.ItemViewHolder>{
    private Cursor _cursor;
    private Context _context;
    private boolean _dataValid;
    private int _rowIDColumn;
    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public TextView topItemView, bottomItemView;
        public ItemViewHolder(View itemView){
            super(itemView);
            topItemView = (TextView) itemView.findViewById(R.id.list_item_top);
            bottomItemView = (TextView) itemView.findViewById(R.id.list_item_bottom);

        }
    }

    public ShoesRecyclerAdapter(Context context, Cursor cursor){
        _context = context;
        _cursor = cursor;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(_context).inflate(R.layout.list_item,parent, false );
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        _cursor.moveToPosition(position);
        Log.v("cursorAtMain", "current position:" + String.valueOf(position));
        int nameColumnIndex = _cursor.getColumnIndex(ShoesEntry.COLUMN_SHOES_NAME);
        int brandColumnIndex = _cursor.getColumnIndex(ShoesEntry.COLUMN_BRAND);
        int priceColumnIndex = _cursor.getColumnIndex(ShoesEntry.COLUMN_PRICE);
        int sizeColumnIndex = _cursor.getColumnIndex(ShoesEntry.COLUMN_SHOES_SIZE);
        int typeColumnIndex = _cursor.getColumnIndex(ShoesEntry.COLUMN_CATEGORY_TYPE);
        String top_item = _context.getText(R.string.item_list_top_bar).toString();
        String bottom_item = _context.getText(R.string.item_list_bottom_bar).toString();
        String type_item = String.format(_context.getText(R.string.item_category_holder).toString(), shoeTypeMapping(_cursor.getInt(typeColumnIndex)));
        Log.i("cursorAtMain", type_item);
        String price_item = String.format(_context.getText(R.string.item_price_holder).toString(), String.valueOf(Double.valueOf(_cursor.getInt(priceColumnIndex))));
        String size_item = String.format(_context.getText(R.string.item_size_holder).toString(), String.valueOf(_cursor.getInt(sizeColumnIndex)));
        holder.topItemView.setText(String.format(top_item, _cursor.getString(brandColumnIndex), _cursor.getString(nameColumnIndex)));
        holder.bottomItemView.setText(String.format(bottom_item, size_item, price_item, type_item));


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(_context, EditActivity.class);
                int idColumnIndex = _cursor.getColumnIndex(ShoesEntry._ID);
                int id = _cursor.getInt(idColumnIndex);
                Uri currentPetUri = ContentUris.withAppendedId(ShoesEntry.CONTENT_URI, id);
                intent.setData(currentPetUri);
                _context.startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        if(_cursor == null)
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
    private String shoeTypeMapping(int category){
        switch(category){
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
