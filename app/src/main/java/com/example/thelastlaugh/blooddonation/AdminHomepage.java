package com.example.thelastlaugh.blooddonation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AdminHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);

        String username = getIntent().getStringExtra("uname_text");
        String userType = getIntent().getStringExtra("userType");

        TextView adminName = (TextView)findViewById(R.id.admin_name);

        adminName.setText("Welcome, " + username);
    }
}
