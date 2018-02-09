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

import com.pickit.kronos.pickit.Objects.CategoryGridItem;
import com.pickit.kronos.pickit.R;

import java.util.ArrayList;

public class CategoryGridItemAdapter extends ArrayAdapter<CategoryGridItem>{
    private ArrayList<CategoryGridItem> categoryGridItems;
    //References to our images
    private Integer[] mThumbIds = {
            R.drawable.apparels_f,
            R.drawable.confec_f,
            R.drawable.souvenir_f,
            R.drawable.footwear_f,
            R.drawable.skincarebeauty_f,
            R.drawable.bagsaccessories_f,
            R.drawable.booksmusic_f,
            R.drawable.perfumes_f
    };

    //CONSTRUCTOR
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

        //Initializing views
        ImageView categoryImage = gridItemView.findViewById(R.id.grid_item_category_image);
        TextView categoryName = gridItemView.findViewById(R.id.grid_item_category_name);

        //Setting values to views
        categoryImage.setImageResource(mThumbIds[position]);
        categoryName.setText(categoryGridItem.getGetCategoryName());

        return gridItemView;
    }

}
