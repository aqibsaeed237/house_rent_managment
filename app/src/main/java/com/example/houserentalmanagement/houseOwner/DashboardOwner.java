package com.example.houserentalmanagement.houseOwner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.houserentalmanagement.R;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardOwner extends AppCompatActivity {

    CardView viewRental, addHouse, logOut,  feed, remove;
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_owner);
        setTitle("Dashboard Owner");

        addHouse = findViewById(R.id.addHouse);
        feed = findViewById(R.id.feed);
        viewRental = findViewById(R.id.viewHouse);
        logOut = findViewById(R.id.logOut);
        remove = findViewById(R.id.remove);
        auth = FirebaseAuth.getInstance();


        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RemoveActivity.class));
            }
        });
        feed.setOnClickListener(v -> startActivity(new Intent(DashboardOwner.this, OwnerFeedback.class)));

        logOut.setOnClickListener(view -> {
          //  startActivity(new Intent(DashboardOwner.this, RemoveActivity.class));
            auth.signOut();
            Toast.makeText(DashboardOwner.this, "Owner is logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(DashboardOwner.this, LoginOwner.class));
            finish();

        });



        viewRental.setOnClickListener(v -> startActivity(new Intent(DashboardOwner.this, ViewHouse.class)));

        addHouse.setOnClickListener(v -> startActivity(new Intent(DashboardOwner.this, AddHouse.class)));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.actionExit) {
            finish();
            return true;
        }




        return super.onOptionsItemSelected(item);

    }
}