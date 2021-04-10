package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditInfo extends AppCompatActivity {
    String uid, employeId, fullname;
    TextView userUID, userEmployeeId, userFullname;
    EditText   employeeAddress, employeeDob, employeeCompany;
    Button updateInfoBtn;
    DatePickerDialog BirthPicker;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editinfo);


        uid = getIntent().getStringExtra("uid");
        employeId = getIntent().getStringExtra("employeeId");
        fullname = getIntent().getStringExtra("firstname") +" " +getIntent().getStringExtra("lastname");

        userUID = findViewById(R.id.UID);
        userEmployeeId = findViewById(R.id.editIdnumInfo);
        userFullname = findViewById(R.id.editNmeInfo);
        employeeAddress = findViewById(R.id.editAddressInfo);
        updateInfoBtn = findViewById(R.id.UpdateBtn);
        employeeDob = findViewById(R.id.Birthdate);
        employeeCompany = findViewById(R.id.editCompanyNameInfo);
        Button homebtn1 = findViewById(R.id.homebtn);

        userUID.setText(uid);
        userEmployeeId.setText(employeId);
        userFullname.setText(fullname);

        employeeDob.setInputType(InputType.TYPE_NULL);
        employeeDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                BirthPicker = new DatePickerDialog(EditInfo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfmonth) {
                        employeeDob.setText((monthOfYear + 1) + "/" + (dayOfmonth) + "/" + year);
                    }
                }, year, month, day);
                BirthPicker.show();

            }

        });

        homebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditInfo.this,home.class);
                startActivity(intent);
                finish();
            }
        });

        updateInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (employeeAddress.getText().toString().isEmpty() || employeeDob.getText().toString().isEmpty() || employeeCompany.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter your Address, Company and Date of Birth", Toast.LENGTH_SHORT).show();
                } else
                {
                    updateInfoNow();
                }
            }
        });


    }

    private void updateInfoNow() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_UPDATEINFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), "Message: " + obj.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                String userid =  getIntent().getStringExtra("uid");
                String address = employeeAddress.getText().toString().trim() ;
                String dob = employeeDob.getText().toString().trim();
                String company = employeeCompany.getText().toString().trim();

                params.put("uid", userid);
                params.put("address", address);
                params.put("dob", dob);
                params.put("company", company);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
