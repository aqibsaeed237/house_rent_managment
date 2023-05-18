package com.example.houserentalmanagement.user;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.houserentalmanagement.R;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardUserMain extends AppCompatActivity {
    private CardView viewHouse, logOut, feedback;
    Button exit;
    private FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_user_main);



        feedback = findViewById(R.id.sendFeed);
        exit = findViewById(R.id.exit);
        viewHouse = findViewById(R.id.viewHouse);
        logOut = findViewById(R.id.logOut);
        auth = FirebaseAuth.getInstance();


        feedback.setOnClickListener(v -> startActivity(new Intent(DashboardUserMain.this, SendFeedback.class)));


        viewHouse.setOnClickListener(v -> startActivity(new Intent(DashboardUserMain.this, ViewHouseUser.class)));

        logOut.setOnClickListener(view -> {
            auth.signOut();
            Toast.makeText(DashboardUserMain.this, "User is logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), LoginUser.class));
            finish();


        });
        exit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(DashboardUserMain.this);
            builder.setTitle("Are you Sure?");
//                builder.setMessage("You Want to exit");

            builder.setPositiveButton("exit", (dialog, which) -> finish());
            builder.setNegativeButton("cancel", (dialog, which) -> Toast.makeText(getApplicationContext(), "Cancelled", Toast.LENGTH_SHORT).show());

            builder.show();
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