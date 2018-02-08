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
        ImageView categoryImage = (ImageView) gridItemView.findViewById(R.id.grid_item_category_image);
        TextView categoryName = (TextView) gridItemView.findViewById(R.id.grid_item_category_name);
        categoryImage.setImageResource(mThumbIds[position]);
        categoryName.setText(categoryGridItem.getGetCategoryName());
        return gridItemView;
    }
    //references to our images
    private Integer[] mThumbIds={
            R.drawable.apparels_f,
            R.drawable.confec_f,
            R.drawable.souvenir_f,
            R.drawable.footwear_f,
            R.drawable.skincarebeauty_f,
            R.drawable.bagsaccessories_f,
            R.drawable.booksmusic_f,
            R.drawable.perfumes_f
    };

}
