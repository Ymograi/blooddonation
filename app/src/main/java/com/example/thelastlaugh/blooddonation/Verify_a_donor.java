package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class Verify_a_donor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_a_donor);
        final String name=getIntent().getStringExtra("name");
        final String blood=getIntent().getStringExtra("Blood");
        final String phno=getIntent().getStringExtra("phno");
        TextView nam=(TextView)findViewById(R.id.namet);
        nam.setText("Name :"+name);
        TextView blo=(TextView)findViewById(R.id.bg);
        blo.setText("Blood Group : "+blood);
        TextView phn=(TextView)findViewById(R.id.ph);
        phn.setText("Phone : "+phno);
        Button call=(Button)findViewById(R.id.cal);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phno));
                startActivity(intent);
            }
        });
        WebView webview=(WebView)findViewById(R.id.webView);
        webview.loadUrl("https://www.gmail.com");

    }
}
