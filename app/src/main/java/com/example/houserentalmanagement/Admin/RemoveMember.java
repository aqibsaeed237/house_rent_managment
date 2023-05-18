package com.example.houserentalmanagement.Admin;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.houserentalmanagement.MemberModel;
import com.example.houserentalmanagement.R;
import com.example.houserentalmanagement.adapter.RemoveMemberAdapter;
import com.example.houserentalmanagement.houseOwner.RegisterOwner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RemoveMember extends AppCompatActivity {

        private RecyclerView rv_showAll;
        private RemoveMemberAdapter adapter;
        private final ArrayList<MemberModel> mList = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_member);

            setTitle("View Member");



            rv_showAll = findViewById(R.id.recyclerView);
            rv_showAll.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RemoveMember.this);
            rv_showAll.setLayoutManager(linearLayoutManager);

            getAllArticle();
        }

    private void getAllArticle() {
        DatabaseReference membersRef = FirebaseDatabase.getInstance().getReference().child(RegisterOwner.MEMBERS);
        membersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mList.clear(); // Clear the list before adding new data
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot memberSnapshot : dataSnapshot.getChildren()) {
                        MemberModel memberModel = memberSnapshot.getValue(MemberModel.class);
                        mList.add(memberModel);
                    }
                }
                adapter = new RemoveMemberAdapter(RemoveMember.this, mList);
                rv_showAll.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }


}
