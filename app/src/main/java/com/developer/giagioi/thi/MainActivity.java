package com.developer.giagioi.thi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.giagioi.thi.adapter.Adapter;
import com.developer.giagioi.thi.database.DatabaseHelper;
import com.developer.giagioi.thi.database.OnDelete;
import com.developer.giagioi.thi.model.Oto;
import com.developer.giagioi.thi.sqldao.DAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnDelete<Oto> {
    private Adapter adapter;
    private ArrayList<Oto> arrayList;
    private Toolbar toolbarLoaiSach;

    private DatabaseHelper databaseHelper;
    private com.developer.giagioi.thi.sqldao.DAO DAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        DAO = new DAO(databaseHelper);

        RecyclerView reclyquanlisach = findViewById(R.id.reclyquanliloaisach);
        arrayList = new ArrayList<>();
        arrayList.clear();
        toolbarLoaiSach = findViewById(R.id.toolbarQLLS);
        toolbarLoaiSach.setTitle("Thi");
        toolbarLoaiSach.setTitleTextColor(Color.WHITE);
        toolbarLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbarLoaiSach);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new Adapter(this, arrayList,this, DAO);

        reclyquanlisach.setLayoutManager(linearLayoutManager);
        reclyquanlisach.setHasFixedSize(true);
        reclyquanlisach.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.them) {
            final AlertDialog.Builder dialog1 = new AlertDialog.Builder(this);
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View dialogView = inflater.inflate(R.layout.dialog_add, null);
            dialog1.setView(dialogView);
            final Dialog dialog = dialog1.show();
            dialog.setTitle("Add ");

            final EditText edid;
            final EditText edName;
            final EditText ednam;

            edid = dialog.findViewById(R.id.edID);
            edName = dialog.findViewById(R.id.edname);
            ednam = dialog.findViewById(R.id.ednam);

            dialog.findViewById(R.id.btLuu).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    
                    Oto oto = new Oto();
                    oto.setID(edid.getText().toString().trim());
                    oto.setName(edName.getText().toString().trim());
                    oto.setNam(ednam.getText().toString().trim());
                    DAO.insertOto(oto);

                    arrayList.add(0, oto);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this,
                            getString(R.string.add_success), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            });
            dialog.findViewById(R.id.btnHuy).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void OnDelete(final Oto oto) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Bạn có muốn xóa oto này không?");
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                long result = DAO.deleteOto(oto.getID());
                if (result < 0) {
                    Toast.makeText(MainActivity.this, "Xóa không thành công!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Xóa Thành Công!!!", Toast.LENGTH_SHORT).show();
                    arrayList.remove(oto.getID());

                }
            }
        });
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}