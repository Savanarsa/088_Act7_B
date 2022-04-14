package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.example.sqlite.adapter.temanadapter;
import com.example.sqlite.database.DBController;
import com.example.sqlite.database.teman;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private temanadapter adapter;
    private ArrayList<teman> temanArrayList;
    DBController controler = new DBController(this);
    String id,nama,telpon;
    private FloatingActionButton fab;
    private Object temanadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingBtn);
        basisdata();
        adapter = new temanadapter(temanArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter((RecyclerView.Adapter) temanadapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), temanbaru.class);
                startActivity(intent);
            }
        });

    }

    public  void basisdata(){
        ArrayList<HashMap<String,String>> daftarTeman = controler.getAllteman();
        temanArrayList = new ArrayList<teman>();
        //memindah dari hasil query kedalam teman
        for(int i=0;i<daftarTeman.size();i++){
            teman teman = new teman();
            teman.setId(daftarTeman.get(i).get("id").toString());
            teman.setNama(daftarTeman.get(i).get("nama").toString());
            teman.setTelpon(daftarTeman.get(i).get("telpon").toString());
            // Pindahkan dari Teman kedalam ArrayList teman di adapter
            temanArrayList.add(teman);
        }

    }
}

