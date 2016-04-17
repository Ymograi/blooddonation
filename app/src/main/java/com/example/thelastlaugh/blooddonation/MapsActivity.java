package com.example.thelastlaugh.blooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    String username,userType;
    private GoogleMap mMap;
    double lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button click=new Button(MapsActivity.this);
        username = getIntent().getStringExtra("uname_text");
        userType = getIntent().getStringExtra("type");


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        GPSTracker gps=new GPSTracker(MapsActivity.this);

            lat=gps.getLatitude();
            lon=gps.getLongitude();
            mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng Mylocation = new LatLng(lat,lon );
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.addMarker(new MarkerOptions().position(Mylocation).title("Present Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Mylocation,14.0f));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                 lat=latLng.latitude;
                lon=latLng.longitude;
                // Clears the previously touched position
                mMap.clear();

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                //Toast.makeText(MapsActivity.this, "Your Location is - \nLat: " + lat + "\nLong: " + lon, Toast.LENGTH_LONG).show();

//                final double lat=latLng.latitude;
//                final double lon=latLng.longitude;

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent i=new Intent(MapsActivity.this,SeekerHomepage.class);
        i.putExtra("uname_text", username);
        i.putExtra("type", userType);
        i.putExtra("lat",lat);
        i.putExtra("lon",lon);
        //Toast.makeText(MapsActivity.this, "Your Location is - \nLat: " + lat + "\nLong: " + lon, Toast.LENGTH_LONG).show();
        startActivity(i);

    }

}
