package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class EditInfo extends AppCompatActivity {
    EditText Bdate;
    DatePickerDialog BirthPicker;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editinfo);
        Bdate = findViewById(R.id.Birthdate);
        Bdate.setInputType(InputType.TYPE_NULL);
        Bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                BirthPicker = new DatePickerDialog(EditInfo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfmonth) {
                        Bdate.setText((monthOfYear + 1) + "/" + (dayOfmonth) + "/" + year);
                    }
                }, year, month, day);
                BirthPicker.show();

            }

        });
        Button saveBtn = findViewById(R.id.SaveBtn);
        final EditText name = findViewById(R.id.editNmeInfo);
        final EditText empId = findViewById(R.id.editIdnumInfo);
        final EditText age = findViewById(R.id.editAgeInfo);
        final EditText Bdate = findViewById(R.id.Birthdate);
        final EditText Address = findViewById(R.id.editAddressInfo);
        final EditText Comname = findViewById(R.id.editCompanyNameInfo);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),myInfo.class);
                intent.putExtra("Message1",name.getText().toString());
                intent.putExtra("Message2",empId.getText().toString());
                intent.putExtra("Message3",age.getText().toString());
                intent.putExtra("Message4",Bdate.getText().toString());
                intent.putExtra("Message5",Address.getText().toString());
                intent.putExtra("Message6",Comname.getText().toString());
                startActivity(intent);
                finish();

            }
        });



    }
}
