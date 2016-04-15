package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class VolunteerHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_homepage);

        String username = getIntent().getStringExtra("uname_text");
        String userType = getIntent().getStringExtra("userType");
        Toast.makeText(VolunteerHomepage.this,"Homepage of " + username,Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.commit();


        Toast.makeText(VolunteerHomepage.this,"Inside VHP "+ username,Toast.LENGTH_LONG).show();
        Intent i=new Intent(VolunteerHomepage.this,AndroidLocationServices.class);
        i.putExtra("username",username);
        startService(i);

        TextView volunteerName = (TextView)findViewById(R.id.volunteer_name);

        volunteerName.setText("Welcome, " + username);
    }
}
