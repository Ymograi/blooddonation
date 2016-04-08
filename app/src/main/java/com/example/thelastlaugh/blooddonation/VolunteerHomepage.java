package com.example.thelastlaugh.blooddonation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class VolunteerHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_homepage);

        String username = getIntent().getStringExtra("uname_text");
        String userType = getIntent().getStringExtra("userType");

        Toast.makeText(VolunteerHomepage.this,"Inside VHP " + username,Toast.LENGTH_LONG).show();

        TextView volunteerName = (TextView)findViewById(R.id.volunteer_name);

        volunteerName.setText("Welcome, " + username);
    }
}
