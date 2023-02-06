package com.example.salahtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_update extends AppCompatActivity {
    EditText etstudentname, etsabaq, etsabki, etmanzil;
    TextView tvDate;
    Button updateBtn, deleteBtn;
    DBHandler db;
    String id1,stdName, sabaq, sabki, manzil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        deleteBtn=findViewById(R.id.btnDelete);
        updateBtn=findViewById(R.id.btnUpdate);
        etstudentname =findViewById(R.id.etstudent);
        etsabaq =findViewById(R.id.etsabaq);
        etsabki =findViewById(R.id.etsabki);
        etmanzil =findViewById(R.id.etmanzil);
        getAndSetIntentData();
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = new DBHandler(activity_update.this);
                stdName = etstudentname.getText().toString().trim();
                sabaq = etsabaq.getText().toString().trim();
                sabki = etsabki.getText().toString().trim();
                manzil = etmanzil.getText().toString().trim();
                int  sabaqno=Integer.parseInt(sabaq);
                int manzilno=Integer.parseInt(manzil);
                int sabqiNo = Integer.parseInt(sabki);

                Namaz namaz=new Namaz(stdName,sabqiNo,manzilno,sabaqno);
                if(db.updateData(namaz)==false)
                {

                    Toast.makeText(activity_update.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(activity_update.this, namaz.studentName, Toast.LENGTH_SHORT).show();

                }

            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                db = new DBHandler(activity_update.this);

                stdName = etstudentname.getText().toString().trim();

                if( db.deleteData(stdName)==false)
                {
                    Toast.makeText(activity_update.this, "Not deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(activity_update.this,"deleted successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("studentName") &&
                getIntent().hasExtra("sabaq") && getIntent().hasExtra("sabqi") && getIntent().hasExtra("manzil")){
            //Getting Data from Intent
            id1= getIntent().getStringExtra("id");
            stdName = getIntent().getStringExtra("studentName");
            sabaq = getIntent().getStringExtra("sabaq");
            sabki =getIntent().getStringExtra("sabqi");
            manzil =getIntent().getStringExtra("manzil");
           // Toast.makeText(activity_update.this,  namazName1, Toast.LENGTH_SHORT).show();
            //Setting Intent Data
            etsabki.setText(sabki);
            etstudentname.setText(stdName);
            etsabaq.setText(sabaq);
            etmanzil.setText(manzil);
            //Log.d("stev", title+" "+author+" "+pages);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }

    }
}