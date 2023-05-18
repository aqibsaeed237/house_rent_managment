package com.example.houserentalmanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.houserentalmanagement.Admin.LoginAdmin;
import com.example.houserentalmanagement.houseOwner.LoginOwner;
import com.example.houserentalmanagement.user.LoginUser;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // Initialize variables using findViewById() method or view binding
        // Declare variables using PascalCase
        CardView userLoginCardView = findViewById(R.id.user_login);
        CardView ownerLoginCardView = findViewById(R.id.owner_login);
        CardView adminLoginCardView = findViewById(R.id.admin_login);

            // Set onClickListener for each CardView
            userLoginCardView.setOnClickListener(view -> {
                // Start LoginUser activity when userLoginCardView is clicked
                startActivity(new Intent(getApplicationContext(), LoginUser.class));
            });

            ownerLoginCardView.setOnClickListener(v -> {
                // Start LoginOwner activity when ownerLoginCardView is clicked
                startActivity(new Intent(MainActivity.this, LoginOwner.class));
            });

            adminLoginCardView.setOnClickListener(v -> {
                // Start LoginAdmin activity when adminLoginCardView is clicked
                startActivity(new Intent(MainActivity.this, LoginAdmin.class));
            });

        }
    }
