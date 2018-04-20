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

import com.bumptech.glide.Glide;
import com.pickit.kronos.pickit.Objects.CartItem;
import com.pickit.kronos.pickit.R;

import java.util.ArrayList;

public class CartItemAdapter extends ArrayAdapter<CartItem>

{
    private ArrayList<CartItem> mCartItemArrayList;

    //CONSTRUCTOR
    public CartItemAdapter(Activity context, ArrayList<CartItem> rows) {
        super(context, 0, rows);
        mCartItemArrayList = rows;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridItemView = convertView;
        CartItem cartItem = mCartItemArrayList.get(position);
        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.cart_list_item, parent, false);
        }

        //Initializing views
        ImageView cartImage = gridItemView.findViewById(R.id.cart_item_image);
        TextView cartName = gridItemView.findViewById(R.id.cart_item_name);
        TextView cartRate = gridItemView.findViewById(R.id.cart_item_price);
        TextView cartQty = gridItemView.findViewById(R.id.cart_item_quantity);
        TextView cartTotal = gridItemView.findViewById(R.id.cart_item_total);



        //Setting values to views
        cartName.setText(cartItem.getItemName());
        cartQty.setText(cartItem.getQty());
        cartRate.setText(cartItem.getItemPrice());
        cartTotal.setText(cartItem.getTotal());
        Glide.with(cartImage.getContext())
                .load(cartItem.getImage())
                .into(cartImage);

        return gridItemView;
    }
}
