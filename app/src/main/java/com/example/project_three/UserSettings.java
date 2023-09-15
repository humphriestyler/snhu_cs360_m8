package com.example.project_three;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * User settings and SMS notification permissions.
 */
public class UserSettings extends AppCompatActivity {
    private Button btnSms;
    private static final int PERMISSION_REQUEST_SEND_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        // Initializes the button and sets a listener to request SMS permission
        btnSms = findViewById(R.id.btnSms);
        btnSms.setOnClickListener(v -> requestSmsPermission());
        // Initializes the back button and sets a listener to navigate back
        Button backButton = findViewById(R.id.backButton2);
        backButton.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        // Sends the user to the User Profile screen on click
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
        super.onBackPressed();
    }

    /**
     * Requests permission for sending SMS.
     */
    private void requestSmsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST_SEND_SMS);
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Sends SMS notification
                sendAlertSms();
            } else {
                // Shows a permission denied toast
                Toast.makeText(this, "SMS notifications permission denied",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Sends a test SMS with a message to a specific phone number.
     */
    private void sendAlertSms() {
        SmsManager smsManager = SmsManager.getDefault();
        String phoneNumber = "1234567890";
        String message = "Test";
        smsManager.sendTextMessage(phoneNumber,
                null, message, null, null);
    }
}