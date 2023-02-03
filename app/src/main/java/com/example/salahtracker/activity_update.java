package com.example.salahtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity_update extends AppCompatActivity {
    EditText etnamazName, etrakhat, etjamat, etnafal;
    TextView tvDate;
    Button updateBtn, deleteBtn;
    DBHandler db;
    String id1,date1,namazName1, rakhat1, jamat1, nafal1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        deleteBtn=findViewById(R.id.btnDelete);
        updateBtn=findViewById(R.id.btnUpdate);
        etnamazName=findViewById(R.id.etnamaz);
        etrakhat=findViewById(R.id.etRakat);
        etjamat=findViewById(R.id.etJamat);
        etnafal=findViewById(R.id.etnafal);
        tvDate=findViewById(R.id.tvDate);
        getAndSetIntentData();
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = new DBHandler(activity_update.this);
                namazName1 = etnamazName.getText().toString().trim();
                rakhat1 = etrakhat.getText().toString().trim();
                jamat1 =etjamat.getText().toString().trim();
                nafal1= etnafal.getText().toString().trim();
                date1= tvDate.getText().toString().trim();
                int  rakatInteger=Integer.parseInt(rakhat1);
                int nafalInteger=Integer.parseInt(nafal1);

                Namaz namaz=new Namaz(namazName1,date1,rakatInteger,jamat1,nafalInteger);
                if(db.updateData(namaz)==false)
                {

                    Toast.makeText(activity_update.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(activity_update.this, namaz.namazName, Toast.LENGTH_SHORT).show();

                }

            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                db = new DBHandler(activity_update.this);

                namazName1 = etnamazName.getText().toString().trim();

                if( db.deleteData(namazName1)==false)
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
        if(getIntent().hasExtra("id") && getIntent().hasExtra("namazName") &&
                getIntent().hasExtra("date") && getIntent().hasExtra("rakhat") && getIntent().hasExtra("jamat")
                && getIntent().hasExtra("nafal")){
            //Getting Data from Intent
            id1= getIntent().getStringExtra("id");
            namazName1 = getIntent().getStringExtra("namazName");
            date1 = getIntent().getStringExtra("date");
            rakhat1 = getIntent().getStringExtra("rakhat");
            jamat1=getIntent().getStringExtra("jamat");
            nafal1=getIntent().getStringExtra("nafal");
            Toast.makeText(activity_update.this,  namazName1, Toast.LENGTH_SHORT).show();

            //Setting Intent Data
            etjamat.setText(jamat1);
            etnamazName.setText(namazName1);
            etrakhat.setText(rakhat1);
            etnafal.setText(nafal1);
            tvDate.setText(date1);
            //Log.d("stev", title+" "+author+" "+pages);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }

    }
}