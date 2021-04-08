package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final Object URL_LOGIN = "";
    private EditText editTextPassword, firstNameText, lastNameText, editTextEmpId;
    private Button buttonRegister;
    private ProgressDialog progressDialog;
    private Button Backbtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firstNameText = (EditText) findViewById(R.id.firstnameText);
        lastNameText = (EditText) findViewById(R.id.lastnameText);
        editTextEmpId = (EditText) findViewById(R.id.editTextEmployeeID);
        editTextPassword = (EditText) findViewById(R.id.passwordText);

        buttonRegister = (Button) findViewById(R.id.registerBtn);

        progressDialog = new ProgressDialog(this);

        buttonRegister.setOnClickListener(this);
    }

    private void registerUser(){
        final String employeeId = editTextEmpId.getText().toString().trim();
        final String firstname = firstNameText.getText().toString().trim();
        final String lastname = lastNameText.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("employeeid", employeeId);
                params.put("firstname", firstname);
                params.put("lastname", lastname);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    @Override
    public void onClick(View v) {
        Backbtn = (Button)findViewById(R.id.Backbtn);
        if(v == buttonRegister){
            registerUser();
        }
    }
}
