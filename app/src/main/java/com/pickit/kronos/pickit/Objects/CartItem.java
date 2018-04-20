package com.pickit.kronos.pickit.Objects;



public class CartItem {

    //ATTRIBUTES
    private String itemName;
    private String itemPrice;
    private String qty;
    private String total;
    private String image;

    //CONSTRUCTOR
    public CartItem( String image, String itemName, String itemPrice, String qty) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.qty = qty;
        this.total = String.valueOf(Integer.parseInt(itemPrice) * Integer.parseInt(qty));
        this.image = image;
    }

    //GETTER METHODS
    public String getItemName() {
        return itemName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    //SETTER METHODS
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public CartItem() {

    }
}
