package com.example.houserentalmanagement.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.houserentalmanagement.R;

public class ViewHouseDetail extends AppCompatActivity {
    ImageView img;
    TextView ownerId,Room,address, description, rent, hId;
    String key = "";





    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_houses);

        img = findViewById(R.id.img);
        hId = findViewById(R.id.hoseId);
        ownerId = findViewById(R.id.ownerId);
        Room = findViewById(R.id.noOfRoom);
        address = findViewById(R.id.address);
        description = findViewById(R.id.description);
        rent = findViewById(R.id.rent);





        Intent intent = getIntent();
        String houseId = intent.getStringExtra("houseId");
        String noOfRoom = intent.getStringExtra("noOfRoom");
        String rentPerRoom = intent.getStringExtra("rentPerRoom");
        String houseDescription = intent.getStringExtra("houseDescription");
        String houseLocation = intent.getStringExtra("houseLocation");
        String houseImage = intent.getStringExtra("houseImage");
        String userId = intent.getStringExtra("userId");
        key = intent.getStringExtra("key");


        address.setText("Location: "+ houseLocation);
        Glide.with(getApplicationContext()).load(houseImage).into(img);
        ownerId.setText("id: " + userId );
        Room.setText("No of Room: "+noOfRoom);
        description.setText("Detail: "+ houseDescription);
        rent.setText("Rent Per Room: "+rentPerRoom);
        hId.setText("HouseId: "+ houseId);







    }
}






