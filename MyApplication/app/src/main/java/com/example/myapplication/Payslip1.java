package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Payslip1 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payslip1);

        Button requestbtn = findViewById(R.id.Requestbtn1);
        requestbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Payslip1.this, "Request Sent", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnback = findViewById(R.id.payslipNext);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payslip1.this,home.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
