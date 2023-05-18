package com.example.houserentalmanagement.houseOwner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.houserentalmanagement.R;

public class HouseDetails extends AppCompatActivity {

    TextView tv_houseDesc;
    ImageView iv_houseImage;
    Button btn_addMember, btn_viewMember;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);

        Intent intent = getIntent();
        String houseId = intent.getStringExtra("houseId");
        String noOfRoom = intent.getStringExtra("noOfRoom");
        String rentPerRoom = intent.getStringExtra("rentPerRoom");
        String houseDescription = intent.getStringExtra("houseDescription");
        String houseLocation = intent.getStringExtra("houseLocation");
        String houseImage = intent.getStringExtra("houseImage");
        String userId = intent.getStringExtra("userId");

        tv_houseDesc = findViewById(R.id.tv_houseDesc);
        iv_houseImage = findViewById(R.id.iv_houseImage);
        btn_addMember = findViewById(R.id.btn_addMember);
        btn_viewMember = findViewById(R.id.btn_viewMember);




        tv_houseDesc.setText(houseDescription);
        Glide.with(HouseDetails.this).load(houseImage).into(iv_houseImage);
        Intent intent1 = new Intent();


        btn_addMember.setOnClickListener(v -> {
            Intent intent11 = new Intent(HouseDetails.this, AddMember.class);
            intent11.putExtra("houseId", houseId);
            intent11.putExtra("noOfRoom", noOfRoom);
            intent11.putExtra("rentPerRoom", rentPerRoom);
            intent11.putExtra("houseDescription", houseDescription);
            intent11.putExtra("houseLocation", houseLocation);
            intent11.putExtra("houseImage", houseImage);
            intent11.putExtra("userId", userId);
            startActivity(intent11);
        });

        btn_viewMember.setOnClickListener(v -> {
            Intent intent112 = new Intent(HouseDetails.this, ViewMembers.class);
            intent112.putExtra("houseId", houseId);
            intent112.putExtra("noOfRoom", noOfRoom);
            intent112.putExtra("rentPerRoom", rentPerRoom);
            intent112.putExtra("houseDescription", houseDescription);
            intent112.putExtra("houseLocation", houseLocation);
            intent112.putExtra("houseImage", houseImage);
            intent112.putExtra("userId", userId);
            startActivity(intent112);
        });

    }
}