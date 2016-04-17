package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main23Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);
        String name=getIntent().getStringExtra("name");
        String username = getIntent().getStringExtra("username");
        String email=getIntent().getStringExtra("email");
        String pass=getIntent().getStringExtra("password");

//        sd.setText("Username : "+uname.toString());
//        sd1.setText("Password: "+pass.toString());
//        sd2.setText("Email :"+email.toString());
        Button signin=(Button)findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main23Activity.this, MainActivity.class);
                startActivity(i);
            }
        });
        TextView confirmMessage = (TextView)findViewById(R.id.textView4);

        confirmMessage.setText("Congratulations, " + name +".\nYou've successfully signed up as a seeker with the username, "
                + username + " and email address, " + email+".");
    }
    public void onBackPressed() {
        Intent i = new Intent(Main23Activity.this,MainActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
