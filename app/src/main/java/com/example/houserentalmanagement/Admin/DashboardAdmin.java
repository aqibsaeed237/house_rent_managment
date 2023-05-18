package com.example.houserentalmanagement.Admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.houserentalmanagement.R;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardAdmin extends AppCompatActivity {
    private FirebaseAuth auth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);


        CardView view = findViewById(R.id.view_House);
        CardView viewUser = findViewById(R.id.view_User);
        CardView viewOwner = findViewById(R.id.view_Owner);
        CardView viewFeedback = findViewById(R.id.view_FeedBack);
        CardView logOut = findViewById(R.id.logOut);
        CardView removehouse = findViewById(R.id.remove_house);
        CardView removemember = findViewById(R.id.remove_member);
        auth = FirebaseAuth.getInstance();

        viewUser.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ViewUser.class)));
        removehouse.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RemoveHouse.class)));
        removemember.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RemoveMember.class)));
        viewOwner.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ViewOwner.class)));

        viewFeedback.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ViewAllFeedback.class)));
        view.setOnClickListener(view1 -> startActivity(new Intent(getApplicationContext(), ViewAllHouses.class)));

        logOut.setOnClickListener(view12 -> {
            auth.signOut();
            Toast.makeText(DashboardAdmin.this, "Admin is logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), LoginAdmin.class));
           finish();


        });




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