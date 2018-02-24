package com.pickit.kronos.pickit;


/**
 * Created by ASUS on 27-01-2018.
 */

public class CategoryGridItem {
    private String CategoryImageId;
    private String getCategoryName;

    public CategoryGridItem(String categoryImageId, String getCategoryName) {
        this.CategoryImageId = categoryImageId;
        this.getCategoryName = getCategoryName;
    }

    public CategoryGridItem() {
    }

    public String getCategoryImageId() {
        return CategoryImageId;
    }

    public void setCategoryImageId(String categoryImageId) {
        CategoryImageId = categoryImageId;
    }

    public String getGetCategoryName() {
        return getCategoryName;
    }

    public void setGetCategoryName(String getCategoryName) {
        this.getCategoryName = getCategoryName;
    }
}
