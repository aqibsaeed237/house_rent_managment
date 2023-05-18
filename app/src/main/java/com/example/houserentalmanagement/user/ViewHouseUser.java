package com.example.houserentalmanagement.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.houserentalmanagement.HouseModel;
import com.example.houserentalmanagement.R;
import com.example.houserentalmanagement.adapter.ViewHouseAdapterUser;
import com.example.houserentalmanagement.houseOwner.RegisterOwner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewHouseUser extends AppCompatActivity {

    private RecyclerView rv_showAllFood;
    private ViewHouseAdapterUser adapter;
    private final ArrayList<HouseModel> mList = new ArrayList<>();
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_house_user);
        setTitle("Dashboard User");

        rv_showAllFood = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        rv_showAllFood.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewHouseUser.this);
        rv_showAllFood.setLayoutManager(linearLayoutManager);
        getAllArticle();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

    }
    public void searchList(String text){
        ArrayList<HouseModel> searchList = new ArrayList<>();
        for(HouseModel model: mList){
            if(model.getHouseLocation().toLowerCase().contains(text.toLowerCase())){
                searchList.add(model);
            }
        }
        adapter.searchData(searchList);

    }




    private void getAllArticle() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();
        if (firebaseUser.getUid() != null) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RegisterOwner.HOUSES);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    mList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            HouseModel article = dataSnapshot1.getValue(HouseModel.class);
                            mList.add(article);
                        }
                    }
                 //   Log.d("TAG1", "onDataChange: " + mList.get(0));
                    adapter = new ViewHouseAdapterUser(ViewHouseUser.this, mList, 0);
                    rv_showAllFood.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }


    }

}