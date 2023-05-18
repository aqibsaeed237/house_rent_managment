package com.example.houserentalmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.houserentalmanagement.MemberModel;
import com.example.houserentalmanagement.R;

import java.util.ArrayList;

public class ViewMemberAdapterOwner extends RecyclerView.Adapter<ViewMemberAdapterOwner.viewholder> {
    Context context;
    ArrayList<MemberModel> arrayList = new ArrayList<>();

    public ViewMemberAdapterOwner(Context context, ArrayList<MemberModel> houseModelArrayList) {
        this.context = context;
        this.arrayList = houseModelArrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewmember, null, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        MemberModel model = arrayList.get(position);

        holder.tv_name.setText("Name: "+model.getName());
        holder.tv_joiningDate.setText("joining date: "+model.getJoiningDate());
        holder.tv_phoneNumber.setText("phone No: "+model.getPhoneNumber());
        holder.tv_age.setText("Age: "+model.getAge());
        holder.tv_job.setText("Job: "+model.getJob());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_joiningDate, tv_phoneNumber, tv_age, tv_job;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_memberName);
            tv_joiningDate = itemView.findViewById(R.id.tv_memberJoiningDate);
            tv_phoneNumber = itemView.findViewById(R.id.tv_memberPhoneNumber);
            tv_job = itemView.findViewById(R.id.tv_memberJob);
            tv_age = itemView.findViewById(R.id.tv_memberAge);

        }
    }
}
