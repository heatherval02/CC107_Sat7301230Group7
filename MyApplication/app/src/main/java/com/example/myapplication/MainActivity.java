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

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final Object URL_LOGIN =;
    private EditText editTextEmpID, editTextPassword;
    private Button buttonRegister;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmpID = (EditText) findViewById(R.id.editTextEmpID);
        editTextPassword = (EditText) findViewById(R.id.editTextpassword);

        buttonRegister = (Button) findViewById(R.id.button);

        progressDialog = new ProgressDialog(this);

        buttonRegister.setOnClickListener(this);

    }

    private void registerUser(){
        final String EmpID = editTextEmpID.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST
                Constant.URL_LOGIN,
                new Response.Listener<String> {

            public void onResponse(String response) {
                progressDialog.dismiss();

                try{
                    JSONObject jsonObject = new JSONObject(response);

                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };
                new Response.ErrorListener()

    @Override
    public void onClick(View view) {
        if (view == buttonRegister)
            registerUser();
        }

    }
}
