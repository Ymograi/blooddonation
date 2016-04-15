package com.example.thelastlaugh.blooddonation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String s=getIntent().getStringExtra("name");
        TextView hello=(TextView)findViewById(R.id.textView3);
        String g="Welcome Admin ";
        hello.setText(g.toString()+s.toString());
    }
}
