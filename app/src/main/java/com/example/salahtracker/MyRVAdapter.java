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
    private ArrayList ID,namazName, date, rakhat, jamat, nafal;
    Activity activity;

    MyRVAdapter(Activity activity,Context context, ArrayList _ID, ArrayList _namazName,ArrayList _date,  ArrayList _rakhat,ArrayList _jamat,ArrayList _nafal){
        this.activity=activity;
        this.context = context;
        //Setting values
        this.ID = _ID;
        this.namazName=_namazName;
        this.date=_date;
        this.rakhat=_rakhat;
        this.jamat=_jamat;
        this.nafal=_nafal;
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
        holder.namazName_txt.setText(String.valueOf(namazName.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));
        holder.rakhat_txt.setText(String.valueOf(rakhat.get(position)));
        holder.nafal_txt.setText(String.valueOf(nafal.get(position)));
        holder.jamat_txt.setText(String.valueOf(jamat.get(position)));
        holder.single_item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,activity_update.class);
                intent.putExtra("id", String.valueOf(ID.get(position)));
                intent.putExtra("namazName", String.valueOf(namazName.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                intent.putExtra("rakhat", String.valueOf(rakhat.get(position)));
                intent.putExtra("jamat", String.valueOf(jamat.get(position)));
                intent.putExtra("nafal", String.valueOf(nafal.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });


    }

    @Override
    public int getItemCount() {
        return ID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, namazName_txt, date_txt, rakhat_txt, jamat_txt, nafal_txt;
        LinearLayout single_item_layout;
        public MyViewHolder(View itemView) {
            super(itemView);

            id_txt=itemView.findViewById(R.id.TVnm_id);
            namazName_txt=itemView.findViewById(R.id.TVNamaz);
            date_txt= itemView.findViewById(R.id.TVDate);
            rakhat_txt= itemView.findViewById(R.id.TVRakat);
            jamat_txt= itemView.findViewById(R.id.TVJamat);
            nafal_txt= itemView.findViewById(R.id.TVNafal);
            single_item_layout=itemView.findViewById(R.id.single_item);
        }
    }
}