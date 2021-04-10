package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoanView extends AppCompatActivity {
    TextView userID,userEmployeeId,trnsdate,payammount,interest;
    TextView loantype,loanamount,nomonths,every;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loanview);

        userID = findViewById(R.id.OvEditTextTransactionNo);
        userEmployeeId = findViewById(R.id.editTextEmployeeID);
        trnsdate = findViewById(R.id.OvEditTextTransactionDate);
        payammount = findViewById(R.id.OvEditTextPayAmount);
        interest = findViewById(R.id.OvEditTextInterest);
        loantype = findViewById(R.id.OvSpinnerofLoanType);
        loanamount = findViewById(R.id.OvSpinnerofLoanAmount);
        nomonths = findViewById(R.id.spinnerofLoanMonths);
        every = findViewById(R.id.OvSpinnerEvery);

        String getid = getIntent().getExtras().getString("Message");
        String empid = getIntent().getExtras().getString("Message1");
        String gettransdate = getIntent().getExtras().getString("Message2");
        String getloantype = getIntent().getExtras().getString("Message3");
        String getloanamount = getIntent().getExtras().getString("Message4");
        String getnomonths = getIntent().getExtras().getString("Message5");
        String getpayamount = getIntent().getExtras().getString("Message6");
        String getinterest = getIntent().getExtras().getString("Message7");
        String getevery = getIntent().getExtras().getString("Message8");


        userID.setText(getid);
        userEmployeeId.setText(empid);
        trnsdate.setText(gettransdate);
        payammount.setText(getpayamount);
        interest.setText(getinterest);
        loantype.setText(getloantype);
        loanamount.setText(getloanamount);
        nomonths.setText(getnomonths);
        every.setText(getevery);

        Button Submitbtn = findViewById(R.id.OvLoanSubmit);
        Submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoanView.this, "Request Sent", Toast.LENGTH_SHORT).show();
            }
        });



        Button backLoan = findViewById(R.id.OvLoanBack);
        backLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoanView.this,LoanForm.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

