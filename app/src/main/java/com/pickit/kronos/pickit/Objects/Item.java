package com.pickit.kronos.pickit.Objects;

public class Item {

    //ATTRIBUTES
    private String mItemName;
    private String mPrice;
    private String mDescription;

    //CONSTRUCTORS
    public Item() {
    }

    public Item(String itemName, String price, String description) {

        this.mItemName = itemName;
        this.mPrice = price;
        this.mDescription = description;
    }

    //GETTER METHODS
    public String getItemName() {
        return mItemName;
    }

    //SETTER METHODS
    public void setItemName(String itemName) {
        this.mItemName = itemName;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        this.mPrice = price;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }
}
