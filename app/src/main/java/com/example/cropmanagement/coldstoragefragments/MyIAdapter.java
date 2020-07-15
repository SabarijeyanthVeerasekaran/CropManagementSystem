package com.example.cropmanagement.coldstoragefragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cropmanagement.R;

import java.util.ArrayList;

public class MyIAdapter extends RecyclerView.Adapter<MyIAdapter.MyViewHolder> {
   Context context;
   ArrayList<ColdFire> coldFires;

    public MyIAdapter(Context context, ArrayList<ColdFire> coldFires) {
        this.context = context;
        this.coldFires = coldFires;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.card_districts,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.card_storagename.setText(position+1+"."+coldFires.get(position).getName());
        holder.card_capacity.setText(String.valueOf(coldFires.get(position).getCapacity()));
        holder.card_purpose.setText(coldFires.get(position).getPurpose());


    }

    @Override
    public int getItemCount() {
        return coldFires.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView card_storagename;
        TextView card_capacity;
        TextView card_purpose;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_storagename=itemView.findViewById(R.id.card_storagename);
            card_capacity=itemView.findViewById(R.id.card_capacity);
            card_purpose=itemView.findViewById(R.id.card_purpose);
        }
    }
}
