package com.example.salahtracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DBHandler db;
    Button add;
    Button showData;
    CheckBox checkJamat;
    EditText rakat;
    EditText nafal;
    Spinner spnNamaz;
    TextView person;
    String salahName;
    EditText select_date;
    String date;
    String jamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        person = findViewById(R.id.txtPersonName);
        spnNamaz =(Spinner) findViewById(R.id.optNamaz);
        add = findViewById(R.id.btnAddData);
        showData = findViewById(R.id.btnDisplay);
        rakat =(EditText)findViewById(R.id.txtNoOfRakat);
        nafal = (EditText) findViewById(R.id.txtNafal);
        checkJamat = findViewById(R.id.chkJamat);
        select_date=findViewById(R.id.dateDisplay);

        //adding calander
        final Calendar calandar=Calendar.getInstance();
        final int year = calandar.get(Calendar.YEAR);
        final int month = calandar.get(Calendar.MONTH);
        final int day = calandar.get(Calendar.DAY_OF_MONTH);

        select_date.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        date = day+"/"+month+"/"+year;
                        select_date.setText(date);
                    }
                },year,month,day);
                dialog.show();

            }
        });


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.SalahName, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnNamaz.setAdapter(adapter);
        spnNamaz.setOnItemSelectedListener(this);

        //CheckBox
        checkJamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkJamat.isChecked())
                {
                    jamat="yes";
//                    Toast.makeText(MainActivity.this,jamatCheckBox, Toast.LENGTH_SHORT).show();
                }
                else
                    jamat="no";


            }
        });

   //// add Button functionality
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int noOfRakat=Integer.parseInt(rakat.getText().toString());
                int nawafils=Integer.parseInt(nafal.getText().toString());
                //////NAMAZ OBJECTTTTTTTTT
                Namaz namaz=new Namaz(salahName,date,noOfRakat,jamat,nawafils);

                Person person=new Person("saba",1);

                //dbbbb
                db=new DBHandler(MainActivity.this);
                if(db.insertNamaz(namaz)==false)
                {
                    Toast.makeText(MainActivity.this, "Data is not Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data is Inserted Successfully", Toast.LENGTH_SHORT).show();

                }

   }
        });


       showData.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, DiaplayDataActivity.class);
               startActivity(intent);
           }

       });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        salahName=adapterView.getItemAtPosition(position).toString();
        Toast.makeText(this, salahName, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}