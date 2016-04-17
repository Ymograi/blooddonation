package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);

        final String username = getIntent().getStringExtra("uname_text");
        String userType = getIntent().getStringExtra("userType");
        final Button verify_list = (Button)findViewById(R.id.verify_list);
        Button addDon = (Button) findViewById(R.id.add_donation_record);

        setTitle("Admin Homepage");

        TextView adminName = (TextView)findViewById(R.id.admin_name);

        adminName.setText("Welcome, " + username);

        verify_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminHomepage.this,verify_list.class);
                i.putExtra("user",username);
                i.putExtra("type","admin");
                startActivity(i);
            }
        });

        addDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminHomepage.this,AddDonationRecord.class);
                i.putExtra("user",username);
                i.putExtra("type","admin");
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(AdminHomepage.this,MainActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
