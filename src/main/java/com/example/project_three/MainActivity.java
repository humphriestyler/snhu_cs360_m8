package com.example.project_three;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Handles the login and account creation functionality for the application.
 */
public class MainActivity extends AppCompatActivity {
    private Database database; // Database instance for managing database operations
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the layout for the activity
        database = new Database(this); // Initializes the database
        // Find the UI elements
        EditText inputUsername = findViewById(R.id.inputUsername);
        EditText inputPassword = findViewById(R.id.inputPassword);
        Button loginButton = findViewById(R.id.loginButton);
        Button newAccountButton = findViewById(R.id.newAccountButton);
        // Set listener for new account button
        newAccountButton.setOnClickListener(v -> {
            String username = inputUsername.getText().toString();
            String password = inputPassword.getText().toString();
            // Insert the new user account into the database and sends success or failure toasts
            long insertResult = database.insertUser(username, password);
            if (insertResult != -1) {
                Toast.makeText(MainActivity.this, "Account created successfully",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Account creation failed",
                        Toast.LENGTH_SHORT).show();
            }
        });
        // Set listener for login button
        loginButton.setOnClickListener(v -> {
            String username = inputUsername.getText().toString();
            String password = inputPassword.getText().toString();
            // Check the user credentials using the database and sends success or failure toasts
            if (database.checkUser(username, password)) {
                Toast.makeText(MainActivity.this, "Logged in!",
                        Toast.LENGTH_SHORT).show();
                // Sends the user to the User Profile screen after logging in
                Intent intent = new Intent(MainActivity.this,
                        UserProfile.class);
                startActivity(intent);
            } else {
                // Display a failure message
                Toast.makeText(MainActivity.this, "Could not log in",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}