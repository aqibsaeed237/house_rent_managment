package com.example.houserentalmanagement.Admin;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.houserentalmanagement.Model.FeedBack;
import com.example.houserentalmanagement.R;
import com.example.houserentalmanagement.adapter.AdapterFeed;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewAllFeedback extends AppCompatActivity {
    AdapterFeed adapterFeed;
    List<FeedBack> feedBackList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager  layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_view);
        feedBackList=new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(ViewAllFeedback.this);
        recyclerView.setLayoutManager(layoutManager);
        loadSearch();
    }

    private void loadSearch() {
        Toast.makeText(this, "Searching...", Toast.LENGTH_SHORT).show();

        Query ref = FirebaseDatabase.getInstance().getReference("FeedBack");
        feedBackList.clear();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    FeedBack feedBack = ds.getValue(FeedBack.class);
                    feedBackList.add(feedBack);
                }

                //adapter
                adapterFeed= new AdapterFeed(ViewAllFeedback.this, feedBackList);
                recyclerView.setLayoutManager(new LinearLayoutManager(ViewAllFeedback.this, LinearLayoutManager.VERTICAL, false));

                //set adapter to recycle
                recyclerView.setAdapter(adapterFeed);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //     Toast.makeText(getActivity(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}