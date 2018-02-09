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

/**
 * Created by ASUS on 27-01-2018.
 */

public class ItemGridItemAdapter extends ArrayAdapter<Item> {
    private ArrayList<Item> itemGridItems;
    public ItemGridItemAdapter(Activity context, ArrayList<Item> rows) {
        super(context, 0, rows);
        itemGridItems = rows;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridItemView = convertView;
        Item item = itemGridItems.get(position);
        if(gridItemView == null){
            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_item, parent, false);
        }
        ImageView itemImage = (ImageView) gridItemView.findViewById(R.id.grid_item_item_image);
        TextView itemName = (TextView) gridItemView.findViewById(R.id.grid_item_item_name);
        TextView itemPrice = (TextView) gridItemView.findViewById(R.id.grid_item_item_price);
        itemImage.setImageResource(R.drawable.shopping_bag);
        itemName.setText(item.getItemName());
        itemPrice.setText(item.getPrice());
        return gridItemView;


    }
}
