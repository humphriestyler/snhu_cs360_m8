package com.example.project_three;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Displays the user profile options
 */
public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        // Set up listener for the inventory button
        Button inventoryButton = findViewById(R.id.inventoryButton);
        inventoryButton.setOnClickListener(v -> {
            // Start the Inventory when the inventory button is clicked
            Intent intent = new Intent(UserProfile.this,
                    Inventory.class);
            startActivity(intent);
        });
        // Set up listener for the user settings button
        Button userSettingsButton = findViewById(R.id.userSettingsButton);
        userSettingsButton.setOnClickListener(v -> {
            // Start the User Settings when the user settings button is clicked
            Intent intent = new Intent(UserProfile.this,
                    UserSettings.class);
            startActivity(intent);
        });
    }
}