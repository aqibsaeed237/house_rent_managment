package com.example.houserentalmanagement.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.houserentalmanagement.HouseModel;
import com.example.houserentalmanagement.R;
import com.example.houserentalmanagement.adapter.RemoveHouseAdmin;
import com.example.houserentalmanagement.houseOwner.RegisterOwner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RemoveHouse extends AppCompatActivity {
    private RecyclerView rv_show;
    private RemoveHouseAdmin adapter;
    private ArrayList<HouseModel> mList = new ArrayList<>();
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_house);

        rv_show = findViewById(R.id.recyclerView);
        databaseReference = FirebaseDatabase.getInstance().getReference(RegisterOwner.HOUSES);
        rv_show.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(com.example.houserentalmanagement.Admin.RemoveHouse.this);
        rv_show.setLayoutManager(linearLayoutManager);
        mList = new ArrayList<>();
        adapter = new RemoveHouseAdmin(this, mList);
        rv_show.setAdapter(adapter);
        mList.clear();
        getAllArticle();
    }
    private void getAllArticle() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();
        if (firebaseUser.getUid() != null) {
            Query query = FirebaseDatabase.getInstance().getReference().child(RegisterOwner.HOUSES);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ArrayList<HouseModel> houseModelArrayList = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            HouseModel houseModel = ds.getValue(HouseModel.class);
                            houseModelArrayList.add(houseModel);
                        }
                    }
                    RemoveHouseAdmin adapter = new RemoveHouseAdmin(RemoveHouse.this, houseModelArrayList);
                    rv_show.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
