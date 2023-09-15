package com.example.project_three;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Connects item objects with individual items in a RecyclerView
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private Database database = null; // References the database
    private final List<Item> itemList; // List to store items for display

    /**
     * Initializes the ItemAdapter with the list of items to display.
     *
     * @param itemList The list of Item objects to be displayed.
     */
    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Creates a new Database instance
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid, parent, false);
        database = new Database(itemView.getContext());
        // Return a new ItemViewHolder
        return new ItemViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Get the item at the specified position
        Item item = itemList.get(position);
        // Set the item's name and quantity
        holder.itemNameTextView.setText(item.getItemName());
        holder.itemQuantityTextView.setText(String.valueOf(item.getItemQuantity()));
        // Creates delete button
        Button deleteButton = holder.itemView.findViewById(R.id.deleteButton);
        // Deletes the item when delete button is clicked
        deleteButton.setOnClickListener(v -> {
            String itemName = item.getItemName();
            // Delete the item from the database
            int deletedRows = database.deleteInventoryItem(itemName);
            if (deletedRows > 0) {
                // Remove the item from the list and sends success or fail toasts
                // Also updates location of the other existing items on the list
                itemList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, itemList.size());
                Toast.makeText(v.getContext(), "Item deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "Failed to delete item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Class used to store and connect RecyclerView items
     */
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView;
        TextView itemQuantityTextView;

        /**
         * Initializes the TextViews for item name and quantity.
         *
         * @param itemView The view representing the RecyclerView item.
         */
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemQuantityTextView = itemView.findViewById(R.id.itemQuantityTextView);
        }
    }
}