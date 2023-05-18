package com.example.houserentalmanagement.houseOwner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.houserentalmanagement.Admin.ViewUser;
import com.example.houserentalmanagement.HouseModel;
import com.example.houserentalmanagement.R;
import com.example.houserentalmanagement.adapter.RemoveHouseAdapterOwner;
import com.example.houserentalmanagement.user.RegisterUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RemoveActivity extends AppCompatActivity {
    private RecyclerView rv_showAllFood;
    private RemoveHouseAdapterOwner adapter;
    private ArrayList<HouseModel> mList = new ArrayList<>();
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        rv_showAllFood = findViewById(R.id.recyclerView);
        databaseReference = FirebaseDatabase.getInstance().getReference(RegisterOwner.HOUSES);
        rv_showAllFood.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RemoveActivity.this);
        rv_showAllFood.setLayoutManager(linearLayoutManager);
        mList = new ArrayList<>();
        adapter = new RemoveHouseAdapterOwner(this, mList);
        rv_showAllFood.setAdapter(adapter);
        mList.clear();
        getAllArticle();
    }
    private void getAllArticle() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();
        if (firebaseUser.getUid() != null) {
            Query reference = FirebaseDatabase.getInstance().getReference().child(RegisterOwner.HOUSES).child(userId);

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        HouseModel article = dataSnapshot.getValue(HouseModel.class);
                        mList.add(article);
                    }
                    adapter = new RemoveHouseAdapterOwner(RemoveActivity.this, mList);
                    rv_showAllFood.setLayoutManager(new LinearLayoutManager(RemoveActivity.this,
                            LinearLayoutManager.VERTICAL, false));
                    rv_showAllFood.setAdapter(adapter);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
