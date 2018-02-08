package com.pickit.kronos.pickit;

/**
 * Created by ASUS on 27-01-2018.
 */

public class CategoryGridItem {
    private int CategoryImageId;
    private String getCategoryName;

    public CategoryGridItem(int categoryImageId, String getCategoryName) {
        CategoryImageId = categoryImageId;
        this.getCategoryName = getCategoryName;
    }

    public int getCategoryImageId() {
        return CategoryImageId;
    }

    public void setCategoryImageId(int categoryImageId) {
        CategoryImageId = categoryImageId;
    }

    public String getGetCategoryName() {
        return getCategoryName;
    }

    public void setGetCategoryName(String getCategoryName) {
        this.getCategoryName = getCategoryName;
    }
}
