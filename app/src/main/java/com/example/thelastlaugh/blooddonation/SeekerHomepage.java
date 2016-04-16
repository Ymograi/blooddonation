package com.example.thelastlaugh.blooddonation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SeekerHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_homepage);
        Toast.makeText(SeekerHomepage.this, "inseeker", Toast.LENGTH_LONG).show();
        String username = getIntent().getStringExtra("uname_text");
        String userType = getIntent().getStringExtra("userType");

        TextView seekerName = (TextView)findViewById(R.id.seeker_name);

        seekerName.setText("Welcome, " + username);
        final Spinner bg=(Spinner)findViewById(R.id.spinner2);
        String[] items=new String[]{"Select Blood Group","O+ve","O-ve","A+","A-","B+","B-","AB+","AB-"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items);
        bg.setAdapter(adapter);
        Button searchdon=(Button)findViewById(R.id.button);
    }
}
