package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class LoanForm extends AppCompatActivity {
    DatePickerDialog Picker;
    EditText userID,userEmployeeId,trnsdate,payammount,interest;
    Spinner loantype,loanamount,nomonths,every;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loanform);
        userID = findViewById(R.id.editTextTransactionNo);
        userEmployeeId = findViewById(R.id.editTextEmployeeID);
        trnsdate = findViewById(R.id.editTextTransactionDate);
        payammount = findViewById(R.id.editTextPayAmount);
        interest = findViewById(R.id.editTextInterest);
        loantype = findViewById(R.id.spinnerofLoanType);
        loanamount = findViewById(R.id.spinnerofLoanAmount);
        nomonths = findViewById(R.id.spinnerofLoanMonths);
        every = findViewById(R.id.spinnerEvery);

        trnsdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                Picker = new DatePickerDialog(LoanForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfmonth) {
                        trnsdate.setText((monthOfYear + 1) + "/" + (dayOfmonth) + "/" + year);
                    }
                }, year, month, day);
                Picker.show();
            }
        });

        Button Viewbtn = findViewById(R.id.LoanButton);
        Viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoanView.class);
                intent.putExtra("Message",userID.getText().toString());
                intent.putExtra("Message1",userEmployeeId.getText().toString());
                intent.putExtra("Message2",trnsdate.getText().toString());
                intent.putExtra("Message3",loantype.getSelectedItem().toString());
                intent.putExtra("Message4",loanamount.getSelectedItem().toString());
                intent.putExtra("Message5",nomonths.getSelectedItem().toString());
                intent.putExtra("Message6",payammount.getText().toString());
                intent.putExtra("Message7",interest.getText().toString());
                intent.putExtra("Message8",every.getSelectedItem().toString());
                startActivity(intent);
                finish();


                startActivity(intent);
            }
        });
    }
}