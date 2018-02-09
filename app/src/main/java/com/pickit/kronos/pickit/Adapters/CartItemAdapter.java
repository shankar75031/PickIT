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

import com.pickit.kronos.pickit.Objects.CartItem;
import com.pickit.kronos.pickit.R;

import java.util.ArrayList;

/**
 * Created by ASUS on 28-01-2018.
 */

public class CartItemAdapter extends ArrayAdapter<CartItem>

    {
        private ArrayList<CartItem> cartItemArrayList;
    public CartItemAdapter(Activity context, ArrayList<CartItem> rows) {
        super(context, 0, rows);
        cartItemArrayList = rows;
    }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridItemView = convertView;
        CartItem cartItem = cartItemArrayList.get(position);
        if(gridItemView == null){
            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_category, parent, false);
        }
        ImageView categoryImage = (ImageView) gridItemView.findViewById(R.id.grid_item_category_image);
        TextView categoryName = (TextView) gridItemView.findViewById(R.id.grid_item_category_name);
        categoryName.setText(cartItem.getItemName());
        return gridItemView;
    }
}
