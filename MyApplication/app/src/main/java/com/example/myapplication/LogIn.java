package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

import java.util.HashMap;
import java.util.Map;


public class LogIn extends AppCompatActivity {

    EditText employee, password;
    Button LoginBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        employee = findViewById(R.id.empId);
        password = findViewById(R.id.pass);
        LoginBtn = findViewById(R.id.btnlog);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (employee.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty Fields", Toast.LENGTH_SHORT).show();


                } else {
                    loginNow();
                }
            }

            private void loginNow () {
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_LOGIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject obj = new JSONObject(response);

                                    if (obj.getString("message").equalsIgnoreCase("User Log in")) {

                                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                        Intent newIntent;
                                        newIntent = new Intent(LogIn.this, home.class);
                                        newIntent.putExtra("uid", obj.getString("uid"));
                                        newIntent.putExtra("employeeId", obj.getString("employeeId"));
                                        newIntent.putExtra("firstname", obj.getString("firstname"));
                                        newIntent.putExtra("lastname", obj.getString("lastname"));
                                        newIntent.putExtra("password", obj.getString("password"));
                                        startActivity(newIntent);

                                    } else {
                                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Returned Obj" + e, Toast.LENGTH_SHORT).show();
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        String empid = employee.getText().toString().trim();
                        String empPass = password.getText().toString().trim();

                        params.put("employee_id", empid);
                        params.put("password", empPass);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(LogIn.this);
                requestQueue.add(stringRequest);


            }



        });
        Button register = findViewById(R.id.btnreg);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (LogIn.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}


