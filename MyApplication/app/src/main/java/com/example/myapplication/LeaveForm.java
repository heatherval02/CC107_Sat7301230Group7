package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class LeaveForm extends AppCompatActivity {
    TextView userUID, userEmployeeId;
    String uid, employeId;
    EditText Sdate, Edate;
    Spinner Sleave,Sdays;

    DatePickerDialog StartPicker, EndPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaveform);


        Sleave = findViewById(R.id.spinnerofleave);
        Sdays = findViewById(R.id.spinnerofdays);
        Sdate = findViewById(R.id.Startdate);
        Sdate.setInputType(InputType.TYPE_NULL);
        Edate = findViewById(R.id.Enddate);
        Edate.setInputType(InputType.TYPE_NULL);
        userUID = findViewById(R.id.editTextLeaveTransaction);
        userEmployeeId = findViewById(R.id.editTextEmployeeID);

        Sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                StartPicker = new DatePickerDialog(LeaveForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfmonth) {
                        Sdate.setText((monthOfYear + 1) + "/" + (dayOfmonth) + "/" + year);
                    }
                }, year, month, day);
                StartPicker.show();


            }

        });

        Edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                EndPicker = new DatePickerDialog(LeaveForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfmonth) {
                        Edate.setText((monthOfYear + 1) + "/" + (dayOfmonth) + "/" + year);
                    }
                }, year, month, day);
                EndPicker.show();


            }
        });
        Button viewbtn = findViewById(R.id.LeaveButton);
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Leaveview.class);
                intent.putExtra("Message",userUID.getText().toString());
                intent.putExtra("Message1",userEmployeeId.getText().toString());
                intent.putExtra("Message2",Sleave.getSelectedItem().toString());
                intent.putExtra("Message3",Sdays.getSelectedItem().toString());
                intent.putExtra("Message4",Sdate.getText().toString());
                intent.putExtra("Message5",Edate.getText().toString());
                startActivity(intent);
                finish();


                startActivity(intent);
            }
        });

    }
}
