package com.example.sqlite.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.MainActivity;
import com.example.sqlite.R;
import com.example.sqlite.database.DBController;
import com.example.sqlite.database.teman;
import com.example.sqlite.editteman;


import java.util.ArrayList;
import java.util.HashMap;

public class temanadapter extends RecyclerView.Adapter<temanadapter.TemanViewHolder> {
    private ArrayList<teman> ListData;
    private Context control;

    {

    }

    public temanadapter(ArrayList<teman> listData) {
        this.ListData = listData;
    }

    @Override
    public temanadapter.TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinf = LayoutInflater.from(parent.getContext());
        View view = layoutinf.inflate(R.layout.rowdatateman, parent, false);
        control = parent.getContext();
        return new TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(temanadapter.TemanViewHolder holder, int position) {
        String id,nm, tlp;

        id = ListData.get(position).getId();
        nm = ListData.get(position).getNama();
        tlp = ListData.get(position).getTelpon();
        DBController db = new DBController(control);

        holder.namaTxt.setText(nm);
        holder.namaTxt.setTextSize(30);
        holder.telponTxt.setText(tlp);

        holder.cardku.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(control, holder.cardku);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.mnEdit:
                                Intent i = new Intent(control, editteman.class);
                                i.putExtra("id", id);
                                i.putExtra("nama", id);
                                i.putExtra("telpon", tlp);
                                control.startActivity(i);
                                break;
                            case R.id.mnHapus:
                                HashMap<String, String> values = new HashMap<>();
                                values.put("id", id);
                                db.DeleteData(values);
                                Intent j = new Intent(control, MainActivity.class);
                                control.startActivity(j);
                                break;

                        }
                        return true;
                    }
                });
                popupMenu.show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {

        return (ListData != null) ? ListData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {

        private CardView cardku;
        private TextView namaTxt, telponTxt;

        public TemanViewHolder(View view) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.recyclerView);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);
        }
    }
}