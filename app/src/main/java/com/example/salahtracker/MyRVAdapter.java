package com.example.salahtracker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;



public class MyRVAdapter  extends RecyclerView.Adapter<MyRVAdapter.MyViewHolder> {
    private Context context;
    private ArrayList ID, studentname, sabaq, sabqi, manzil;
    Activity activity;

    MyRVAdapter(Activity activity,Context context, ArrayList _ID, ArrayList student,  ArrayList sabak,ArrayList sabki,ArrayList manzil){
        this.activity=activity;
        this.context = context;
        //Setting values
        this.ID = _ID;
        this.studentname =student;
        this.sabaq =sabak;
        this.sabqi =sabki;
        this.manzil =manzil;
    }

    @NonNull
    @Override
    public MyRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRVAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.id_txt.setText(String.valueOf(ID.get(position)));
        holder.studentName_txt.setText(String.valueOf(studentname.get(position)));
        holder.sabaq_txt.setText(String.valueOf(sabaq.get(position)));
        holder.manzil_txt.setText(String.valueOf(manzil.get(position)));
        holder.sabqi_txt.setText(String.valueOf(sabqi.get(position)));
        holder.single_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,activity_update.class);
                intent.putExtra("id", String.valueOf(ID.get(position)));
                intent.putExtra("studentName", String.valueOf(studentname.get(position)));
                intent.putExtra("sabaq", String.valueOf(sabaq.get(position)));
                intent.putExtra("sabqi", String.valueOf(sabqi.get(position)));
                intent.putExtra("manzil", String.valueOf(manzil.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });


    }

    @Override
    public int getItemCount() {
        return ID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, studentName_txt, sabaq_txt, sabqi_txt, manzil_txt;
        LinearLayout single_item_layout;
        public MyViewHolder(View itemView) {
            super(itemView);

            id_txt=itemView.findViewById(R.id.TVnm_id);
            studentName_txt =itemView.findViewById(R.id.TVStudent);
            sabaq_txt = itemView.findViewById(R.id.TVSabaq);
            sabqi_txt = itemView.findViewById(R.id.TVSabqi);
            manzil_txt = itemView.findViewById(R.id.TVManzil);
            single_item_layout=itemView.findViewById(R.id.single_item);
        }
    }
}