package com.example.salahtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class DiaplayDataActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> ID,namazName, date, rakhat, jamat, nafal;
    DBHandler db;
    MyRVAdapter RvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaplay_data);



        recyclerView = findViewById(R.id.recyclerView);
        db = new DBHandler(this);
        ID= new ArrayList<>();
        namazName= new ArrayList<>();
        date = new ArrayList<>();
        rakhat= new ArrayList<>();
        jamat = new ArrayList<>();
        nafal = new ArrayList<>();


        storeInArray();
        RvAdapter= new MyRVAdapter(DiaplayDataActivity.this,this,ID,namazName, date, rakhat, jamat, nafal);
        recyclerView.setAdapter(RvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DiaplayDataActivity.this));

    }
    void storeInArray(){
        Cursor cursor = db.ReadAllData();

        if(cursor.getCount() == 0){
            Toast.makeText(DiaplayDataActivity.this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                //Log.d();
                ID.add(cursor.getString(0));
                namazName.add(cursor.getString(1));
                date.add(cursor.getString(2));
                rakhat.add(cursor.getString(3));
                jamat.add(cursor.getString(4));
                nafal.add(cursor.getString(5));
            }

        }
    }
}