package com.example.salahtracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class DiaplayDataActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> ID, studentName, date, sabaq, sabki, manzil;
    DBHandler db;
    MyRVAdapter RvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaplay_data);



        recyclerView = findViewById(R.id.recyclerView);
        db = new DBHandler(this);
        ID= new ArrayList<>();
        studentName = new ArrayList<>();
        sabaq = new ArrayList<>();
        sabki = new ArrayList<>();
        manzil = new ArrayList<>();


        storeInArray();
        RvAdapter= new MyRVAdapter(DiaplayDataActivity.this,this,ID, studentName, sabaq, sabki, manzil);
        recyclerView.setAdapter(RvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DiaplayDataActivity.this));

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }


    void storeInArray(){
        Cursor cursor = db.ReadAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(DiaplayDataActivity.this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                //Log.d();
                ID.add(cursor.getString(0));
                studentName.add(cursor.getString(1));
                sabaq.add(cursor.getString(2));
                sabki.add(cursor.getString(3));
                manzil.add(cursor.getString(4));
            }

        }
    }
}