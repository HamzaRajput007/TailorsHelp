package com.example.m.a1stonnewandroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<Customer> customerList;
    public  RecyclerAdapter(List<Customer> customers){
        customerList = customers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_layout,parent);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Customer sampleCustomer = customerList.get(position);
        holder.id.setText(sampleCustomer.CustID);
        holder.Name.setText(sampleCustomer.Name);
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }
}
