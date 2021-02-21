package com.example.group7_age_act1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;


import java.util.Calendar;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    EditText Bdate,firstname,lastname;
    int Bday;
    int Bmonth;
    int Byear;
    int age;
    int Cday;
    int Cmonth;
    int Cyear;



    Calendar Bcalendar;

    DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bdate = findViewById(R.id.Bdate);


        Calendar calendar = Calendar.getInstance();
        Bcalendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                DatePickerDialog datePickerDialog = new DatePickerDialog
                        (MainActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth
                                ,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                Byear = year;
                Bmonth = month ;
                Bday = dayOfMonth;
                Bdate.setText(date);

                btn1 = (Button)findViewById(R.id.btn1) ;
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lastname = findViewById(R.id.lastname);
                        firstname = findViewById(R.id.firstname);
                        String Fullname = firstname.getText() + " " + lastname.getText() + " ";
                        Bcalendar = Calendar.getInstance();
                        Cyear = Bcalendar.get(Calendar.YEAR);
                        Cmonth = Bcalendar.get(Calendar.MONTH);
                        Cday = Bcalendar.get(Calendar.DAY_OF_MONTH);



                        if(Cday == Bday){
                            if (Bmonth == (Cmonth +1)){
                                age = Cyear - Byear;

                            }
                            if (Bmonth < (Cmonth+1)){
                                age = Cyear - Byear;
                            }
                            if (Bmonth > (Cmonth + 1)) {
                                age = Cyear = Byear;
                                age = age - 1;
                            }


                        }
                        if (Cday > Bday){
                            if (Bmonth == (Cmonth + 1)) {
                                age = Cyear - Byear;

                            }

                            if (Bmonth < (Cmonth +1)){
                                age = Cyear - Byear;
                            }
                            if (Bmonth < (Cmonth +1)){
                                age = Cyear - Byear;
                                age = age -1;

                            }
                        }
                        if (Cday < Bday){
                            if (Bmonth == (Cmonth +1)){
                                age = Cyear - Byear;
                                age = age- 1;

                            }
                            if(Bmonth < (Cmonth+1)){
                                age = Cyear - Byear;

                            }
                            if(Bmonth < (Cmonth+1)){
                                age = Cyear - Byear;
                                age = age +1;

                            }

                        }
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("");

                        if (age <18 ){
                            builder.setMessage(Fullname + "You Cannot Vote Right Now");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }

                            });builder.show();

                        }else{
                            builder.setMessage(Fullname + "You Can Vote Now");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });builder.show();

                        }


                    }
                });
            }
        } ;




    }
}


