package com.pickit.kronos.pickit.Objects;

public class Item {

    //ATTRIBUTES
    private String itemName;
    private String Price;
    private String Description;
    private String image;
    //CONSTRUCTORS
    public Item() {
    }

    public Item(String image, String itemName, String price, String description) {

        this.itemName = itemName;
        this.Price = price;
        this.Description = description;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //GETTER METHODS
    public String getItemName() {
        return itemName;
    }

    //SETTER METHODS
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}
