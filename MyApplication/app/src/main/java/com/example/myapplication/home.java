package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ReportFragment;
import androidx.transition.Slide;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

public class home extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button loanbtn = findViewById(R.id.btnloanform);
        loanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this,LoanForm.class);
                startActivity(intent);
            }
        });
        Button leavebtn = findViewById(R.id.btnleaveform);
        leavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this,LeaveForm.class);
                startActivity(intent);
            }
        });
        Button payslipbtn = findViewById(R.id.btnpayslip);
        payslipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this,Payslip.class);
                startActivity(intent);
            }
        });
        Button infobtn = findViewById(R.id.myInfoBtn);
        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this,myInfo.class);
                startActivity(intent);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = (NavigationView) findViewById(R.id.navmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_editinfo:
                 Intent intent = new Intent (home.this,EditInfo.class);
                       startActivity(intent);
                        break;

                    case R.id.menu_aboutus:
                        intent = new Intent(home.this, AboutUs.class);
                        startActivity(intent);
                        break;

                    case R.id.menu_references:
                        intent = new Intent(home.this, References.class);
                        startActivity(intent);
                        break;
                }

                return true;
            }
        });


    }

}
