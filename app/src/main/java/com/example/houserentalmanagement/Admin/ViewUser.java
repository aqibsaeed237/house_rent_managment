package com.example.houserentalmanagement.Admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.houserentalmanagement.Model.mUser;
import com.example.houserentalmanagement.R;
import com.example.houserentalmanagement.adapter.AdapterViewUsers;
import com.example.houserentalmanagement.user.RegisterUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewUser extends AppCompatActivity {
    RecyclerView rv;
    AdapterViewUsers adapter;
    ArrayList<mUser> userList ;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        setTitle("Users List");



        rv = findViewById(R.id.recyclerView);
        databaseReference = FirebaseDatabase.getInstance().getReference(RegisterUser.USERS);
        rv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewUser.this);
        rv.setLayoutManager(linearLayoutManager);
        userList = new ArrayList<>();
        adapter = new AdapterViewUsers(this, userList, "Users");
        rv.setAdapter(adapter);
        loadSearch();
    }

    private void loadSearch() {
        //  Toast.makeText(this, "Searching...", Toast.LENGTH_SHORT).show();
        //path
        Query ref = FirebaseDatabase.getInstance().getReference(RegisterUser.USERS);
        userList.clear();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    mUser mUsers = ds.getValue(mUser.class);

                    userList.add(mUsers);
                }

                //adapter
                adapter= new AdapterViewUsers(ViewUser.this, userList, "Users");
                rv.setLayoutManager(new LinearLayoutManager(ViewUser.this, LinearLayoutManager.VERTICAL, false));

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