package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SeekerHomepage extends AppCompatActivity {
    GPSTracker gps;
    double lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_homepage);

        setTitle("Seeker Homepage");
//        Toast.makeText(SeekerHomepage.this, "in seeker", Toast.LENGTH_LONG).show();
        final String username = getIntent().getStringExtra("uname_text");
        final String userType = getIntent().getStringExtra("userType");
        lat=getIntent().getDoubleExtra("lat",0);
        lon=getIntent().getDoubleExtra("lon",0);
        //Toast.makeText(SeekerHomepage.this,"after lat long "+lat+lon , Toast.LENGTH_LONG).show();
        TextView seekerName = (TextView)findViewById(R.id.seeker_name);
        final TextView latit=(TextView)findViewById(R.id.lat);
        final TextView longit=(TextView)findViewById(R.id.lon);
        seekerName.setText("Welcome, " + username);
        latit.setText("LATITUDE:  " + lat);
        longit.setText("LONGITUDE: " + lon);
        final Spinner bg=(Spinner)findViewById(R.id.spinner2);
        String[] items=new String[]{"Select Blood Group","O+","O-","A+","A-","B+","B-","AB+","AB-"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items);
        bg.setAdapter(adapter);
        Button searchdon=(Button)findViewById(R.id.button);
        Button myloc=(Button)findViewById(R.id.button2);
        Button setloc=(Button)findViewById(R.id.button3);

        setloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SeekerHomepage.this,MapsActivity.class);
                i.putExtra("uname_text", username);
                i.putExtra("type", userType);
                startActivity(i);
            }
        });
        myloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps=new GPSTracker(SeekerHomepage.this);
                if(gps.canGetLocation()){
                    lat=gps.getLatitude();
                    lon=gps.getLongitude();
                    latit.setText("LATITUDE:  " + lat);
                    longit.setText("LONGITUDE: " + lon);}
                  //  Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + lat + "\nLong: " + lon, Toast.LENGTH_LONG).show();}
                    else{
                        gps.showSettingsAlert();

                }
            }
        });
        searchdon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bld=bg.getSelectedItem().toString();

                if(!bld.equalsIgnoreCase("Select Blood Group")) {
                    latit.setText("LATITUDE:  " + lat);
                    longit.setText("LONGITUDE: " + lon);
                    Intent i = new Intent(SeekerHomepage.this, Donor_list.class);
                    i.putExtra("lat", lat);
                    i.putExtra("lon", lon);
                    i.putExtra("bg",bld );
                    i.putExtra("username",username);
                    startActivity(i);
                }
                else
                {
                    TextView err=(TextView)findViewById(R.id.textView9);
                    err.setText("Please Select Blood Group");
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(SeekerHomepage.this,MainActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
