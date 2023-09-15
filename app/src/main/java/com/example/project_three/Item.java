package com.example.project_three;

/**
 * Manages inventory items and displaying them using RecyclerView.
 */
public class Item {
    private String itemName; // Item name
    private int itemQuantity; // Item quantity

    // Initializes an Item object
    public Item(String itemName, int itemQuantity) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    // Gets the item name
    public String getItemName() {
        return itemName;
    }

    // Gets the quantity of the item
    public int getItemQuantity() {
        return itemQuantity;
    }
}
