package com.pickit.kronos.pickit;

/**
 * Created by ASUS on 28-01-2018.
 */

public class CartItem {
    String itemName;
    String itemPrice;
    String qty;
    String total;
    int pickITCode;

    public CartItem(String itemName, String itemPrice, String qty, int pickITCode) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.qty = qty;
        this.total = String.valueOf(Integer.parseInt(itemPrice) * Integer.parseInt(qty));
        this.pickITCode = pickITCode;
    }

    public int getPickITCode() {
        return pickITCode;
    }

    public void setPickITCode(int pickITCode) {
        this.pickITCode = pickITCode;
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
