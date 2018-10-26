package com.developer.giagioi.thi.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.giagioi.thi.R;
import com.developer.giagioi.thi.database.OnDelete;
import com.developer.giagioi.thi.holder.Holder;
import com.developer.giagioi.thi.model.Oto;
import com.developer.giagioi.thi.sqldao.DAO;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Holder>{
    private final Context context;
    private final ArrayList<Oto> arrayList;
    private final OnDelete onDelete;
    private final DAO DAO;

    public Adapter(Context context, ArrayList<Oto> arrayList, OnDelete onDelete, DAO DAO) {
        this.context = context;
        this.arrayList = arrayList;
        this.onDelete = onDelete;
        this.DAO = DAO;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        final Oto oto = arrayList.get(position);
        holder.tvid.setText(String.valueOf(oto.getID()));
        holder.tvUser.setText(oto.getID());
        holder.tvSDT.setText(oto.getName());
        holder.tvnam.setText(oto.getNam());
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDelete.OnDelete(oto);
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_add);

                final EditText edtMa;
                final EditText edtName;
                final EditText edtPos;
                Button btnAddTypeBook;
                Button btnCancel;

                edtMa = dialog.findViewById(R.id.edID);
                edtName = dialog.findViewById(R.id.edname);
                edtPos = dialog.findViewById(R.id.ednam);
                btnAddTypeBook = dialog.findViewById(R.id.btLuu);
                btnCancel = dialog.findViewById(R.id.btnHuy);
                btnAddTypeBook.setText("Update");


                edtName.setText(oto.getName());
                btnAddTypeBook.setOnClickListener(new View.OnClickListener() {
                    @SuppressWarnings("unused")
                    @Override
                    public void onClick(View view) {

                        String id = oto.getID();
                        String ma = edtMa.getText().toString().trim();
                        String name = edtName.getText().toString().trim();
                        String pos = edtPos.getText().toString().trim();
                        Oto oto = new Oto();

                        long result = DAO.updateOto(oto);
                        if (result < 0) {
                            Toast.makeText(context, "Cập nhật không thành công. Có lỗi xảy ra !!!", Toast.LENGTH_SHORT).show();
                        } else {
                            arrayList.get(position).setID(edtMa.getText().toString().trim());
                            arrayList.get(position).setName(edtName.getText().toString().trim());
                            arrayList.get(position).setNam(edtPos.getText().toString().trim());

                            notifyDataSetChanged();

                            Toast.makeText(context, "Cập nhật oto thành công!!!", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
