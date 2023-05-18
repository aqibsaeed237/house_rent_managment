package com.example.houserentalmanagement.Admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.houserentalmanagement.Model.Owner;
import com.example.houserentalmanagement.R;
import com.example.houserentalmanagement.adapter.AdapterViewOwner;
import com.example.houserentalmanagement.houseOwner.RegisterOwner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewOwner extends AppCompatActivity {

    RecyclerView rv;
    AdapterViewOwner adapter;
    ArrayList<Owner> ownerList ;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_owner);
        setTitle("Owners List");



        rv = findViewById(R.id.recyclerView);
        databaseReference = FirebaseDatabase.getInstance().getReference(RegisterOwner.OWNER);
        rv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewOwner.this);
        rv.setLayoutManager(linearLayoutManager);
        ownerList = new ArrayList<>();
        adapter = new AdapterViewOwner(this, ownerList, "Owner");
        rv.setAdapter(adapter);
        loadSearch();
    }

    private void loadSearch() {
        //  Toast.makeText(this, "Searching...", Toast.LENGTH_SHORT).show();
        //path
        Query ref = FirebaseDatabase.getInstance().getReference(RegisterOwner.OWNER);
        ownerList.clear();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Owner mode = ds.getValue(Owner.class);

                    ownerList.add(mode);
                }

                //adapter
                adapter= new AdapterViewOwner(ViewOwner.this, ownerList, "Owner");
                rv.setLayoutManager(new LinearLayoutManager(ViewOwner.this, LinearLayoutManager.VERTICAL, false));

                //set adapter to recycle
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}