package com.example.salahtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHandler db;
    Button add;
    Button showData;
    CheckBox jamat;
    EditText rakat;
    EditText nafal;
    Spinner namaz;
    TextView person;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        person = findViewById(R.id.txtPersonName);
        namaz = findViewById(R.id.optNamaz);
        add = findViewById(R.id.btnAddData);
        showData = findViewById(R.id.btnDisplay);
        rakat = findViewById(R.id.txtNoOfRakat);
        nafal = findViewById(R.id.txtNafal);
        jamat = findViewById(R.id.chkJamat);




        //dbbbb
        db=new DBHandler(this);
        Namaz namaz=new Namaz("asar","1/30/2023",4,"yes",2,"no");
        Person person=new Person("saba",1);
        db.insertNamaz(namaz);

        if(db.insertNamaz(namaz)==false)
        {
            Toast.makeText(MainActivity.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Data is Inserted Successfully", Toast.LENGTH_SHORT).show();

        }

    }
}