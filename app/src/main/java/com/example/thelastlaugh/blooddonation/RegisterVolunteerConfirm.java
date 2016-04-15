package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterVolunteerConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_volunteer_confirm);

        String username = getIntent().getStringExtra("username");
        String email = getIntent().getStringExtra("email");
        String name = getIntent().getStringExtra("name");

        Button signin = (Button)findViewById(R.id.signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterVolunteerConfirm.this, MainActivity.class);
                startActivity(i);
            }
        });

        TextView confirmMessage = (TextView)findViewById(R.id.confirm_message);

        confirmMessage.setText("Congratulations, " + name +".\nYou've successfully signed up as a volunteer with the username, "
                + username + ". Further instructions have been mailed to your email address, " + email+".");
    }
}
