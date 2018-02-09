package com.pickit.kronos.pickit.Objects;

public class CategoryGridItem {

    //ATTRIBUTES
    private int mCategoryImageId;
    private String mCategoryName;

    //CONSTRUCTORS
    public CategoryGridItem(int categoryImageId, String categoryName) {
        this.mCategoryImageId = categoryImageId;
        this.mCategoryName = categoryName;
    }

    //GETTER METHODS
    public int getCategoryImageId() {
        return mCategoryImageId;
    }

    //SETTER METHODS
    public void setCategoryImageId(int categoryImageId) {
        this.mCategoryImageId = categoryImageId;
    }

    public String getGetCategoryName() {
        return mCategoryName;
    }

    public void setGetCategoryName(String categoryName) {
        this.mCategoryName = categoryName;
    }
}
