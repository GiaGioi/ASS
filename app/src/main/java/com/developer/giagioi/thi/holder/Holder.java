package com.developer.giagioi.thi.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developer.giagioi.thi.R;


public class Holder extends RecyclerView.ViewHolder {
    public final TextView tvid, tvUser, tvSDT,tvnam;
    public final ImageView btDelete;
    public final ImageView btnEdit;


    public Holder(View itemView) {
        super(itemView);
        btnEdit = itemView.findViewById(R.id.btnEdit);
        tvid = itemView.findViewById(R.id.tvid);
        tvUser = itemView.findViewById(R.id.tvUser);
        tvSDT = itemView.findViewById(R.id.tvSDT);
        tvnam = itemView.findViewById(R.id.tvnam);
        btDelete = itemView.findViewById(R.id.btDeleteUser);
    }
}