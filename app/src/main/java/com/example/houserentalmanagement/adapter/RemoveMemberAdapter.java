package com.example.houserentalmanagement.adapter;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.houserentalmanagement.MemberModel;
import com.example.houserentalmanagement.R;
import com.example.houserentalmanagement.houseOwner.RegisterOwner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RemoveMemberAdapter extends RecyclerView.Adapter<RemoveMemberAdapter.viewholder> {
    Context context;
    ArrayList<MemberModel> arrayList = new ArrayList<>();

    public RemoveMemberAdapter(Context context, ArrayList<MemberModel> houseModelArrayList) {
        this.context = context;
        this.arrayList = houseModelArrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viemember2, null, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        MemberModel model = arrayList.get(position);

        holder.tv_name.setText("Name: "+ model.getName());
        holder.tv_joiningDate.setText("joining date: "+ model.getJoiningDate());
        holder.tv_phoneNumber.setText("phone No: "+ model.getPhoneNumber());
        holder.tv_age.setText("Age: "+ model.getAge());
        holder.tv_job.setText("Job: "+ model.getJob());
        holder.btn_delete.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_name.getContext());
            builder.setTitle("Are you Sure?");


            builder.setPositiveButton("delete", (dialog, position1) -> {

                Query query=  FirebaseDatabase.getInstance().getReference()
                        .child(RegisterOwner.MEMBERS)
                        .child(model.getHouseId()).child(model.getOwnerId())
                        .orderByChild("key").equalTo(model.getKey());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds:snapshot.getChildren()){
                            ds.getRef().removeValue().addOnSuccessListener(unused -> {
                                arrayList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, arrayList.size());

                            });
                        }
                        Toast.makeText(context, "Deleted ", Toast.LENGTH_SHORT).show();
                        //   context.startActivity(new Intent(context, DashboardOwner.class));


                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(context, ""+error.getMessage() , Toast.LENGTH_SHORT).show();

                    }
                });



            });
            builder.setNegativeButton("cancel", (dialog, which) -> Toast.makeText(holder.tv_name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show());
            builder.show();
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_joiningDate, tv_phoneNumber, tv_age, tv_job;
        Button btn_delete;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_Name);
            tv_joiningDate = itemView.findViewById(R.id.tv_JoiningDate);
            tv_phoneNumber = itemView.findViewById(R.id.tv_PhoneNumber);
            tv_job = itemView.findViewById(R.id.tv_Job);
            tv_age = itemView.findViewById(R.id.tv_Age);
            btn_delete = itemView.findViewById(R.id.delete);

        }
    }
}



