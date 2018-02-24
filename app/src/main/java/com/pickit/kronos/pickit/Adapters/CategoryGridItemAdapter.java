package com.pickit.kronos.pickit;

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

import java.util.ArrayList;

/**
 * Created by ASUS on 27-01-2018.
 */

public class CategoryGridItemAdapter extends ArrayAdapter<CategoryGridItem>{
    private ArrayList<CategoryGridItem> categoryGridItems;
    public CategoryGridItemAdapter(Activity context, ArrayList<CategoryGridItem> rows) {
        super(context, 0, rows);
        categoryGridItems = rows;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridItemView = convertView;
        CategoryGridItem categoryGridItem = categoryGridItems.get(position);
        if(gridItemView == null){
            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_category, parent, false);
        }
        ImageView categoryImage = gridItemView.findViewById(R.id.grid_item_category_image);
        TextView categoryName = gridItemView.findViewById(R.id.grid_item_category_name);
        Glide.with(categoryImage.getContext())
                .load(categoryGridItem.getCategoryImageId())
                .into(categoryImage);
        categoryName.setText(categoryGridItem.getGetCategoryName());
        return gridItemView;
    }
}
