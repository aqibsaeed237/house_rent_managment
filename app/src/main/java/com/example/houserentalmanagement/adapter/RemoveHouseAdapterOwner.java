package com.example.houserentalmanagement.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.houserentalmanagement.HouseModel;
import com.example.houserentalmanagement.R;
import com.example.houserentalmanagement.houseOwner.DashboardOwner;
import com.example.houserentalmanagement.houseOwner.RegisterOwner;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RemoveHouseAdapterOwner extends RecyclerView.Adapter<RemoveHouseAdapterOwner.viewholder>{
    Context context;
    ArrayList<HouseModel> houseModelArrayList;


    public RemoveHouseAdapterOwner(Context context, ArrayList<HouseModel> houseModelArrayList) {
        this.context = context;
        this.houseModelArrayList = houseModelArrayList;
    }

    @NonNull
    @Override
    public RemoveHouseAdapterOwner.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.viewhouse2, null, false);
        return new RemoveHouseAdapterOwner.viewholder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RemoveHouseAdapterOwner.viewholder holder, @SuppressLint("RecyclerView") int position) {
        HouseModel model = houseModelArrayList.get(position);


        holder.tv_houseId.setText("House Id: " +model.getHouseId());
        holder.tv_location.setText("Location: " +model.getHouseLocation());
        holder.tv_rentPerRoom.setText("Rent Per Room: " +model.getRentPerRoom());
        holder.tv_noOfRoom.setText("No. Of Room: " +model.getNoOfRoom());
        Glide.with(context).load(model.getHouseImage()).into(holder.iv_houseImage);






        holder.delete.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_houseId.getContext());
            builder.setTitle("Are you Sure?");


            builder.setPositiveButton("delete", (dialog, position1) -> {

                Query query= FirebaseDatabase.getInstance().getReference().child(RegisterOwner.HOUSES).child(model.getUserId())
                        .orderByChild("key").equalTo(model.getKey());
               query.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       for(DataSnapshot ds:snapshot.getChildren()){
                           ds.getRef().removeValue().addOnSuccessListener(unused -> {
                               houseModelArrayList.remove(position);
                               notifyItemRemoved(position);
                               notifyItemRangeChanged(position, houseModelArrayList.size());

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
            builder.setNegativeButton("cancel", (dialog, which) -> Toast.makeText(holder.tv_houseId.getContext(), "Cancelled", Toast.LENGTH_SHORT).show());
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return houseModelArrayList.size();

    }


     static class viewholder extends RecyclerView.ViewHolder {
        ImageView iv_houseImage;
        TextView tv_noOfRoom, tv_rentPerRoom, tv_location,tv_houseId;
        Button delete;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            iv_houseImage = itemView.findViewById(R.id.imageview);
            tv_noOfRoom = itemView.findViewById(R.id.tv_noOfRooms);
            tv_rentPerRoom = itemView.findViewById(R.id.tv_rentPerRoom);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_houseId = itemView.findViewById(R.id.tv_houseId);
            delete = itemView.findViewById(R.id.delete);

        }
    }
}
