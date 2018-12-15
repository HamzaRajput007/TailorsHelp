package com.example.m.a1stonnewandroid;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewHolder extends RecyclerView.ViewHolder {
    public EditText id;
    public EditText Name;
    View v;

    public ViewHolder(View itemView) {
        super(itemView);

        id = v.findViewById(R.id.IdTvId);
        Name = v.findViewById(R.id.NameTvId);
    }
}
