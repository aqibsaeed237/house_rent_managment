package com.example.houserentalmanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.houserentalmanagement.HouseModel;
import com.example.houserentalmanagement.R;
import com.example.houserentalmanagement.user.HouseDetails;

import java.util.ArrayList;

public class ViewHouseAdapterUser extends RecyclerView.Adapter<ViewHouseAdapterUser.viewholder>{
    Context context;
    ArrayList<HouseModel> houseModelArrayList = new ArrayList<>();

    public ViewHouseAdapterUser(Context context, ArrayList<HouseModel> houseModelArrayList, int i) {
        this.context = context;
        this.houseModelArrayList = houseModelArrayList;
    }

    @NonNull
    @Override
    public ViewHouseAdapterUser.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewhouse, null, false);
        return new ViewHouseAdapterUser.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHouseAdapterUser.viewholder holder, int position) {
        HouseModel model = houseModelArrayList.get(position);

        holder.tv_location.setText(""+model.getHouseLocation());
        holder.house_id.setText("House Id: "+model.getHouseId());
        holder.tv_rentPerRoom.setText("Rent Per Room: "+model.getRentPerRoom());
        holder.tv_noOfRoom.setText("No. Of Room: "+model.getNoOfRoom());
        Glide.with(context).load(model.getHouseImage()).into(holder.iv_houseImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HouseDetails.class);

                intent.putExtra("houseId", model.getHouseId());
                intent.putExtra("noOfRoom", model.getNoOfRoom());
                intent.putExtra("rentPerRoom", model.getRentPerRoom());
                intent.putExtra("houseDescription", model.getHouseDescription());
                intent.putExtra("houseLocation", model.getHouseLocation());
                intent.putExtra("houseImage", model.getHouseImage());
                intent.putExtra("userId", model.getUserId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return houseModelArrayList.size();
    }

    public void searchData(ArrayList<HouseModel> searchList){
        houseModelArrayList = searchList;
        notifyDataSetChanged();
    }

    class viewholder extends RecyclerView.ViewHolder {
        ImageView iv_houseImage;
        TextView tv_noOfRoom, tv_rentPerRoom, tv_location,house_id;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            iv_houseImage = itemView.findViewById(R.id.imageview);
            tv_noOfRoom = itemView.findViewById(R.id.tv_noOfRooms);
            tv_rentPerRoom = itemView.findViewById(R.id.tv_rentPerRoom);
            tv_location = itemView.findViewById(R.id.tv_location);
            house_id = itemView.findViewById(R.id.tv_houseId);

        }
    }
}
