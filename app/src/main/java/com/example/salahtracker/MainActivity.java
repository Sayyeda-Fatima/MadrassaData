package com.example.salahtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DBHandler db;
    Button add;
    Button gitbutton;
    Button showData;
    EditText sabaq;
    EditText sabki;
    EditText spnStudent;
    TextView person;
    String studentName;
    EditText manzil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        person = findViewById(R.id.txtPersonName);
        spnStudent =(EditText) findViewById(R.id.optStudent);
        add = findViewById(R.id.btnAddData);
        showData = findViewById(R.id.btnDisplay);
        sabaq =(EditText)findViewById(R.id.sabaqView);
        sabki = (EditText) findViewById(R.id.sabkiView);
        manzil =findViewById(R.id.manzilView);

        //adding calander
        final Calendar calandar=Calendar.getInstance();
        final int year = calandar.get(Calendar.YEAR);
        final int month = calandar.get(Calendar.MONTH);
        final int day = calandar.get(Calendar.DAY_OF_MONTH);
        gitbutton=findViewById(R.id.gitButton);
        gitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://github.com/Sayyeda-Fatima/MadrassaData.git";//change name later-------
                Intent intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
//        manzil.setOnClickListener(new View.OnClickListener() {
////            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this,new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                        month = month+1;
//                        date = day+"/"+month+"/"+year;
//                        manzil.setText(date);
//                    }
//                },year,month,day);
//                dialog.show();
//
//            }
//        });

        //change adapter to students name from db
//        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.SalahName, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        spnStudent.setAdapter(adapter);
//        spnStudent.setOnItemSelectedListener(this);

//        //CheckBox
//        checkJamat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(checkJamat.isChecked())
//                {
//                    jamat="yes";
////                    Toast.makeText(MainActivity.this,jamatCheckBox, Toast.LENGTH_SHORT).show();
//                }
//                else
//                    jamat="no";
//
//
//            }
//        });

   //// add Button functionality
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String studentName = spnStudent.getText().toString();
                int sabaqno=Integer.parseInt(sabaq.getText().toString());
                int sabkino=Integer.parseInt(sabki.getText().toString());
                int manzilno = Integer.parseInt(manzil.getText().toString());
                //////NAMAZ OBJECTTTTTTTTT
                Namaz namaz=new Namaz(studentName,sabkino, manzilno, sabaqno);
               // Namaz namaz1= new Namaz("fajar", "12/2/23", 2,"yes", 4);

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

//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//        studentName =adapterView.getItemAtPosition(position).toString();
//        Toast.makeText(this, studentName, Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
}