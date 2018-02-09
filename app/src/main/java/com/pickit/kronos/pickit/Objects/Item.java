package com.pickit.kronos.pickit.Objects;

/**
 * Created by ASUS on 27-01-2018.
 */

public class Item {
    private String itemName;
    private String Price;
    private String Description;

    public Item() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Item(String itemName, String Price, String Description) {

        this.itemName = itemName;
        this.Price = Price;
        this.Description = Description;
    }

}
