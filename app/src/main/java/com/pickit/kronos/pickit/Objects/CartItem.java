package com.pickit.kronos.pickit.Objects;

/**
 * Created by ASUS on 28-01-2018.
 */

public class CartItem {
    String itemName;
    String itemPrice;
    String qty;
    String total;

    public CartItem(String itemName, String itemPrice, String qty) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.qty = qty;
        this.total = String.valueOf(Integer.parseInt(itemPrice) * Integer.parseInt(qty));
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice() {
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
        this.total = String.valueOf(Integer.parseInt(itemPrice) * Integer.parseInt(qty));
    }
}
