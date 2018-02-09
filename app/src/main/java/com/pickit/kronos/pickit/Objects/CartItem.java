package com.pickit.kronos.pickit.Objects;



public class CartItem {

    //ATTRIBUTES
    private String mItemName;
    private String mItemPrice;
    private String mQuantity;
    private String mTotal;

    //CONSTRUCTOR
    public CartItem(String itemName, String itemPrice, String qty) {
        this.mItemName = itemName;
        this.mItemPrice = itemPrice;
        this.mQuantity = qty;
        this.mTotal = String.valueOf(Integer.parseInt(mItemPrice) * Integer.parseInt(mQuantity));
    }

    //GETTER METHODS
    public String getItemName() {
        return mItemName;
    }

    //SETTER METHODS
    public void setItemName(String itemName) {
        this.mItemName = itemName;
    }

    public String getItemPrice() {
        return mItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.mItemPrice = itemPrice;
    }

    public String getQty() {
        return mQuantity;
    }

    public void setQty(String qty) {
        this.mQuantity = qty;
    }

    public String getTotal() {
        return mTotal;
    }

    public void setTotal(String total) {
        this.mTotal = String.valueOf(Integer.parseInt(mItemPrice) * Integer.parseInt(mQuantity));
    }
}
