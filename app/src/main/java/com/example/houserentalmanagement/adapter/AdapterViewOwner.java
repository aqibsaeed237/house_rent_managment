package com.example.houserentalmanagement.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.houserentalmanagement.Admin.ViewOwner;
import com.example.houserentalmanagement.Admin.ViewUser;
import com.example.houserentalmanagement.Model.Owner;
import com.example.houserentalmanagement.R;
import com.example.houserentalmanagement.houseOwner.RegisterOwner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterViewOwner extends RecyclerView.Adapter<AdapterViewOwner.viewholder> {
    Context context;
    ArrayList<Owner> arrayList = new ArrayList<>();
    String action;


    public AdapterViewOwner(Context context, ArrayList<Owner> arrayList,String action) {
        this.context = context;
        this.arrayList = arrayList;
        this.action=action;


    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_users, null, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") final int position) {
        String id= arrayList.get(position).getId();
        String name= arrayList.get(position).getUsername();
        String email= arrayList.get(position).getEmail();
        String phoneNo = arrayList.get(position).getPhoneNo();




        holder.tv_name.setText("Name: "+ name);
        holder.id.setText("Id: "+ id);
        holder.email.setText("Email: "+ email);
        holder.phone.setText("Phone No: "+ phoneNo);


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, email+" "+arrayList.get(position).getPassword(), Toast.LENGTH_SHORT).show();
//                signIn(email,arrayList.get(position).getPassword().toString());
//                showDialog(arrayList.get(position));
//            }
//        });
    }
//    private void signIn(String email, String password) {
//        final ProgressDialog progressDialog;
//        final FirebaseAuth mAuth;
//        mAuth= FirebaseAuth.getInstance();
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                        } else {
//                        }
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        // progressDialog.dismiss();
//                        Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//  }
//    private void showDialog(final Owner users) {
//
//        String id=action.equals("Users")?"userId":"id";
//        //this function is for removing book from database
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//        alertDialogBuilder.setMessage("Do You Want To Delete Owner");
//        alertDialogBuilder.setPositiveButton("yes",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        final ProgressDialog pd=new ProgressDialog(context);
//                        pd.setMessage("Deleting..");
//                        //image
//                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//                        AuthCredential credential = EmailAuthProvider
//                                .getCredential(users.getEmail(), users.getPassword());
//
//                        // Prompt the user to re-provide their sign-in credentials
//                        user.reauthenticate(credential)
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<Void> task) {
//                                                if (task.isSuccessful()) {
//                                                    Query fquery= FirebaseDatabase.getInstance().getReference().child(RegisterOwner.OWNER)
//                                                            .orderByChild("id").equalTo(user.getUid());
//                                                    fquery.addValueEventListener(new ValueEventListener() {
//                                                        @Override
//                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                                            for(DataSnapshot ds:dataSnapshot.getChildren()){
//                                                                ds.getRef().removeValue();
//                                                            }
//                                                            Toast.makeText(context, "Deleted ", Toast.LENGTH_SHORT).show();
//                                                            pd.dismiss();
//                                                            context.startActivity(new Intent(context, ViewOwner.class));
//                                                        }
//                                                        @Override
//                                                        public void onCancelled(@NonNull DatabaseError databaseError) {
//                                                        }
//                                                    });
//
//                                                }
//                                            }
//                                        });
//
//                                    }
//                                });
//
//                    }
//                });
//
//        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView tv_name, email, id, phone;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.name);
            tv_name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            id = itemView.findViewById(R.id.id);
            phone = itemView.findViewById(R.id.phone);


        }
    }
}
