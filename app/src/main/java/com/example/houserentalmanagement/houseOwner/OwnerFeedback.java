package com.example.houserentalmanagement.houseOwner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.houserentalmanagement.Model.FeedBack;
import com.example.houserentalmanagement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OwnerFeedback extends AppCompatActivity {
    Button feedBtn;
    EditText editText;
    String mUID;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_feedback);
        checkforuserlogin();
        feedBtn=findViewById(R.id.feedBtn);
        editText=findViewById(R.id.editText);
        //init firebase
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("FeedBack");
        feedBtn.setOnClickListener(view -> {
            if(!editText.getText().toString().isEmpty()){
                FeedBack feedBack=new FeedBack(mUID,editText.getText().toString());
                databaseReference.child(mUID).setValue(feedBack);
                Toast.makeText(OwnerFeedback.this, "FeedBack Sended", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OwnerFeedback.this, DashboardOwner.class));
                finish();
            }else{
                Toast.makeText(OwnerFeedback.this, "Fill FeedBack", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void checkforuserlogin() {
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            //  token=FirebaseInstanceId.getInstance().getToken();
            mUID = user.getUid();
        }else{
            startActivity(new Intent(OwnerFeedback.this, DashboardOwner.class));
            finish();
        }
    }
}