package com.pickit.kronos.pickit.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pickit.kronos.pickit.Objects.Item;
import com.pickit.kronos.pickit.R;

import java.util.ArrayList;

public class ItemGridItemAdapter extends ArrayAdapter<Item> {
    private ArrayList<Item> mGridItemsArrayList;
    private View mGridItemView;
    private ImageView mItemImageView;
    private TextView mItemNameTextView, mItemPriceTextView;
    private Item mItem;

    //CONSTRUCTOR
    public ItemGridItemAdapter(Activity context, ArrayList<Item> rows) {
        super(context, 0, rows);
        mGridItemsArrayList = rows;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        mGridItemView = convertView;
        mItem = mGridItemsArrayList.get(position);
        if (mGridItemView == null) {
            mGridItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_item, parent, false);
        }

        initViews();

        setViews();

        return mGridItemView;
    }

    //Links xml view id with java view objects
    private void initViews() {
        mItemImageView = mGridItemView.findViewById(R.id.grid_item_item_image);
        mItemNameTextView = mGridItemView.findViewById(R.id.grid_item_item_name);
        mItemPriceTextView = mGridItemView.findViewById(R.id.grid_item_item_price);
    }

    private void setViews() {
        mItemImageView.setImageResource(R.drawable.shopping_bag);
        mItemNameTextView.setText(mItem.getItemName());
        mItemPriceTextView.setText(mItem.getPrice());
    }
}
