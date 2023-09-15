package com.example.project_three;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages inventory items and displaying them using RecyclerView.
 */
public class Inventory extends AppCompatActivity {

    private Database database; // Database instance for managing database operations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory); // Set the layout for the activity
        database = new Database(this); // Initializes the database
        // Initialize UI elements
        EditText editItemName = findViewById(R.id.editItemName);
        EditText editItemQuantity = findViewById(R.id.editItemQuantity);
        Button createItemButton = findViewById(R.id.createItemButton);
        // Set listener for creating new items and adds toasts for success or failure
        createItemButton.setOnClickListener(v -> {
            String itemName = editItemName.getText().toString().trim();
            int itemQuantity = Integer.parseInt(editItemQuantity.getText().toString().trim());
            long insertResult = database.insertInventoryItem(itemName, itemQuantity);
            if (insertResult != -1) {
                setResult(RESULT_OK);
                Toast.makeText(Inventory.this, "Item Added!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Inventory.this, "Problem Adding Item!",
                        Toast.LENGTH_SHORT).show();
            }
        });
        // Get item names from the database
        List<Item> itemList = new ArrayList<>();
        List<String> itemNames = database.getItemNames();
        // Create Item objects and populate the item list
        for (String itemName : itemNames) {
            int itemQuantity = database.getItemQuantity(itemName);
            itemList.add(new Item(itemName, itemQuantity));
        }
        // Set up RecyclerView for displaying items
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ItemAdapter itemAdapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(itemAdapter);
        // Set up click listener for the back button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        // Sends user back to UserProfile on press
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
        super.onBackPressed();
    }
}