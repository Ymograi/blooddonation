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
        TextView sd=(TextView)findViewById(R.id.textView5);
        TextView sd1=(TextView)findViewById(R.id.textView6);
        TextView sd2=(TextView)findViewById(R.id.textView7);
        String uname=getIntent().getStringExtra("name");
        String email=getIntent().getStringExtra("email");
        String pass=getIntent().getStringExtra("password");

        sd.setText("Username : "+uname.toString());
        sd1.setText("Password: "+pass.toString());
        sd2.setText("Email :"+email.toString());
        Button signin=(Button)findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main23Activity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
