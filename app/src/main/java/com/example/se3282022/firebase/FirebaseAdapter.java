package com.example.se3282022.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.se3282022.R;
import com.example.se3282022.model.User;

import java.util.ArrayList;

public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseAdapter.ViewHolder> {
    ArrayList<User> list;
    FirebaseAdapter(ArrayList<User> mList){
        list = mList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.list_item_users, parent, false);
        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = list.get(position);
        holder.id.setText(user.getId());
        holder.name.setText(user.getName());
        holder.sureName.setText(user.getSureName());
        holder.fatherName.setText(user.getFatherName());
        holder.nationalID.setText(user.getNationalID());
        holder.dob.setText(user.getDob());
        holder.gender.setText(user.getGender());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(ArrayList<User> updatedList){
        list = updatedList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView id, name, sureName, fatherName, nationalID, dob, gender;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_id);
            name = itemView.findViewById(R.id.tv_name);
            sureName = itemView.findViewById(R.id.tv_sure_name);
            fatherName = itemView.findViewById(R.id.tv_father_name);
            nationalID = itemView.findViewById(R.id.tv_nat_id);
            dob = itemView.findViewById(R.id.tv_dob);
            gender = itemView.findViewById(R.id.tv_gender);
        }
    }
}
